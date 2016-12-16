package ecom.technique;

import ecom.model.Book;
import ecom.service.BookPOJO;
import ecom.storage.Dao;
import ecom.utilitaire.Conversion;

public class ManagedBooks {
	private int id;
	private Book model;
	private BookPOJO pojo;
	private Dao<BookPOJO> dao;

	public ManagedBooks() {
		// TODO Auto-generated constructor stub
	}
	
	public ManagedBooks(int id, Dao<BookPOJO> d) {
		this.id = id;
		this.dao = d;
		this.pojo = d.select(id);
		this.model = Conversion.pojoToLivre(pojo);
	}

	@Override
	public String toString() {
		return "ManagedBooks [cle=" + id + ", model=" + model + "]";
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
	
	public String getName(){
		return pojo.getNom();
	}
	
	public boolean checkQty(int qty){
		return (pojo.getQte() >= qty);
	}
}
