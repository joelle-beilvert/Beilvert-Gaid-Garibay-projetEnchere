package fr.eni.javaee.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.eni.javaee.servlets.ArticleManager;
import fr.eni.javaee.servlets.CategorieManager;
import fr.eni.javaee.bo.ArticleVendu;
import fr.eni.javaee.servlets.Categorie;
import fr.eni.javaee.servlets.Retrait;
import fr.eni.javaee.bo.Utilisateur;
import fr.eni.javaee.exception.GeneralException;


@WebServlet("/Vendre")
public class Vendre extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur vendeur = (Utilisateur) session.getAttribute("utilisateur");
		request.setAttribute("vendeur", vendeur);
		CategorieManager catMger = new CategorieManager();
		List<Categorie> listeCategorie = new ArrayList<Categorie>();
		
		listeCategorie = catMger.selectAll();
				request.setAttribute("listeCategorie", listeCategorie);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/vendreUnArticle.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Récupération de l'utilisateur en session qui est le vendeur
		HttpSession session = request.getSession();
		Utilisateur vendeur = (Utilisateur) session.getAttribute("utilisateur");
		
		// Récupération des données du formulaire
		String nomArticle = request.getParameter("nom").trim();
		String description = request.getParameter("description").trim();
		String dateDebutEncheresStr = request.getParameter("dateDebut");
		String dateFinEncheresStr = request.getParameter("dateFin");
		int noCategorie = Integer.parseInt(request.getParameter("noCategorie"));
		int noArticle;
		
		// Initialisation variables
		LocalDateTime dateFinEncheres = null;
		LocalDateTime dateDebutEncheres = null;
		int prixInitial = 0;
		
		ArticleManager artMger = new ArticleManager();
		GeneralException be = new GeneralException();
		
		//Conversion pour les dates
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
			dateDebutEncheres = LocalDateTime.parse(dateDebutEncheresStr, formatter);
			dateFinEncheres = LocalDateTime.parse(dateFinEncheresStr, formatter);
		} catch (DateTimeParseException e) {
			e.printStackTrace();
			request.setAttribute("error", "Erreur de saisie dans les données Date de début et/ou fin enchère");
		}
		
		//Conversion pour les entiers
		try {
			prixInitial = Integer.parseInt(request.getParameter("prixInitial").trim());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("error", "Erreur de saisie dans les données de type numérique");
		}
		
		// récupere les 3 champs de RETRAIT, puis construction des objets
		String rue = request.getParameter("rue").trim();
		String codePostal = request.getParameter("codePostal").trim();
		String ville = request.getParameter("ville").trim();
		Retrait retrait = new Retrait(rue, codePostal, ville);
		Categorie categorie = new Categorie();
		categorie.setNoCategorie(noCategorie);
		ArticleVendu art = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial, vendeur, categorie);
		
		//Test si l'adresse de retrait a été remplie puis insert en fonction
		if(retrait.getRue().isEmpty() || retrait.getCodePostal().isEmpty() || retrait.getVille().isEmpty()) {
			noArticle = artMger.ajouterArticle(art);
		}else {
			art.setRetrait(retrait);
			noArticle = artMger.ajouterArticleRetrait(art);
		}
		
		//Test erreur
		if (noArticle==0) {
			request.setAttribute("error", "Erreur lors de l'insertion de l'article");
			doGet(request, response);
		}else {
			request.setAttribute("success", "L'article a bien été mis en vente");
			request.setAttribute("noArticle", noArticle);
			RequestDispatcher rd = request.getRequestDispatcher("afficherDetailEnchere");
			rd.forward(request, response);
		}

	}

}