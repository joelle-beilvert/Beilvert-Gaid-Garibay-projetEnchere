package fr.eni.javaee.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.bll.EnchereManager;
import fr.eni.javaee.bo.Enchere;
import fr.eni.javaee.bo.Utilisateur;
import fr.eni.javaee.exception.GeneralException;


@WebServlet("/AfficherDetailEnchere")
public class AfficherDetailEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EnchereManager emger;
	
   
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config); 
		emger = new EnchereManager();	
		
	}
	

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =  request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		
		LocalDateTime date = LocalDateTime.now();
		
		int noArticle = 0;

		
		if (request.getParameter("noArticle")!=null) {
			noArticle = Integer.parseInt(request.getParameter("noArticle"));
		} else { 
			noArticle = -1;
		}
		
		Enchere enchere = null;
		System.out.println("numero article :" + noArticle);
		enchere = emger.select(noArticle);
		request.setAttribute("enchere",enchere);
		
		// enchère non terminée	
		if (enchere != null && date.isBefore(enchere.getArticle().getDateFinEncheres())) {
			if (utilisateur==null) {			
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/encherir.jsp");
				rd.forward(request, response);
			} else {
				// 	utilisateur connecté mais pas le vendeur	
				if (utilisateur.getNoUtilisateur()!=enchere.getArticle().getVendeur().getNoUtilisateur()) {					
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/encherir.jsp");
					rd.forward(request, response);
				// utilisateur connecté est le vendeur					
				} else {
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/detailMaVente.jsp");
					rd.forward(request, response);
				}
			}
		// enchère terminée		
		} else{
			if (utilisateur==null) {				
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/JSP/Accueil");
				rd.forward(request, response);
			} else {
				// si l'utilisateur est l'acheteur			
				if (utilisateur.getNoUtilisateur()==enchere.getArticle().getAcheteur().getNoUtilisateur()) {				
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/acquisition.jsp");
					rd.forward(request, response);
				// l'utilisateur est le vendeur	
				} else if (utilisateur.getNoUtilisateur()==enchere.getArticle().getVendeur().getNoUtilisateur()){			
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/detailMaVenteFinEnchere.jsp"); 
					rd.forward(request, response);
				//ni l'un ni l'autre
				} else{			
				RequestDispatcher rd = request.getRequestDispatcher("accueil");
				rd.forward(request, response);
				}
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}