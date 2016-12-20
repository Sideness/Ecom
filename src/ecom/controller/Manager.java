package ecom.controller;

import java.util.List;
import java.util.Vector;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import ecom.model.Cart;
import ecom.service.BookPOJO;
import ecom.storage.Dao;
import ecom.technique.ManagedBooks;
import ecom.technique.OutOfStockException;
import oreilly.storage.DaoJPARemote;

public class Manager {
	private static Manager instance = new Manager();
	public List<ManagedBooks> books;
	public ManagedBooks book;
	private Dao<BookPOJO> dao;
	private int rank;

	public Manager() {
		books = new Vector<>();
		rank = 0;
		init();
	}

	public static Manager getInstance() {
		return instance;
	}

	public void init() {
		//dao = null;
		try {
			dao = InitialContext.doLookup("java:module/DaoJPA!ecom.storage.DaoJPA");
			//dao = (Dao<BookPOJO>) DaoFactory.getInstance().getDao();
			addBooks(dao);
			readDaoEJB();

			/*for (int i = 0; i < books.size(); i++) {
				book = books.get(i);
				addToCart(book, 1);
			}
			System.out.println(Cart.getInstance().toString());
			try {
				Cart.getInstance().orderCart();
			} catch (OutOfStockException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e){
			//Todo handle exception
			e.printStackTrace();
		}

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
		for (BookPOJO lp : tmp) {
			books.add(new ManagedBooks(lp.getId(), dao, rank));
			rank++;
		}
		System.out.println("Livres Managés créés");
	}

	public List<ManagedBooks> displayBooks() {
		return books;
	}

	private void readDaoEJB() {
		DaoJPARemote dao = null;
		try {
			dao = InitialContext.doLookup("java:global/oreilly/DaoJPA!oreilly.storage.DaoJPARemote");
			addBooks(dao);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
