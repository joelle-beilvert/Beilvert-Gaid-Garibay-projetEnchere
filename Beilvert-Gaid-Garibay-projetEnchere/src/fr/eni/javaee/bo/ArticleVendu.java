package fr.eni.javaee.bo;



import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import fr.eni.javaee.servlets.Retrait;


public class ArticleVendu implements Serializable {
	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDateTime dateDebutEncheres;
	private LocalDateTime dateFinEncheres;
	private int prixInitial;
	private Utilisateur vendeur;
	private Utilisateur acheteur;
	private Categorie categorie;
	private Retrait retrait;

	
	public ArticleVendu() {
	}

	
	public ArticleVendu(String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int prixInitial, Utilisateur vendeur, Categorie categorie) {
		this();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.vendeur = vendeur;
		this.categorie = categorie;
	}
	
	
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int prixInitial, Utilisateur vendeur, Categorie categorie) {
		this(nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial, vendeur, categorie);
		this.noArticle = noArticle;
	}

	
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int prixInitial, Utilisateur vendeur, Utilisateur acheteur,
			Categorie categorie, Retrait retrait) {
		this(noArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial, vendeur, categorie);
		this.acheteur = acheteur;
		this.retrait = retrait;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(LocalDateTime dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public LocalDateTime getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDateTime dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public int getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}

	public Utilisateur getVendeur() {
		return vendeur;
	}

	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}

	public Utilisateur getAcheteur() {
		return acheteur;
	}

	public void setAcheteur(Utilisateur acheteur) {
		this.acheteur = acheteur;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Retrait getRetrait() {
		return retrait;
	}

	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + noArticle;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticleVendu other = (ArticleVendu) obj;
		if (noArticle != other.noArticle)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", prixInitial="
				+ prixInitial + ", vendeur=" + vendeur + ", acheteur=" + acheteur
				+ ", categorie=" + categorie + ", retrait=" + retrait + "]";
	}


	public String getPrixVente() {
		// TODO Auto-generated method stub
		return null;
	}


	public int getNoUtilisateur() {
		// TODO Auto-generated method stub
		return vendeur.getNoUtilisateur();
	}


	

}