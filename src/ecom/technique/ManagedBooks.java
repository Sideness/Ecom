package ecom.technique;

import ecom.model.Book;
import ecom.service.BookPOJO;
import ecom.storage.Dao;
import ecom.utilitaire.Conversion;

public class ManagedBooks {
	private int key;
	private Book model;
	private BookPOJO pojo;
	private Dao<BookPOJO> dao;

	public ManagedBooks() {
		// TODO Auto-generated constructor stub
	}
	
	public ManagedBooks(int id, Dao<BookPOJO> d, int rank) {
		this.key = rank;
		this.dao = d;
		this.pojo = d.select(id);
		this.model = Conversion.pojoToLivre(pojo);
	}

	@Override
	public String toString() {
		return "ManagedBooks [cle=" + key + ", model=" + model + "]";
	}
	
	public boolean saveBook() {
		if(pojo.getQte() >= 0){
			pojo.setQte(pojo.getQte()-1);
		}else{
			return false;
		}
		dao.edit(pojo);
		return true;
	}
	
	public void freeBook() {
		pojo.setQte(pojo.getQte()+1);
		dao.edit(pojo);
	}
	
	public float getPriceByQty(int qty){
		return pojo.getPrix() * qty;
	}
	
	public float getPrice(){
		return pojo.getPrix();
	}
	
	public String getName(){
		return pojo.getNom();
	}
	
	public int getId(){
		return pojo.getId();
	}
	
	public boolean checkQty(int qty){
		return (pojo.getQte() >= qty);
	}
	
	public String getImage(){
		return pojo.getImage();
	}
	
	public String getDescription(){
		return pojo.getDescription();
	}

	public int getKey() {
		return key;
	}
}
