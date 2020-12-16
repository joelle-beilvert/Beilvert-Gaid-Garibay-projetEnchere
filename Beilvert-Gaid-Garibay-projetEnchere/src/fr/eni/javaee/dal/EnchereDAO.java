package fr.eni.javaee.dal;

import java.util.List;

import fr.eni.javaee.bo.ArticleVendu;
import fr.eni.javaee.bo.Enchere;
import fr.eni.javaee.bo.Utilisateur;
import fr.eni.javaee.exception.GeneralException;


public interface EnchereDAO {
	
	void insert(Enchere enchere) throws GeneralException;
	
	
	List<Enchere> selectAllEnCours(String categorie, String recherche) throws GeneralException;

	
	Enchere select(int noArticle) throws GeneralException;
	
	
	Enchere selectVenteRemporte(int noArticle) throws GeneralException;


	
	List<Enchere> selectMesEncheres(String categorie, String recherche, int noUtilisateur) throws GeneralException;
	
	
	List<Enchere> selectMesVentesNonDebutees(String categorie, String recherche, int noUtilisateur)
			throws GeneralException;
	
	
	List<Enchere> selectMesVentes(String categorie, String recherche, int noUtilisateur) throws GeneralException;
	
	
	List<Enchere> selectMesVentesTerminees(String categorie, String recherche, int noUtilisateur)
			throws GeneralException;
	
	
	Enchere selectByUtilisateur(int noUtilisateur,int noArticle) throws GeneralException;

	
	void updateEnchere(Enchere enchere) throws GeneralException;

	

	

	

}