package ecom.storage;


import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class DaoJPA<T> implements Dao<T>, Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext(name="Ecom")
	private EntityManager em;
	public DaoJPA() {
		System.out.println("=============== OUTPUT Source::JPA ===============");
		//em = Persistence.createEntityManagerFactory("Ecom").createEntityManager();
	}
	
	@Override
	public T select(int id) {
		return (T) em.createNamedQuery("produit.Select").setParameter("cle",id).getSingleResult();
	}

	@Override
	public List<T> selectAll() {
		return em.createNamedQuery("produit.All").getResultList();
	}

	@Override
	public void edit(T obj) {
		em.merge(obj);

	}

	@Override
	public void delete(T obj) {
		em.remove(obj);

	}

	@Override
	public void insert(T obj) {
		em.persist(obj);
	}

}