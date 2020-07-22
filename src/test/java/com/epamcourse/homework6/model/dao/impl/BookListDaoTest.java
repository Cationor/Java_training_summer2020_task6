package com.epamcourse.homework6.model.dao.impl;

import com.epamcourse.homework6.model.dao.storage.Stock;
import com.epamcourse.homework6.model.exception.DaoException;
import com.epamcourse.homework6.model.entity.Book;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class BookListDaoTest {
    @Mock
    private Stock stock;
    @Mock
    private Book book1;
    @Mock
    private Book book2;
    @Mock
    private Book book3;
    @InjectMocks
    private final BookListDao bookListDao = new BookListDao();

    @BeforeMethod
    public void setup() {
        List<Book> bookList = new ArrayList<>();
        MockitoAnnotations.initMocks(this);
        when(book1.getBookId()).thenReturn(1);
        when(book2.getBookId()).thenReturn(2);
        when(book3.getBookId()).thenReturn(3);

        when(book1.getName()).thenReturn("Don Quixote");
        when(book2.getName()).thenReturn("Moby Dick");
        when(book3.getName()).thenReturn("Hamlet");

        when(book1.getAuthor()).thenReturn(new String[]{"Miguel de Cervantes"});
        when(book2.getAuthor()).thenReturn(new String[]{"Herman Melville"});
        when(book3.getAuthor()).thenReturn(new String[]{"William Shakespeare"});

        when(book1.getNumberOfPage()).thenReturn(534);
        when(book2.getNumberOfPage()).thenReturn(644);
        when(book3.getNumberOfPage()).thenReturn(632);

        when(stock.getContext()).thenReturn(bookList);

        stock.getContext().add(book1);
        stock.getContext().add(book2);
        stock.getContext().add(book3);
    }

    @Test
    public void testSearchById() {
        Book expected = book2;
        Book actual = null;
        try {
            actual = bookListDao.findById(2);
        } catch (DaoException e) {
            fail();
        }

        assertEquals(expected, actual);
    }

    @Test
    public void testSearchByName() {
        Object[] expected = new Object[]{book2};
        Object[] actual = bookListDao.findByName("Moby Dick").toArray();

        assertEquals(actual, expected);
    }

    @Test
    public void testSearchByAuthor() {
        Object[] expected = new Object[]{book2};
        Object[] actual = bookListDao.findByAuthor(new String[]{"Herman Melville"}).toArray();

        assertEquals(actual, expected);
    }

    @Test
    public void testSortByName() {
        Object[] expected = new Object[]{book2, book3, book1};
        Object[] actual = bookListDao.sortByName().toArray();

        assertEquals(actual, expected);
    }

    @Test
    public void testSortByNumberOfPage() {
        Object[] expected = new Object[]{book3, book1, book2};
        Object[] actual = bookListDao.sortByNumberOfPage().toArray();

        assertEquals(actual, expected);
    }
}
