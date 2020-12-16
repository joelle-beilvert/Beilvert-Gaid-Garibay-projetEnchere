package fr.eni.javaee.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.javaee.bo.Utilisateur;
import fr.eni.javaee.exception.GeneralException;
import fr.eni.javaee.jdbc.UtilisateurDAOJdbcImpl;

/**
 * Servlet implementation class ServletInscription
 */
@WebServlet("/ServletInscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/Inscription.jsp");
		 rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nom = request.getParameter("nom");
		request.setAttribute("nom", nom);
		String prenom = request.getParameter("prenom");
		request.setAttribute("prenom",prenom);
		String pseudo = request.getParameter("pseudo");
		request.setAttribute("pseudo", pseudo);
		String rue = request.getParameter("rue");
		request.setAttribute("rue", rue);
		String ville = request.getParameter("ville");
		request.setAttribute("ville", ville);
		String codePostale = request.getParameter("code-postale");
		request.setAttribute("code-postale", codePostale);
		String email = request.getParameter("email");
		request.setAttribute("email", email);
		String tel = request.getParameter("telephone");
		request.setAttribute("telephone", tel);
		String motDePasse = request.getParameter("password");
		request.setAttribute("password", motDePasse);
		
		
		Utilisateur utilisateur = new Utilisateur( pseudo, nom, prenom,  email,  tel,
				 rue,  codePostale,  ville,  motDePasse);
		UtilisateurDAOJdbcImpl utilisateurDAOJdbcImpl = new UtilisateurDAOJdbcImpl();
		try {
			utilisateurDAOJdbcImpl.insert(utilisateur);
		} catch (GeneralException e) {
			e.printStackTrace();
		}
		
		
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/Inscription.jsp");
		 rd.forward(request, response);
		 
		 
	
	}

}
