package domain;

import java.util.List;

public interface BookRepository{
	void setUp();

	void dropLibrary();

	Book getBook(Long id);
	void createBook(Book createdEntity);
	boolean removeBook(Book removEntity);
	List<Book> getBooks();
}
