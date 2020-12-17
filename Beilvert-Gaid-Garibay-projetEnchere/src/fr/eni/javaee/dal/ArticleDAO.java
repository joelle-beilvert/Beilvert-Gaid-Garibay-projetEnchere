package fr.eni.javaee.dal;

import fr.eni.javaee.bo.ArticleVendu;
import fr.eni.javaee.exception.GeneralException;


public interface ArticleDAO {
	
	int insertArticleRetrait(ArticleVendu article) throws GeneralException;
	
	
	int insertArticle(ArticleVendu article) throws GeneralException;
	
	
	void update(ArticleVendu article) throws GeneralException;

	
	public ArticleVendu select(int noArticle) throws GeneralException;


	void insert(ArticleVendu ArticleVendu) throws GeneralException;
}
