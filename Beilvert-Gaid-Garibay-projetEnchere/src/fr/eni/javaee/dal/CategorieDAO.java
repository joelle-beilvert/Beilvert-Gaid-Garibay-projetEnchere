package fr.eni.javaee.dal;

import java.util.List;

import fr.eni.javaee.bo.Categorie;
import fr.eni.javaee.exception.GeneralException;


public interface CategorieDAO {	
	
	List<Categorie> selectAll() throws GeneralException;

	
	void insert(Categorie categorie) throws GeneralException;
}




