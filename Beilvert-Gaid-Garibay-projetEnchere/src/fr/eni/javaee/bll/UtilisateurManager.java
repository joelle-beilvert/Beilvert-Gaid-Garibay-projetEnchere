package fr.eni.javaee.bll;


import fr.eni.javaee.bo.Utilisateur;
import fr.eni.javaee.dal.UtilisateurDAO;
import fr.eni.javaee.exception.GeneralException;
import fr.eni.javaee.jdbc.UtilisateurDAOJdbcImpl;

public class UtilisateurManager {

	public Utilisateur selectionnerConnexion(String username, String password) {
		UtilisateurDAO utilisateurDAOJdbcImpl = new UtilisateurDAOJdbcImpl();
		try {
			return utilisateurDAOJdbcImpl.selectConnexion(username, password);
		} catch (GeneralException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void modifierUtilisateur(Utilisateur utilisateur) {
		
	}

}
