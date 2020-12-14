package fr.eni.javaee.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.eni.javaee.bll.UtilisateurManager;
import fr.eni.javaee.bo.Utilisateur;
import fr.eni.javaee.exception.GeneralException;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Utilisateur utilisateur;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie pseudo;
		Cookie mdp;

		
		HttpSession session = request.getSession();
		session.setAttribute("utilisateur", utilisateur);
		boolean etatCheckBox = request.getParameter("remember") != null;

		if (etatCheckBox == true) {
			pseudo = new Cookie("username", utilisateur.getPseudo());
			mdp = new Cookie("password", utilisateur.getMotDePasse());
			pseudo.setMaxAge(60 * 60 * 24 * 365);
			mdp.setMaxAge(60 * 60 * 24 * 365);
			response.addCookie(pseudo);
			response.addCookie(mdp);
		} else {
			pseudo = new Cookie("username", "");
			mdp = new Cookie("password", "");
			pseudo.setMaxAge(0);
			mdp.setMaxAge(0);
			response.addCookie(pseudo);
			response.addCookie(mdp);
			etatCheckBox = false;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/SeConnecter.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UtilisateurManager mger = new UtilisateurManager();

		Utilisateur utilisateurTest = mger.selectionnerConnexion(username, password);

		if (utilisateurTest==null) {
			request.setAttribute("errorLogin", "Erreur de saisie Login / MDP, veuillez réessayer");
			RequestDispatcher rd = request.getRequestDispatcher("connexion");
			rd.forward(request, response);
		} else {
			utilisateur = utilisateurTest;
			doGet(request, response);
		}
	}
}