package fr.eni.javaee.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String prenom = request.getParameter("prénom");
		request.setAttribute("prénom",prenom);
		String pseudo = request.getParameter("pseudo");
		request.setAttribute("pseudo", pseudo);
		String rue = request.getParameter("rue");
		request.setAttribute("rue", rue);
		String ville = request.getParameter("Ville");
		request.setAttribute("Ville", ville);
		String codePostale = request.getParameter("Code postale");
		request.setAttribute("Code Postale", codePostale);
		String email = request.getParameter("Email");
		request.setAttribute("Email", email);
		
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/Inscription.jsp");
		 rd.forward(request, response);
	
	}

}
