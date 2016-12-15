package controller;

import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.naming.remote.client.InitialContextFactory;

import model.Livre;
import service.LivrePOJO;
import storage.Dao;
import storage.DaoJPA;
import technique.LivreManager;
import oreilly.DaoJPARemote;

public class Manager {
	private static Manager instance = new Manager();
	private Dao<?> dao = new DaoJPA<LivrePOJO>();
	public List<LivreManager> livres;
	public Livre livre;
	
	public Manager() {
		livres = new Vector<>();
		init();
		
		// TODO: INTEGRER LES FONCTIONS ajouterProduit(...)
		// DU PANIER DANS LE MANAGER
		livre = new Livre();
		livre = livres.get(1).reserverLivre();
		livres.get(1).lacherLivre();
	}
	
	public static Manager getInstance() {
		return instance;
	}
	
	public void init() {
		Dao<LivrePOJO> dao = null;
		dao = (Dao<LivrePOJO>) this.dao;

		ajouterLivres(dao);
		//readDaoEJB();

	}
	
	public void ajouterLivres(Dao<LivrePOJO> dao) {
		List<LivrePOJO> tmp = null;
		tmp = (List<LivrePOJO>) dao.selectAll();
		for(LivrePOJO lp : tmp)
		{
			livres.add(new LivreManager(lp.getId(), dao));
		}
	}
	
	public List<LivreManager> afficherLivres() {
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
