package model;

public class Jeux {
	private int jeuxId;
	private String titre;
	private String description;
	private double prix;
	private java.sql.Date dateSortie;
	private String paysOrigine;
	private String connexion;
	private String mode;
	private int genreId;

	public int getJeuxId() {
		return jeuxId;
	}

	public void setJeuxId(int jeuxId) {
		this.jeuxId = jeuxId;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public java.sql.Date getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(java.sql.Date dateSortie) {
		this.dateSortie = dateSortie;
	}

	public String getPaysOrigine() {
		return paysOrigine;
	}

	public void setPaysOrigine(String paysOrigine) {
		this.paysOrigine = paysOrigine;
	}

	public String getConnexion() {
		return connexion;
	}

	public void setConnexion(String connexion) {
		this.connexion = connexion;
	}

	public String getJeuxMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	@Override
	public String toString() {
		return "Jeux [_JeuxId=" + jeuxId + ", _JeuxTitre=" + titre + ", _JeuxDescription=" + description
				+ ", _JeuxPrix=" + prix + ", _JeuxDateSortie=" + dateSortie + ", _JeuxPaysOrigine="
				+ paysOrigine + ", _JeuxConnexion=" + connexion + ", _JeuxMode=" + mode + ", _GenreId="
				+ genreId + "]";
	}

}