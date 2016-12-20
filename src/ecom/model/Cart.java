package ecom.model;

import java.util.HashMap;
import java.util.Map;

import ecom.technique.ManagedBooks;
import ecom.technique.OutOfStockException;

public class Cart {
	private static Cart instance = new Cart();
	private static float deliveryCost = 23;
	private Map<ManagedBooks, Integer> items;
	private float total;
	
	private Cart() {
		items = new HashMap<>();
		total = 0;
	}
	
	public static Cart getInstance(){
		return instance;
	}
	
	public void addToCart(ManagedBooks book, int qty) throws OutOfStockException {
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
		System.out.println("Livres commandés. Panier vidé.");
	}
	
	public void removeBook(ManagedBooks book) {
		items.remove(book);
		System.out.println(book.getName() + " supprimé du panier");
	}
	
	public Map<ManagedBooks, Integer> getCart(){
		return items;
	}
	
	public float getTotalPriceDelivery() {
		total = 0;
		for (Map.Entry<ManagedBooks, Integer> entry : items.entrySet())
		{
			total += entry.getKey().getPriceByQty(entry.getValue());
		}
		
		return total + deliveryCost;
	}
	
	public float getTotalPrice() {
		total = 0;
		for (Map.Entry<ManagedBooks, Integer> entry : items.entrySet())
		{
			total += entry.getKey().getPriceByQty(entry.getValue());
		}
		
		return total;
	}
	
	public float getDelivery(){
		return deliveryCost;
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
		ret += "TOTAL : " + getTotalPriceDelivery();
		
		return ret;
	}
}
