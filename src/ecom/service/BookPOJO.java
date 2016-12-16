package ecom.service;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="produit")
@NamedQueries ({
	@NamedQuery(name="produit.All", query="SELECT book FROM BookPOJO book"),
	@NamedQuery(name="produit.Select", query="SELECT book FROM BookPOJO book WHERE book.id = :cle")
})
public class BookPOJO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String nom;
	private String description;
	private float prix;
	private int qte;
	private String image;

	@ManyToOne
	@JoinColumn(name="cat_id")
	private CategoryPOJO categorie;

	public BookPOJO() {
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

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public CategoryPOJO getCategorie() {
		return categorie;
	}

	public void setCategorie(CategoryPOJO categorie) {
		this.categorie = categorie;
	}


}