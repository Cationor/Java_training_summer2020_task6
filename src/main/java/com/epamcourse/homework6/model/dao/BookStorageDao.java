package com.epamcourse.homework6.model.dao;

import com.epamcourse.homework6.model.entity.Book;
import com.epamcourse.homework6.model.exception.DaoException;

import java.util.stream.Stream;

public interface BookStorageDao {
    void addBook(Book book);

    void removeBook(Book book) throws DaoException;

    Book findById(int tag) throws DaoException;

    Stream<Book> findByName(String tag);

    Stream<Book> findByNumberOfPage(int tag);

    Stream<Book> findByAuthor(String[] tag);

    Stream<Book> sortByName();

    Stream<Book> sortByNumberOfPage();
}