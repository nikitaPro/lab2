package application;

import domain.Book;
import domain.BookRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ApplicationServiceTest {
    @InjectMocks
    ApplicationServiceImpl applicationService;

    @Mock
    BookRepositoryImpl mockUserRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateRandomBooks(){
        // Given

        // When
        applicationService.createRandomBooks(10);
        List<Book> library = applicationService.getLibrary();
        // Then
        verify(mockUserRepository, times(10)).createBook(any(Book.class));
    }
    @Test
    public void testDeleteEntitiesIdNotFibonacci(){
        // Given
        Book userBook;
        List<Book> library = new ArrayList<Book>(12);
        userBook = new Book();
        userBook.setId(5);
        library.add(userBook);
        userBook = new Book();
        userBook.setId(8);
        library.add(userBook);
        userBook = new Book();
        userBook.setId(6);
        library.add(userBook);
        userBook = new Book();
        userBook.setId(10);
        library.add(userBook);
        // When
        when(mockUserRepository.getBooks()).thenReturn(library);
        when(mockUserRepository.removeBook(library.get(2))).thenReturn(true);
        when(mockUserRepository.removeBook(library.get(3))).thenReturn(true);
        when(mockUserRepository.removeBook(library.get(0))).thenReturn(false);
        when(mockUserRepository.removeBook(library.get(1))).thenReturn(false);
        // action
        boolean res = applicationService.deleteEntitiesIdNotFibonacci();
        // Then
        verify(mockUserRepository, times(1)).removeBook(library.get(2));
        verify(mockUserRepository, times(1)).removeBook(library.get(3));
        verify(mockUserRepository, times(0)).removeBook(library.get(0));
        verify(mockUserRepository, times(0)).removeBook(library.get(1));
        assertTrue(res);
    }
    @Test
    public void testDeleteEntitiesIdNotFibonacciRemoveErr(){
        // Given
        Book userBook;
        List<Book> library = new ArrayList<Book>(12);
        userBook = new Book();
        userBook.setId(10);
        library.add(userBook);
        userBook = new Book();
        userBook.setId(6);
        library.add(userBook);
        userBook = new Book();
        userBook.setId(8);
        library.add(userBook);
        userBook = new Book();
        userBook.setId(5);
        library.add(userBook);
        // When
        when(mockUserRepository.getBooks()).thenReturn(library);
        when(mockUserRepository.removeBook(library.get(0))).thenReturn(true);
        //must be true
        when(mockUserRepository.removeBook(library.get(1))).thenReturn(false);
        when(mockUserRepository.removeBook(library.get(2))).thenReturn(false);
        when(mockUserRepository.removeBook(library.get(3))).thenReturn(false);
        // action
        boolean res = applicationService.deleteEntitiesIdNotFibonacci();
        // Then
        verify(mockUserRepository, times(1)).removeBook(library.get(1));
        verify(mockUserRepository, times(1)).removeBook(library.get(0));
        verify(mockUserRepository, times(0)).removeBook(library.get(2));
        verify(mockUserRepository, times(0)).removeBook(library.get(3));
        assertFalse(res);
    }
}