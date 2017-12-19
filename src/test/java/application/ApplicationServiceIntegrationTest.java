package application;

import domain.Book;
import domain.BookRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import presentation.AppConfig;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ApplicationServiceIntegrationTest {
    ApplicationService applicationService;

    @Before
    public void setUp(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        applicationService = applicationContext.getBean(ApplicationService.class);
        BookRepository bookRepository = (BookRepository) applicationContext.getBean(BookRepository.class);
        bookRepository.setUp();
    }
    @After
    public void clearDB() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        BookRepository bookRepository = (BookRepository) applicationContext.getBean(BookRepository.class);
        bookRepository.dropLibrary();
    }

    @Test
    public void testCreateRandomBooks(){
        // Given
        int amount = 10;
        // When
        applicationService.createRandomBooks(10);
        List<Book> library = applicationService.getLibrary();
        // Then
        assertEquals(10, library.size());
    }
    @Test
    public void testDeleteEntitiesIdNotFibonacci(){
        // Given
        applicationService.createRandomBooks(15);
        // When
        boolean ans = applicationService.deleteEntitiesIdNotFibonacci();
        List<Book> library = applicationService.getLibrary();
        // Then
        assertTrue(ans);
        int pre = 1;
        int next = 1;
        int tmp;
        assertEquals(6,library.size());
        for (Book book : library) {
            assertEquals(next,book.getId());
            tmp = next;
            next += pre;
            pre = tmp;
        }
    }

}