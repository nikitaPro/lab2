package application;

import domain.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApplicationService {
	void createRandomBooks(int amount);
	boolean deleteEntitiesIdNotFibonacci();
	List<Book> getLibrary();
}
