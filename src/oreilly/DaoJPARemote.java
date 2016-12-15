package oreilly;

import java.util.List;

import service.BookPOJO;
import storage.Dao;

public interface DaoJPARemote extends Dao<BookPOJO> {
	public BookPOJO select(int id);
	public List<BookPOJO> selectAll();
	public void edit(BookPOJO obj);
	public void delete(BookPOJO obj);
	public void insert (BookPOJO obj);

}