package fr.eni.javaee.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.bo.ArticleVendu;
import fr.eni.javaee.bo.Enchere;
import fr.eni.javaee.bo.Utilisateur;
import fr.eni.javaee.dal.CodesResultatDAL;
import fr.eni.javaee.dal.ConnectionProvider;
import fr.eni.javaee.dal.EnchereDAO;
import fr.eni.javaee.exception.GeneralException;


public class EnchereDAOJdbcImpl implements EnchereDAO{
	private static final String INSERT = "INSERT INTO ENCHERES(no_utilisateur,no_article,date_enchere,montant_enchere) VALUES (?,?,?,?);";
	private static final String SELECT_VENTE_REMPORTE = "SELECT av.no_article, av.nom_article, av.prix_initial, r.rue as rue_retrait, r.code_postal as CPO_retrait, r.ville as ville_retrait, vendeur.no_utilisateur as vendeur_id, vendeur.pseudo as vendeur_pseudo, vendeur.telephone, vendeur.rue as rue_vendeur, vendeur.code_postal as CPO_vendeur, vendeur.ville as ville_vendeur, u.no_utilisateur, u.pseudo as pseudo_max, MAX(e.montant_enchere) as val_max FROM ENCHERES e JOIN ARTICLES_VENDUS av ON e.no_article = av.no_article JOIN UTILISATEURS u ON av.no_acheteur = u.no_utilisateur JOIN UTILISATEURS vendeur ON av.no_vendeur = vendeur.no_utilisateur JOIN RETRAITS r ON r.no_article = av.no_article GROUP BY av.no_article, u.no_utilisateur, u.pseudo WHERE av.no_article = ?;";;
	private static final String SELECT_AVEC_MEILLEURE_OFFRE = "SELECT TOP 1\r\n" + 
			"	vme.enchere_max, vme.pseudo as acheteur_pseudo, vme.no_utilisateur as acheteur_id, vme.email as acheteur_email,\r\n" + 
			"	av.nom_article, av.no_article, av.description, av.prix_initial, av.date_debut_encheres, av.date_fin_encheres, c.no_categorie, c.libelle,\r\n" + 
			"	r.rue as r_rue,	r.ville as r_ville,	r.code_postal as r_code_postal,	vendeur.pseudo as vendeur_pseudo,\r\n" + 
			"	vendeur.no_utilisateur as vendeur_id, vendeur.rue as vendeur_rue, vendeur.ville as vendeur_ville, vendeur.code_postal as vendeur_code_postal,\r\n" + 
			"	vendeur.telephone as vendeur_telephone\r\n" +
			"	FROM ARTICLES_VENDUS av\r\n" + 
			"	LEFT JOIN RETRAITS r ON av.no_article = r.no_article\r\n" + 
			"	JOIN UTILISATEURS vendeur ON av.no_vendeur = vendeur.no_utilisateur \r\n" + 
			"	JOIN CATEGORIES c ON c.no_categorie = av.no_categorie \r\n" + 
			"	LEFT JOIN \r\n" + 
			"	(SELECT \r\n" + 
			"			MAX(e.montant_enchere) as enchere_max, av.no_article, u.pseudo,	u.no_utilisateur, u.email\r\n" + 
			"		    FROM ENCHERES e \r\n" + 
			"			JOIN ARTICLES_VENDUS av ON e.no_article = av.no_article\r\n" + 
			"			JOIN UTILISATEURS u ON u.no_utilisateur = e.no_utilisateur\r\n" + 
			"		  GROUP BY 	av.no_article,u.pseudo,u.no_utilisateur, u.email)\r\n" + 
			"	vme ON vme.no_article = av.no_article\r\n" + 
			"	WHERE av.no_article = ? ORDER BY vme.enchere_max DESC;";

	private static final String SELECT_ENCHERES_CONTAINS_BY_CATEGORIE = "SELECT DISTINCT a.nom_article, a.no_article, a.date_fin_encheres, a.prix_initial, u.pseudo, u.no_utilisateur as no_vendeur, u.rue as rue_vendeur, u.code_postal as code_postal_vendeur, u.ville as ville_vendeur, r.rue as rue_retrait, r.ville as ville_retrait, r.code_postal as code_postal_retrait, vme.val_max, c.libelle FROM ARTICLES_VENDUS a JOIN UTILISATEURS u ON u.no_utilisateur = a.no_vendeur JOIN CATEGORIES c ON a.no_categorie = c.no_categorie LEFT JOIN ENCHERES e ON e.no_article = a.no_article LEFT JOIN RETRAITS r ON a.no_article = r.no_article LEFT JOIN (SELECT MAX(e.montant_enchere) as val_max, av.no_article FROM ENCHERES e JOIN ARTICLES_VENDUS av ON e.no_article = av.no_article GROUP BY av.no_article) vme ON vme.no_article = a.no_article WHERE c.libelle LIKE ? and nom_article LIKE ? and a.date_debut_encheres < GETDATE() and a.date_fin_encheres > GETDATE();";
	private static final String SELECT_MES_ENCHERES = "SELECT DISTINCT a.nom_article, a.no_article, a.date_fin_encheres, a.prix_initial, u.pseudo, u.no_utilisateur as no_vendeur, u.rue as rue_vendeur, u.code_postal as code_postal_vendeur, u.ville as ville_vendeur, r.rue as rue_retrait, r.ville as ville_retrait, r.code_postal as code_postal_retrait, vme.val_max, c.libelle FROM ARTICLES_VENDUS a JOIN UTILISATEURS u ON u.no_utilisateur = a.no_vendeur JOIN CATEGORIES c ON a.no_categorie = c.no_categorie LEFT JOIN ENCHERES e ON e.no_article = a.no_article LEFT JOIN RETRAITS r ON a.no_article = r.no_article LEFT JOIN (SELECT MAX(e.montant_enchere) as val_max, av.no_article FROM ENCHERES e JOIN ARTICLES_VENDUS av ON e.no_article = av.no_article GROUP BY av.no_article) vme ON vme.no_article = a.no_article WHERE c.libelle LIKE ? and nom_article LIKE ? and e.no_utilisateur = ? and a.date_debut_encheres < GETDATE() and a.date_fin_encheres > GETDATE();";
	//private static final String SELECT_ENCHERES_REMPORTEES = "SELECT DISTINCT a.nom_article, a.no_article, a.date_fin_encheres, a.prix_initial, u.pseudo, u.no_utilisateur as no_vendeur, u.rue as rue_vendeur, u.code_postal as code_postal_vendeur, u.ville as ville_vendeur, r.rue as rue_retrait, r.ville as ville_retrait, r.code_postal as code_postal_retrait, vme.val_max, c.libelle FROM ARTICLES_VENDUS a JOIN UTILISATEURS u ON u.no_utilisateur = a.no_vendeur JOIN CATEGORIES c ON a.no_categorie = c.no_categorie LEFT JOIN ENCHERES e ON e.no_article = a.no_article LEFT JOIN RETRAITS r ON a.no_article = r.no_article LEFT JOIN (SELECT MAX(e.montant_enchere) as val_max, av.no_article FROM ENCHERES e JOIN ARTICLES_VENDUS av ON e.no_article = av.no_article GROUP BY av.no_article) vme ON vme.no_article = a.no_article WHERE a.date_debut_encheres < GETDATE() and c.libelle LIKE ? and nom_article LIKE ?;";
	private static final String SELECT_MES_VENTES_EN_COURS = "SELECT DISTINCT a.nom_article, a.no_article, a.date_fin_encheres, a.prix_initial, u.pseudo, u.no_utilisateur as no_vendeur, u.rue as rue_vendeur, u.code_postal as code_postal_vendeur, u.ville as ville_vendeur, r.rue as rue_retrait, r.ville as ville_retrait, r.code_postal as code_postal_retrait, vme.val_max, c.libelle FROM ARTICLES_VENDUS a JOIN UTILISATEURS u ON u.no_utilisateur = a.no_vendeur JOIN CATEGORIES c ON a.no_categorie = c.no_categorie LEFT JOIN ENCHERES e ON e.no_article = a.no_article LEFT JOIN RETRAITS r ON a.no_article = r.no_article LEFT JOIN (SELECT MAX(e.montant_enchere) as val_max, av.no_article FROM ENCHERES e JOIN ARTICLES_VENDUS av ON e.no_article = av.no_article GROUP BY av.no_article) vme ON vme.no_article = a.no_article WHERE c.libelle LIKE ? and nom_article LIKE ? and a.no_vendeur = ? and a.date_debut_encheres < GETDATE() and a.date_fin_encheres > GETDATE();";
	private static final String SELECT_MES_VENTES_NON_DEBUTEES = "SELECT DISTINCT a.nom_article, a.no_article, a.date_fin_encheres, a.prix_initial, u.pseudo, u.no_utilisateur as no_vendeur, u.rue as rue_vendeur, u.code_postal as code_postal_vendeur, u.ville as ville_vendeur, r.rue as rue_retrait, r.ville as ville_retrait, r.code_postal as code_postal_retrait, vme.val_max, c.libelle FROM ARTICLES_VENDUS a JOIN UTILISATEURS u ON u.no_utilisateur = a.no_vendeur JOIN CATEGORIES c ON a.no_categorie = c.no_categorie LEFT JOIN ENCHERES e ON e.no_article = a.no_article LEFT JOIN RETRAITS r ON a.no_article = r.no_article LEFT JOIN (SELECT MAX(e.montant_enchere) as val_max, av.no_article FROM ENCHERES e JOIN ARTICLES_VENDUS av ON e.no_article = av.no_article GROUP BY av.no_article) vme ON vme.no_article = a.no_article WHERE c.libelle LIKE ? and nom_article LIKE ? and a.no_vendeur = ? and a.date_debut_encheres > GETDATE();";
	private static final String SELECT_MES_VENTES_TERMINEES = "SELECT DISTINCT a.nom_article, a.no_article, a.date_fin_encheres, a.prix_initial, u.pseudo, u.no_utilisateur as no_vendeur, u.rue as rue_vendeur, u.code_postal as code_postal_vendeur, u.ville as ville_vendeur, r.rue as rue_retrait, r.ville as ville_retrait, r.code_postal as code_postal_retrait, vme.val_max, c.libelle FROM ARTICLES_VENDUS a JOIN UTILISATEURS u ON u.no_utilisateur = a.no_vendeur JOIN CATEGORIES c ON a.no_categorie = c.no_categorie LEFT JOIN ENCHERES e ON e.no_article = a.no_article LEFT JOIN RETRAITS r ON a.no_article = r.no_article LEFT JOIN (SELECT MAX(e.montant_enchere) as val_max, av.no_article FROM ENCHERES e JOIN ARTICLES_VENDUS av ON e.no_article = av.no_article GROUP BY av.no_article) vme ON vme.no_article = a.no_article WHERE c.libelle LIKE ? and nom_article LIKE ? and a.no_vendeur = ? AND a.date_fin_encheres < GETDATE();";
	private static final String UPDATE_BY_UTILISATEUR_ARTICLE = "UPDATE ENCHERES SET montant_enchere=?, date_enchere=? WHERE no_article=? AND no_utilisateur=?;";
	private static final String SELECT_BY_UTILISATEUR ="SELECT \r\n" + 
			"av.no_article, \r\n" + 
			"av.date_debut_encheres, \r\n" + 
			"av.date_fin_encheres, \r\n" + 
			"acheteur.no_utilisateur\r\n" + 
			"FROM ENCHERES e \r\n" + 
			"JOIN UTILISATEURS acheteur ON acheteur.no_utilisateur = e.no_utilisateur\r\n" + 
			"JOIN ARTICLES_VENDUS av ON av.no_article = e.no_article\r\n" + 
			"WHERE acheteur.no_utilisateur = ? AND av.no_article = ?;";
	
			
	
	@Override
	public void insert(Enchere enchere) throws GeneralException {
		Connection cnx = null;
		GeneralException be = new GeneralException();
		try {
			cnx = ConnectionProvider.getConnection();
			PreparedStatement psmt = cnx.prepareStatement(INSERT);
			psmt.setInt(1, enchere.getUtilisateur().getNoUtilisateur());
			psmt.setInt(2, enchere.getArticle().getNoArticle());
			psmt.setTimestamp(3, Timestamp.valueOf(enchere.getDateEnchere()));
			psmt.setInt(4, enchere.getMontantEnchere());
			psmt.executeUpdate();
			psmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			be.ajouterErreur(CodesResultatDAL.INSERT_ENCHERE_ECHEC);
		} finally {
			try {
				cnx.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (be.hasErreurs()) {
				throw be;
			}
		}

	}

	
	@Override
	public Enchere select(int noArticle) throws GeneralException {
		GeneralException be = new GeneralException();
		try (Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement psmt = cnx.prepareStatement(SELECT_AVEC_MEILLEURE_OFFRE);) {
			psmt.setInt(1, noArticle);
			ResultSet rs = psmt.executeQuery();
			Enchere enchere = null;
			if (rs.next()) {
				enchere = Mapping.mappingDetailEnchereSelonArticle(rs);
			}
			rs.close();
			psmt.close();
			return enchere;
		}catch(SQLException e){
			e.printStackTrace();
			be.ajouterErreur(CodesResultatDAL.SELECT_MAX_ENCHERE_ECHEC);
			throw be;
		}
	}
	
	
	@Override
	public Enchere selectVenteRemporte(int noArticle) throws GeneralException {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement psmt = cnx.prepareStatement(SELECT_VENTE_REMPORTE);) {
			psmt.setInt(1, noArticle);
			ResultSet rs = psmt.executeQuery();
			Enchere enchereRemporte = null;
			if (rs.next()) {
				enchereRemporte = Mapping.mappingEnchereRemporte(rs);
			}
			rs.close();
			psmt.close();
			return enchereRemporte;
		} catch (SQLException e) {
			e.printStackTrace();
			GeneralException be = new GeneralException();
			be.ajouterErreur(CodesResultatDAL.SELECT_VENTE_REMPORTE_ECHEC);
			throw be;
		}
	}

	
	@Override
	public List<Enchere> selectAllEnCours(String categorie, String recherche) throws GeneralException {
		GeneralException be = new GeneralException();
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		Enchere enchere = null;
		try (Connection cnx = ConnectionProvider.getConnection(); PreparedStatement psmt = cnx.prepareStatement(SELECT_ENCHERES_CONTAINS_BY_CATEGORIE);) {
			psmt.setString(1, "%"+categorie+"%");
			psmt.setString(2, "%"+recherche+"%");
				ResultSet rs = psmt.executeQuery();
				while (rs.next()) {
					enchere = Mapping.mappingEnchereRemporte(rs);
					listeEnchere.add(enchere);
				}
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
				be.ajouterErreur(CodesResultatDAL.SELECT_ENCHERES);
				throw be;
			}
		return listeEnchere;
	}
	

	
	@Override
	public List<Enchere> selectMesEncheres(String categorie, String recherche, int noUtilisateur) throws GeneralException {
		GeneralException be = new GeneralException();
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		Enchere enchere = null;
		try (Connection cnx = ConnectionProvider.getConnection(); PreparedStatement psmt = cnx.prepareStatement(SELECT_MES_ENCHERES);) {
			psmt.setString(1, "%"+categorie+"%");
			psmt.setString(2, "%"+recherche+"%");
			psmt.setInt(3, noUtilisateur);
				ResultSet rs = psmt.executeQuery();
				while (rs.next()) {
					enchere = Mapping.mappingEnchereRemporte(rs);
					listeEnchere.add(enchere);
				}
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
				be.ajouterErreur(CodesResultatDAL.SELECT_ENCHERES);
				throw be;
			}
		return listeEnchere;
	}
	
	
	@Override
	public List<Enchere> selectMesVentes(String categorie, String recherche, int noUtilisateur) throws GeneralException {
		GeneralException be = new GeneralException();
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		Enchere enchere = null;
		try (Connection cnx = ConnectionProvider.getConnection(); PreparedStatement psmt = cnx.prepareStatement(SELECT_MES_VENTES_EN_COURS);) {
			psmt.setString(1, "%"+categorie+"%");
			psmt.setString(2, "%"+recherche+"%");
			psmt.setInt(3, noUtilisateur);
				ResultSet rs = psmt.executeQuery();
				while (rs.next()) {
					enchere = Mapping.mappingEnchereRemporte(rs);
					listeEnchere.add(enchere);
				}
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
				be.ajouterErreur(CodesResultatDAL.SELECT_ENCHERES);
				throw be;
			}
		return listeEnchere;
	}
	
	
	
	@Override
	public List<Enchere> selectMesVentesNonDebutees(String categorie, String recherche, int noUtilisateur) throws GeneralException {
		GeneralException be = new GeneralException();
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		Enchere enchere = null;
		try (Connection cnx = ConnectionProvider.getConnection(); PreparedStatement psmt = cnx.prepareStatement(SELECT_MES_VENTES_NON_DEBUTEES);) {
			psmt.setString(1, "%"+categorie+"%");
			psmt.setString(2, "%"+recherche+"%");
			psmt.setInt(3, noUtilisateur);
				ResultSet rs = psmt.executeQuery();
				while (rs.next()) {
					enchere = Mapping.mappingEnchereRemporte(rs);
					listeEnchere.add(enchere);
				}
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
				be.ajouterErreur(CodesResultatDAL.SELECT_ENCHERES);
				throw be;
			}
		return listeEnchere;
	}
	
	
	@Override
	public List<Enchere> selectMesVentesTerminees(String categorie, String recherche, int noUtilisateur) throws GeneralException {
		GeneralException be = new GeneralException();
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		Enchere enchere = null;
		try (Connection cnx = ConnectionProvider.getConnection(); PreparedStatement psmt = cnx.prepareStatement(SELECT_MES_VENTES_TERMINEES);) {
			psmt.setString(1, "%"+categorie+"%");
			psmt.setString(2, "%"+recherche+"%");
			psmt.setInt(3, noUtilisateur);
				ResultSet rs = psmt.executeQuery();
				while (rs.next()) {
					enchere = Mapping.mappingEnchereRemporte(rs);
					listeEnchere.add(enchere);
				}
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
				be.ajouterErreur(CodesResultatDAL.SELECT_ENCHERES);
				throw be;
			}
		return listeEnchere;
	}
	

	
	@Override
	public void updateEnchere(Enchere enchere) throws GeneralException {
		Connection cnx = null;
		GeneralException be = new GeneralException();
		try {
			cnx = ConnectionProvider.getConnection();
			// Pour prendre la main sur la transaction
			PreparedStatement psmt = cnx.prepareStatement(UPDATE_BY_UTILISATEUR_ARTICLE);
			psmt.setInt(1, enchere.getMontantEnchere());
			psmt.setTimestamp(2, Timestamp.valueOf(enchere.getDateEnchere()));
			psmt.setInt(3, enchere.getArticle().getNoArticle());
			psmt.setInt(4, enchere.getUtilisateur().getNoUtilisateur());
			psmt.executeUpdate();
			psmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			be.ajouterErreur(CodesResultatDAL.UPDATE_UTILISATEUR_ECHEC); //*TODO erreur à refaire, pas la bonne
		} finally {
			try {
				cnx.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	
	@Override
	public Enchere selectByUtilisateur(int noUtilisateur,int noArticle) throws GeneralException {
		try (Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement psmt = cnx.prepareStatement(SELECT_BY_UTILISATEUR);) {
			
			psmt.setInt(1, noUtilisateur);
			psmt.setInt(2, noArticle);
			ResultSet rs = psmt.executeQuery();
			Enchere enchere = null;
			if (rs.next()) {
				Utilisateur acheteur = new Utilisateur();
				acheteur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				ArticleVendu article = new ArticleVendu();
				article.setNoArticle(rs.getInt("no_article"));
				article.setDateDebutEncheres(rs.getTimestamp("date_debut_encheres").toLocalDateTime());
		        article.setDateFinEncheres(rs.getTimestamp("date_fin_encheres").toLocalDateTime());
		       
		        enchere=new Enchere();
		        enchere.setUtilisateur(acheteur);
		        enchere.setArticle(article);
			}
			rs.close();
			psmt.close();
			return enchere;
		} catch (SQLException e) {
			e.printStackTrace();
			GeneralException be = new GeneralException();
			be.ajouterErreur(CodesResultatDAL.SELECT_LOGIN_ECHEC);//*TODO refaire l'erreur, pas la bonne
			throw be;
		}

	}
	
}