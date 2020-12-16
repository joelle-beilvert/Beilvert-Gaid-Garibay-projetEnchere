package fr.eni.javaee.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.bll.UtilisateurManager;
import fr.eni.javaee.bo.Utilisateur;
import fr.eni.javaee.exception.GeneralException;


@WebServlet("/modifierProfil")
public class ModifierProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/modifierProfil.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String motDePasse = request.getParameter("password").trim();
		String checkMotDePasse = request.getParameter("checkPassword").trim();
		
		if(motDePasse.equals(checkMotDePasse)) {
			UtilisateurManager mger = new UtilisateurManager();
			HttpSession session = request.getSession();
			Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("utilisateur");
				
			String pseudo = request.getParameter("pseudo").trim();
			String nom = request.getParameter("nom").trim();
			String prenom = request.getParameter("prenom").trim();
			String email = request.getParameter("mail").trim();
			String telephone = request.getParameter("tel").trim();
			String rue = request.getParameter("rue").trim();
			String codePostal = request.getParameter("cpo").trim();
			String ville = request.getParameter("ville").trim();
		
			Utilisateur utilisateur = new Utilisateur(utilisateurSession.getNoUtilisateur(), pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
			mger.modifierUtilisateur(utilisateur);
			
			// On écrase l'utilisateur en session par l'utilisateur qui contient les modifs
			session.setAttribute("utilisateur", utilisateur);
			request.setAttribute("success", "Profil modifié avec succès");
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/modifierProfil.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("error", "Les mots de passe ne correspondent pas");
		}
	}

}