package presentation;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import application.ApplicationService;
import domain.Book;

public class Main {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		ApplicationService bookserv = applicationContext.getBean(ApplicationService.class);

		bookserv.createRandomBooks(100);
		bookserv.deleteEntitiesIdNotFibonacci();
		List<Book> library = bookserv.getLibrary();
		for (Book book : library) {
			System.out.println(book);
		}
	}
}
