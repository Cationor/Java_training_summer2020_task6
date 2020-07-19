package com.epamcourse.homework6.controller.invoker;

import com.epamcourse.homework6.model.dao.impl.BookListDao;
import com.epamcourse.homework6.model.entity.Book;
import com.epamcourse.homework6.model.exception.DaoException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;
import static org.testng.FileAssert.fail;

public class RequestProcessorTest {
    private final RequestProcessor requestProcessor = new RequestProcessor();
    private final BookListDao bookListDao = new BookListDao();

    @BeforeClass
    public void init() {
        bookListDao.addBook(new Book(1, "JAVA_Methods_Prog", 400, "Blinov", "Romanchic"));
        bookListDao.addBook(new Book(2, "War and peace", 600, "Tolstoi"));
        bookListDao.addBook(new Book(3, "Bible", 349, "Mystery"));
        bookListDao.addBook(new Book(4, "Mystery method", 412, "Mystery"));
    }

    @Test
    public void testDoRequestAddBook() {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "War");
        params.put("numberOfPage", 9);
        params.put("author", new String[]{"Ivanov", "Sodorov"});
        requestProcessor.doRequest("add_Book", params);
        Book expected = new Book(5, "War", 9, "Ivanov", "Sodorov");
        Book actual = null;
        try {
            actual = bookListDao.findById(5);
        } catch (DaoException e) {
            fail();
        }
        assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = "testDoRequestAddBook")
    public void testDoRequestFindById() {
        Map<String, Object> params = new HashMap<>();
        params.put("id", 5);
        Book expected = new Book(5, "War", 9, "Ivanov", "Sodorov");
        Book actual = (Book) requestProcessor.doRequest("find_by_id", params).get("result");
        assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = "testDoRequestAddBook")
    public void testDoRequestFindByAuthor() {
        Map<String, Object> params = new HashMap<>();
        params.put("author", new String[]{"Mystery"});
        Object[] expected = new Object[]{new Book(3, "Bible", 349, "Mystery"),
                new Book(4, "Mystery method", 412, "Mystery")};
        Object[] actual = ((Stream<Book>) requestProcessor.doRequest("FIND_BY_AUTHOR", params)
                .get("result")).toArray();
        assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = "testDoRequestAddBook")
    public void testDoRequestSortByName() {
        Object[] expected = new Object[]{new Book(3, "Bible", 349, "Mystery"),
                new Book(1, "JAVA_Methods_Prog", 400, "Blinov", "Romanchic"),
                new Book(4, "Mystery method", 412, "Mystery"),
                new Book(5, "War", 9, "Ivanov", "Sodorov"),
                new Book(2, "War and peace", 600, "Tolstoi")};
        Object[] actual = ((Stream<Book>) requestProcessor.doRequest("sort_BY_name", new HashMap<>())
                .get("result")).toArray();
        assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = {"testDoRequestFindById", "testDoRequestFindByAuthor", "testDoRequestSortByName"},
            expectedExceptions = DaoException.class)
    public void testDoRequestRemove() throws DaoException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", 5);
        params.put("name", "War");
        params.put("numberOfPage", 9);
        params.put("author", new String[]{"Ivanov", "Sodorov"});
        requestProcessor.doRequest("remove_Book", params);
        bookListDao.findById(5);
    }
}
