package com.epamcourse.homework6.model.service;

import com.epamcourse.homework6.model.exception.ServiceException;
import com.epamcourse.homework6.model.entity.Book;
import com.epamcourse.homework6.model.exception.DaoException;
import com.epamcourse.homework6.model.dao.BookStorageDao;
import com.epamcourse.homework6.model.dao.impl.BookListDao;

import java.util.stream.Stream;

public class BookService {
    private BookStorageDao bookStorageDao = new BookListDao();

    public void addBook(Book book) throws ServiceException {
        if (book == null) {
            throw new ServiceException();
        }
        bookStorageDao.addBook(book);
    }

    public void removeBook(Book book) throws ServiceException {
        if (book == null) {
            throw new ServiceException();
        }
        try {
            bookStorageDao.removeBook(book);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Book findById(int tag) throws ServiceException {
        try {
            return bookStorageDao.findById(tag);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Stream<Book> findByName(String tag) throws ServiceException {
        if (tag == null) {
            throw new ServiceException();
        }
        return bookStorageDao.findByName(tag);
    }

    public Stream<Book> findByNumberOfPage(int tag) {
        return bookStorageDao.findByNumberOfPage(tag);
    }

    public Stream<Book> findByAuthor(String[] tag) throws ServiceException {
        if (tag == null) {
            throw new ServiceException();
        }
        return bookStorageDao.findByAuthor(tag);
    }

    public Stream<Book> sortByName() {
        return bookStorageDao.sortByName();
    }

    public Stream<Book> sortByNumberOfPage() {
        return bookStorageDao.sortByNumberOfPage();
    }
}
