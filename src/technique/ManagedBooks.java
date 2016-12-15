package technique;

import model.Book;
import service.BookPOJO;
import storage.Dao;
import utilitaire.Conversion;

public class ManagedBooks {
	private int cle;
	private Book model;
	private BookPOJO pojo;
	private Dao<BookPOJO> dao;

	public ManagedBooks() {
		// TODO Auto-generated constructor stub
	}
	
	public ManagedBooks(int id, Dao<BookPOJO> d) {
		cle = id;
		dao = d;
		pojo = d.select(id);
		model = Conversion.pojoToLivre(pojo);
	}

	@Override
	public String toString() {
		return "ManagedBooks [cle=" + cle + ", model=" + model + "]";
	}
	
	public Book reserverLivre() {
		Book ret = null;
		pojo.setQte(pojo.getQte()-1);
		dao.edit(pojo);
		return ret;
	}
	
	public void lacherLivre() {
		Book ret = null;
		pojo.setQte(pojo.getQte()+1);
		dao.edit(pojo);
	}
}
