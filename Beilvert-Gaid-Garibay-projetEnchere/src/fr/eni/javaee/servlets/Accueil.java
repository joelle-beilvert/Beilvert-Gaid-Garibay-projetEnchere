package fr.eni.javaee.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.bll.CategorieManager;
import fr.eni.javaee.bll.EnchereManager;
import fr.eni.javaee.bo.Categorie;
import fr.eni.javaee.bo.Enchere;
import fr.eni.javaee.bo.Utilisateur;
import fr.eni.javaee.exception.GeneralException;


@WebServlet("/accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		CategorieManager catMger = new CategorieManager();
		EnchereManager enchMger = new EnchereManager();
		List<Categorie> listeCategorie = new ArrayList<Categorie>();
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		String categorieStr = "";
		String recherche = "";
		String filtre = request.getParameter("filtre");
		
		//Recupération du choix de la categorie et du mot rechercher
		if(request.getParameter("categorie")!=null) {
			categorieStr = request.getParameter("categorie");
		}
		if(request.getParameter("nomArticleContient")!=null) {
			recherche = request.getParameter("nomArticleContient");
		}
		System.out.println(categorieStr);
		System.out.println(recherche);
		// Recuperation de la liste des categories en BDD pour le select html
		listeCategorie = catMger.selectAll();
		request.setAttribute("listeCategorie", listeCategorie);
		
		
		// Si l'utilisateur n'a pas choisi de filtre supplémentaires 
		if(filtre==null || filtre.equals("encheresOuvertes")) {
			// Recuperation de la liste des encheres en cours avec eventuellement tri sur categorie et mot recherché 
			listeEnchere = enchMger.selectAllEnCours(categorieStr,recherche);
			request.setAttribute("listeEnchere", listeEnchere);
			
		}else if(filtre.equals("mesEncheres")) {
			listeEnchere = enchMger.selectMesEncheres(categorieStr,recherche,utilisateur.getNoUtilisateur());
			request.setAttribute("listeEnchere", listeEnchere);
			

			
		}else if(filtre.equals("mesVentes")) {
			listeEnchere = enchMger.selectMesVentes(categorieStr,recherche,utilisateur.getNoUtilisateur());
			request.setAttribute("listeEnchere", listeEnchere);
			
		}else if(filtre.equals("ventesNonDebutees")) {
			listeEnchere = enchMger.selectMesVentesNonDebutees(categorieStr,recherche,utilisateur.getNoUtilisateur());
			request.setAttribute("listeEnchere", listeEnchere);
			
		}else if(filtre.equals("ventesTerminees")) {
			listeEnchere = enchMger.selectMesVentesTerminees(categorieStr,recherche,utilisateur.getNoUtilisateur());
			request.setAttribute("listeEnchere", listeEnchere);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/AccueilUtilisateur.jsp");
		rd.forward(request, response);	
		}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}