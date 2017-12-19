package application;

import java.util.List;

import domain.Book;
import domain.BookRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApplicationServiceImpl implements ApplicationService {
	@Autowired
	private BookRepository repository;

	public void setRepository(BookRepository repository) {
		this.repository = repository;
	}

    /**
     * This method will create random Books in database with random String instead author, name,
     * random page number and <b>random id only in this version!</b>
     * @param amount of book which will be crated
     * */
	@Transactional
	public void createRandomBooks(int amount) {
		for (int i = 0; i < amount; i++) {
			Book randomBook = new Book();
			randomBook.setAuthor(RandomString.make());
			randomBook.setName(RandomString.make());
			randomBook.setPages((int)(Math.random() * 1000.0));
			repository.createBook(randomBook);
		}
	}
    /**
     * This method will delete books from database with not Fibonacci id number
     * */
	@Transactional
	public boolean deleteEntitiesIdNotFibonacci() {
		List<Book> list = repository.getBooks();
		for (int i = 0; i < list.size(); i++) {
			Book book = list.get(i);
			if (isFiboNum(book.getId())) continue;
			else{
				if(!repository.removeBook(book))
				    return false;
			}
		}
        return true;
	}
	private boolean isFiboNum(int n){
        int x1 = 5*n*n+4;
        int x2 = 5*n*n-4;
        int jn1 = (int)Math.sqrt(x1);
        int jn2 = (int)Math.sqrt(x2);
        return jn1*jn1 == x1 || jn2*jn2 == x2;
	}
	/**
     * Allows to receive library with all books entities
     * @return List of books
     * */
	public List<Book> getLibrary() {
		return repository.getBooks();
	}

}
