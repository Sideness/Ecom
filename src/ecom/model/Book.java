package ecom.model;

public class Book {
	private String name;
	private String description;
	private double price;
	private int qty;
	private String picture;

	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	public Book(String name, String description, double price, int qty, String picture) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.qty = qty;
		this.picture = picture;
	}

	public String getName() {
		return name;
	}

	public void setName(String nom) {
		this.name = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(short qty) {
		this.qty = qty;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "Livre [nom=" + name + ", description=" + description + ", prix=" + price + ", qte=" + qty + ", image="
				+ picture + "]";
	}

}
