package controller;

import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.naming.remote.client.InitialContextFactory;

import model.Book;
import service.BookPOJO;
import storage.Dao;
import storage.DaoFactory;
import storage.DaoJPA;
import technique.ManagedBooks;
import oreilly.DaoJPARemote;

public class Manager {
	private static Manager instance = new Manager();
	private Dao<?> dao = new DaoJPA<BookPOJO>();
	public List<ManagedBooks> livres;
	public Book livre;
	
	public Manager() {
		livres = new Vector<>();
		init();
		
		// TODO: INTEGRER LES FONCTIONS ajouterProduit(...)
		// DU PANIER DANS LE MANAGER
		livre = new Book();
		livre = livres.get(1).reserverLivre();
		livres.get(1).lacherLivre();
	}
	
	public static Manager getInstance() {
		return instance;
	}
	
	public void init() {
		Dao<BookPOJO> dao = null;
		dao = (Dao<BookPOJO>) DaoFactory.getInstance().getDao();

		ajouterLivres(dao);
		//readDaoEJB();

	}
	
	public void ajouterLivres(Dao<BookPOJO> dao) {
		List<BookPOJO> tmp = null;
		tmp = (List<BookPOJO>) dao.selectAll();
		for(BookPOJO lp : tmp)
		{
			livres.add(new ManagedBooks(lp.getId(), dao));
		}
	}
	
	public List<ManagedBooks> afficherLivres() {
		return livres;
	}
	
	private void readDaoEJB() {
		DaoJPARemote dao = null;
		InitialContext contexte = null;
		Properties env = null;
		
		try {
			env = new Properties();
		    env.put("jboss.naming.client.ejb.context", true); 
		    env.put(Context.INITIAL_CONTEXT_FACTORY, InitialContextFactory.class.getName());
			env.put(Context.PROVIDER_URL, "http-remoting://localhost:8081");
			contexte = new InitialContext(env);
			dao = (DaoJPARemote) contexte.lookup("//oreillyDS/DaoJPA!oreilly.DaoJPARemote");
			ajouterLivres(dao);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
