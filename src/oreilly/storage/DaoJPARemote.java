package oreilly.storage;

import java.util.List;

import ecom.service.BookPOJO;
import ecom.storage.Dao;

public interface DaoJPARemote extends Dao<BookPOJO> {
	public BookPOJO select(int id);
	public List<BookPOJO> selectAll();
	public void edit(BookPOJO obj);
	public void delete(BookPOJO obj);
	public void insert (BookPOJO obj);
}