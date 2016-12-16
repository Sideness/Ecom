package model;

import java.sql.Savepoint;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import service.BookPOJO;
import technique.ManagedBooks;
import technique.OutOfStockException;

public class Cart {
	// Panier doit être unique à chaque lancement
	private static Cart instance = new Cart();
	private static float deliveryCost = 23;
	private Map<ManagedBooks, Integer> items;
	private float price;
	private float total;

	// APPELER LES FONCTIONS DU LIVREMANAGER
	// POUR L'ECHANGE AVEC LA DAO
	
	// LE MODEL PANIER ACTUEL EFFECTUE UNIQUEMENT
	// LES CONTROLES D'AFFICHAGE
	
	private Cart() {
		items = new HashMap<>();
		price = 0;
		total = 0;
	}
	
	public static Cart getInstance(){
		return instance;
	}
	
	public void ajouterProduit(ManagedBooks book, int qty) throws OutOfStockException {
		if(book.checkQty(qty)){
			items.put(book, (items.get(book) == null ? qty : (items.get(book))));
		}else{
			throw new OutOfStockException();
		}
		
	}
	
	public void orderCart() throws OutOfStockException {
		//We use this boolean in order to throw only once the exception when
		//we're out of stock
		boolean stock = false;
		for (Map.Entry<ManagedBooks, Integer> entry : items.entrySet()){
			for(int i = 0 ; i < entry.getValue() ; i++){
				if(entry.getKey().saveBook()){
					stock = true;
				}
			}
		}
		if(stock){
			throw new OutOfStockException();
		}
		items.clear();
	}
	
	public void supprimerProduit(ManagedBooks book) {
		items.remove(book);
	}
	
	public float afficherTotal() {
		for (Map.Entry<ManagedBooks, Integer> entry : items.entrySet())
		{
			total += entry.getKey().getPriceByQty(entry.getValue());
		}
		
		return total;
	}
	
	@Override
	public String toString(){
		String ret = "";
		for (Map.Entry<ManagedBooks, Integer> entry : items.entrySet())
		{
			ret += "item : ";
			ret += entry.getKey().getName();
			ret += ", quantity : ";
			ret += entry.getValue();
			ret += ", prix : ";
			ret += entry.getKey().getPriceByQty(entry.getValue());
			ret += " | ";
		}
		ret += "TOTAL : " + afficherTotal();
		
		return ret;
	}
}
