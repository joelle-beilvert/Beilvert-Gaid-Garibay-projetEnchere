package fr.eni.javaee.dal;

import java.util.List;

import fr.eni.javaee.bo.Utilisateur;
import fr.eni.javaee.exception.GeneralException;


public interface UtilisateurDAO {
	
	void insert(Utilisateur utilisateur) throws GeneralException;
	
	
	void delete(Utilisateur utilisateur) throws GeneralException;
	
	
	void update(Utilisateur utilisateur) throws GeneralException;

	
	List<Utilisateur> select() throws GeneralException;
	
	
	Utilisateur selectConnexion(String identifiant, String password) throws GeneralException;
	
	
	Utilisateur selectPseudo(String pseudo) throws GeneralException;

}