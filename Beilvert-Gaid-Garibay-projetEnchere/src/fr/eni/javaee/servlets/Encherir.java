package fr.eni.javaee.servlets;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.bll.ArticleManager;
import fr.eni.javaee.bll.EnchereManager;
import fr.eni.javaee.bo.ArticleVendu;
import fr.eni.javaee.bo.Enchere;
import fr.eni.javaee.bo.Utilisateur;
import fr.eni.javaee.exception.GeneralException;


@WebServlet("/encherir")
public class Encherir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("afficherDetailEnchere");
		rd.forward(request, response);	
		}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur acheteur = (Utilisateur) session.getAttribute("utilisateur");
		
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		int montantEnchere = Integer.parseInt(request.getParameter("proposition"));
		
		LocalDateTime dateEnchere = LocalDateTime.now();
		EnchereManager eMger = new EnchereManager();
		ArticleManager aMger = new ArticleManager();
		
		Enchere enchereCheck = eMger.selectByUtilisateur(acheteur.getNoUtilisateur(),noArticle);
		Enchere enchereEnCours = null;
		
		//verification qu'il n'y a pas déja une enchere de l'utilisateur	
		if (enchereCheck==null) {
			
			ArticleVendu article = aMger.select(noArticle);
			enchereEnCours = new Enchere(dateEnchere, montantEnchere, acheteur, article);
			eMger.ajouterEnchere(enchereEnCours);
			
		} else {
			enchereEnCours = new Enchere(dateEnchere, montantEnchere, acheteur, enchereCheck.getArticle());
			eMger.updateEnchere(enchereEnCours);
		}
		
		request.setAttribute("success", "L'enchère a bien été prise en compte");
		request.setAttribute("noArticle", noArticle);
		request.setAttribute("enchere", enchereEnCours);
		
		RequestDispatcher rd = request.getRequestDispatcher("afficherDetailEnchere");
		rd.forward(request, response);

	}

}