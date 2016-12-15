package technique;

import model.Livre;
import service.LivrePOJO;
import storage.Dao;
import utilitaire.Conversion;

public class ManagedBooks {
	private int cle;
	private Livre model;
	private LivrePOJO pojo;
	private Dao<LivrePOJO> dao;

	public ManagedBooks() {
		// TODO Auto-generated constructor stub
	}
	
	public ManagedBooks(int id, Dao<LivrePOJO> d) {
		cle = id;
		dao = d;
		pojo = d.select(id);
		model = Conversion.pojoToLivre(pojo);
	}

	@Override
	public String toString() {
		return "ManagedBooks [cle=" + cle + ", model=" + model + "]";
	}
	
	public Livre reserverLivre() {
		Livre ret = null;
		pojo.setQte(pojo.getQte()-1);
		dao.edit(pojo);
		return ret;
	}
	
	public void lacherLivre() {
		Livre ret = null;
		pojo.setQte(pojo.getQte()+1);
		dao.edit(pojo);
	}
}
