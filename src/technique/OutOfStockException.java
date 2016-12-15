package technique;

public class OutOfStockException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public OutOfStockException(){
		super("Nous ne pouvons satisfaire complètement votre commande : pas assez d'articles en stock.");
	}

}
