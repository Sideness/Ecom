package service;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="categorie")
@NamedQueries ({
	@NamedQuery(name="all", query="SELECT categorie FROM CategoriePOJO as categorie"),
	@NamedQuery(name="select", query="SELECT categorie FROM categoriePOJO categorie WHERE categorie.id = :cle")
})
public class CategoriePOJO implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id; //cle primaire
	private String nom; // nom categorie
	private String description; // description categorie
	private String image; // chemin d'acc�s image cat�gorie
	
	public CategoriePOJO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
