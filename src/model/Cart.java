package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import service.BookPOJO;

public class Cart {
	// Panier doit être unique à chaque lancement
	private static Cart instance = new Cart();
	private static float deliveryCost = 23;
	private Map<BookPOJO, Integer> cart;
	private float price;
	private float total;

	// APPELER LES FONCTIONS DU LIVREMANAGER
	// POUR L'ECHANGE AVEC LA DAO
	
	// LE MODEL PANIER ACTUEL EFFECTUE UNIQUEMENT
	// LES CONTROLES D'AFFICHAGE
	
	public Cart() {
		cart = new HashMap<>();
		price = 0;
		total = 0;
	}
	
	public void ajouterProduit(BookPOJO livre) {
		// TODO: Tester qu'on en ajoute pas + que la limite
		if(cart.containsKey(livre.getId())) {
			cart.put(livre, cart.get(livre)+1);
		}
		else {
			cart.put(livre, 1);
		}
	}
	
	public void supprimerProduit(Book livre) {
		cart.remove(livre, 1);
	}
	
	public float afficherTotal() {
		for ( Entry<BookPOJO, Integer> entry : cart.entrySet()) {
            Integer qte = entry.getValue();
            price = entry.getKey().getPrix();
            total += price * qte;
        }
		total += deliveryCost;
		
		return total;
	}
}
