package model;

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
		boolean stock = false;
		for(int i = 0 ; i < qty ; i++){
			if(book.saveBook()){
				//TODO Faire un truc moins sale
				items.put(book, (items.get(book) == null ? qty : qty + (items.get(book))));
			}else{
				stock = true;
			}
		}
		if(stock){
			throw new OutOfStockException();
		}
	}
	
	public void supprimerProduit(ManagedBooks book) {
		items.remove(book);
	}
	
	public float afficherTotal() {
		for (Map.Entry<ManagedBooks, Integer> entry : items.entrySet())
		{
			total += entry.getKey().getPrice(entry.getValue());
		}
		
		return total;
	}
}
