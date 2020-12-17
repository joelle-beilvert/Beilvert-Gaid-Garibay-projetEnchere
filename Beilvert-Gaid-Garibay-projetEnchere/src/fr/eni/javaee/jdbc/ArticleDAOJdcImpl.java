package fr.eni.javaee.jdbc;


	
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import fr.eni.javaee.bo.ArticleVendu;
import fr.eni.javaee.bo.Utilisateur;
import fr.eni.javaee.dal.ArticleDAO;
import fr.eni.javaee.dal.CodesResultatDAL;
	import fr.eni.javaee.dal.ConnectionProvider;
	import fr.eni.javaee.dal.UtilisateurDAO;
	import fr.eni.javaee.exception.GeneralException;


	public  class ArticleDAOJdcImpl implements ArticleDAO {

		private static final String INSERT = "INSERT INTO ARTICLES_VENDUS(nom_article, description , date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,?,?);";
		

		@Override
		public void insert(ArticleVendu article) throws GeneralException {
			  
			GeneralException be = new GeneralException();
			try 
				(Connection cnx = ConnectionProvider.getConnection()){
				// Pour prendre la main sur la transaction
				PreparedStatement psmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
				psmt.setString(1, article.getNomArticle());
				psmt.setString(2, article.getDescription());
				psmt.setTimestamp(3,Timestamp.valueOf(article.getDateDebutEncheres()));
				psmt.setTimestamp(4,Timestamp.valueOf(article.getDateFinEncheres()));
				psmt.setInt(5, article.getPrixInitial());
				psmt.setString(6, article.getPrixVente());
				psmt.setInt(7, article.getNoUtilisateur());
				 psmt.setInt(8, article.getCategorie().getNoCategorie());
				psmt.executeUpdate();
				psmt.close();
				cnx.close();
			} catch (Exception e) {
				e.printStackTrace();
				be.ajouterErreur(CodesResultatDAL.INSERT_USER_ECHEC);
				
			} finally {
				
				if (be.hasErreurs()) {
					throw be;
				}
			}

		}


		@Override
		public int insertArticleRetrait(ArticleVendu article) throws GeneralException {
			// TODO Auto-generated method stub
			return 0;
		}


		@Override
		public int insertArticle(ArticleVendu article) throws GeneralException {
			// TODO Auto-generated method stub
			return 0;
		}


		@Override
		public void update(ArticleVendu article) throws GeneralException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public ArticleVendu select(int noArticle) throws GeneralException {
			// TODO Auto-generated method stub
			return null;
		}

}
