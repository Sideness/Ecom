package storage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DaoFactory {
	private Class<?> laClasse;
	private static DaoFactory instanceSingle = new DaoFactory();
	private DaoFactory() {
		laClasse = null;
		Properties prop = null;
		prop = new Properties();

		try {
			prop.load(new FileInputStream("dao.properties"));
			laClasse = Class.forName(prop.getProperty("package")+prop.getProperty("classe"));
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static DaoFactory getInstance(){
		return instanceSingle;
	}

	public Dao<?> getDao() {
		Dao<?> ret = null; 
		try {
			ret = (Dao<?>) laClasse.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
}
