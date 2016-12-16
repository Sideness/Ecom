package ecom.utilitaire;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import ecom.model.Book;
import ecom.service.BookPOJO;

public abstract class Conversion {
	private static final String PAQ = "model.";
	public static Book pojoToLivre(BookPOJO lp)
	{	
		Book ret = null;
		Class<?> classToResolve = null;
		Book book = null;
		Constructor<?> constructor = null;
		Class<?> types[] = null;
		Object []values = null;
		
		ret = new Book();

		if(lp.getNom() != null)
		{
			types = new Class<?>[5];
			values = new Object[5];

			types[0] = String.class;
			types[1] = String.class;
			types[2] = double.class;
			types[3] = int.class;
			types[4] = String.class;

			values[0] = lp.getNom();
			values[1] = lp.getDescription();
			values[2] = lp.getPrix();
			values[3] = lp.getQte();
			values[4] = lp.getImage();
			
			try 
			{
				classToResolve = Class.forName("model.Book");
				constructor = classToResolve.getConstructor(types);
				book = (Book) constructor.newInstance(values);
				System.out.println(book);
			} 
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return ret;
	}
}
