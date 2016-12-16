package controller;

import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.stream.IntStream;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.naming.remote.client.InitialContextFactory;

import model.Book;
import model.Cart;
import service.BookPOJO;
import storage.Dao;
import storage.DaoFactory;
import storage.DaoJPA;
import technique.ManagedBooks;
import technique.OutOfStockException;
import oreilly.DaoJPARemote;

public class Manager {
	private static Manager instance = new Manager();
	private Dao<?> dao = new DaoJPA<BookPOJO>();
	public List<ManagedBooks> books;
	public ManagedBooks book;;
	
	public Manager() {
		books = new Vector<>();
		init();
	}
	
	public static Manager getInstance() {
		return instance;
	}
	
	public void init() {
		Dao<BookPOJO> dao = null;
		dao = (Dao<BookPOJO>) DaoFactory.getInstance().getDao();

		addBooks(dao);
		
		for (int i = 0 ; i < books.size() ; i++){
			book = books.get(i);
			addToCart(book, 1);
		}
		System.out.println(Cart.getInstance().toString());
		try {
			Cart.getInstance().orderCart();
		} catch (OutOfStockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		books = new Vector<>();
		System.out.println("Livres managés réinitialisés");
		addBooks(dao);
	}
	
	public void addToCart(ManagedBooks book, int qty) {
		try {
			Cart.getInstance().ajouterProduit(book, qty);
			System.out.println(book.getName() + " ajouté au panier.");
		} catch (OutOfStockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addBooks(Dao<BookPOJO> dao) {
		List<BookPOJO> tmp = null;
		tmp = (List<BookPOJO>) dao.selectAll();
		for(BookPOJO lp : tmp)
		{
			books.add(new ManagedBooks(lp.getId(), dao));
		}
		System.out.println("Livres Managés créés");
	}
	
	public List<ManagedBooks> afficherLivres() {
		return books;
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
			addBooks(dao);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
