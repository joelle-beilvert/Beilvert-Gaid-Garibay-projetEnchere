package fr.eni.javaee.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.javaee.bo.ArticleVendu;
import fr.eni.javaee.bo.Categorie;
import fr.eni.javaee.bo.Enchere;
import fr.eni.javaee.bo.Utilisateur;

public class Mapping {

	public static Utilisateur mappingUtilisateur(ResultSet rs) throws SQLException {
	        Utilisateur utilisateur = new Utilisateur();
	        utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
	        utilisateur.setPseudo(rs.getString("pseudo"));
	        utilisateur.setNom(rs.getString("nom"));
	        utilisateur.setPrenom(rs.getString("prenom"));
	        utilisateur.setEmail(rs.getString("email"));
	        utilisateur.setTelephone(rs.getString("telephone"));
	        utilisateur.setRue(rs.getString("rue"));
	        utilisateur.setCodePostal(rs.getString("code_postal"));
	        utilisateur.setVille(rs.getString("ville"));
	        utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
	        utilisateur.setCredit(rs.getInt("credit"));
	        return utilisateur;
	    }
	

	public static Enchere mappingDetailEnchereSelonArticle(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Enchere mappingEnchereRemporte(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Categorie mappingCategorie(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArticleVendu mappingArticleVendu(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

}
