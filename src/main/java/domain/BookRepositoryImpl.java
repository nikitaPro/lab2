package domain;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BookRepositoryImpl implements BookRepository {

	@PersistenceContext
	private EntityManager entityManager;
	/**
	 * Create book entity table.
	 */
	@Transactional
	public void setUp() {
		String query = "create table if not exists book(id serial primary key, name varchar(50), " +
				"pages varchar(50), author varchar(50));";
		String seq = "CREATE SEQUENCE IF NOT EXISTS book_id_seq\n" +
                "  INCREMENT by 1\n" +
                "  MINVALUE 1\n" +
                "  MAXVALUE 922337\n" +
                "  START with 1;";
		entityManager.createNativeQuery(query).executeUpdate();
        entityManager.createNativeQuery(seq).executeUpdate();
	}

	/**
	 * Remove entity table.
	 */
	@Transactional
	public void dropLibrary() {
		String query = "drop table book;";
        String seq = "drop SEQUENCE book_id_seq;";
		entityManager.createNativeQuery(query).executeUpdate();
        entityManager.createNativeQuery(seq).executeUpdate();
	}

	public Book getBook(Long id) {
		Book b = entityManager.find(Book.class, id);
		return b;
	}
	/**
	 * Method creates a new book
	 * @param bookEntity this book entity will be written in data base
	 * */
	public void createBook(Book bookEntity) {
		entityManager.persist(bookEntity);
	}
	/**
	 * Method deletes book
	 * @param bookEntity this book entity will be removed from data base
	 * @return true if it is ok, false in otherwise
	 * */
	public boolean removeBook(Book bookEntity) {
		if(bookEntity != null){
			entityManager.remove(bookEntity);
			return true;
		}
		return false;
	}
	/**
	 * Method reads all books from data base and return its like a <code>List&lt Book&gt</code>
	 * @return List of Books
	 * */
	@SuppressWarnings("unchecked")
	public List<Book> getBooks() {
		List<Book> books = entityManager.createQuery("SELECT b FROM Book b").getResultList();
		return books;
	}
}
