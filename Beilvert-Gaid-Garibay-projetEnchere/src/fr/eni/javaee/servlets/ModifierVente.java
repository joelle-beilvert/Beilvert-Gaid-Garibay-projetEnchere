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
import fr.eni.javaee.bll.ArticleManager;
import fr.eni.javaee.bll.CategorieManager;
import fr.eni.javaee.bo.ArticleVendu;
import fr.eni.javaee.bo.Categorie;
import fr.eni.javaee.bo.Utilisateur;
import fr.eni.javaee.exception.GeneralException;


@WebServlet("/ModifierVente")
public class ModifierVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		CategorieManager catMger = new CategorieManager();
		List<Categorie> listeCategorie = new ArrayList<Categorie>();
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		ArticleManager mger = new ArticleManager();
		ArticleVendu article = null;
		
		listeCategorie = catMger.selectAll();
		request.setAttribute("listeCategorie", listeCategorie);
		
		article = mger.select(noArticle);
		request.setAttribute("articleVendu", article);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/modificationVente.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Récupération de l'utilisateur en session qui est le vendeur
		HttpSession session = request.getSession();
		Utilisateur vendeur = (Utilisateur) session.getAttribute("utilisateur");

		// Récupération des données du formulaire
		String nomArticle = request.getParameter("nom");
		String description = request.getParameter("description");
		String dateDebutEncheresStr = request.getParameter("dateDebut");
		String dateFinEncheresStr = request.getParameter("dateFin");
		System.out.println("categorie" + request.getParameter("noCategorie"));
		int noCategorie = Integer.parseInt(request.getParameter("noCategorie"));
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));

		// Initialisation variables
		LocalDateTime dateFinEncheres = null;
		LocalDateTime dateDebutEncheres = null;
		int prixInitial = 0;
		ArticleManager artMger = new ArticleManager();
		GeneralException be = new GeneralException();
		
		// Conversion pour les dates
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
			dateDebutEncheres = LocalDateTime.parse(dateDebutEncheresStr, formatter);
			dateFinEncheres = LocalDateTime.parse(dateFinEncheresStr, formatter);
		} catch (DateTimeParseException e) {
			e.printStackTrace();
			request.setAttribute("error", "Erreur de saisie dans les données Date de début et/ou fin enchère");
		}
		
		// Conversion pour les entiers
		try {
			prixInitial = Integer.parseInt(request.getParameter("prixInitial").trim());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("error", "Erreur de saisie dans les données de type numérique");
		}
		
		Categorie categorie = new Categorie();
		categorie.setNoCategorie(noCategorie);

		// Construction de l'objet et requete d'update
		ArticleVendu art = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres,
				prixInitial, vendeur, categorie);
		art.setNoArticle(noArticle);
		artMger.modifierArticle(art);
		request.setAttribute("success", "La vente a bien été modifié");
		RequestDispatcher rd = request.getRequestDispatcher("/afficherDetailEnchere");
		rd.forward(request, response);
	}
}