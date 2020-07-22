package com.epamcourse.homework6.model.dao.impl;

import com.epamcourse.homework6.model.dao.storage.Stock;
import com.epamcourse.homework6.model.entity.Book;
import com.epamcourse.homework6.model.exception.DaoException;
import com.epamcourse.homework6.model.dao.BookStorageDao;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class BookListDao implements BookStorageDao {
    private Stock stock = Stock.getInstance();

    @Override
    public void addBook(Book book) {
        stock.add(book);
    }

    @Override
    public void removeBook(Book book) throws DaoException {
        if (stock.getContext().stream().noneMatch(b -> b.getBookId() == book.getBookId())) {
            throw new DaoException();
        }
        stock.remove(book);
    }

    @Override
    public Book findById(int tag) throws DaoException {
        return stock.getContext().stream().filter(b -> b.getBookId() == tag).findAny()
                .orElseThrow(DaoException::new);
    }

    @Override
    public Stream<Book> findByName(String tag) {
        return stock.getContext().stream().filter(b -> b.getName().equals(tag));
    }

    @Override
    public Stream<Book> findByNumberOfPage(int tag) {
        return stock.getContext().stream().filter(b -> b.getNumberOfPage() == tag);
    }

    @Override
    public Stream<Book> findByAuthor(String[] tag) {
        return stock.getContext().stream().filter(b -> Arrays.equals(b.getAuthor(), tag));
    }

    @Override
    public Stream<Book> sortByName() {
        return stock.getContext().stream().sorted(Comparator.comparing(Book::getName));
    }

    @Override
    public Stream<Book> sortByNumberOfPage() {
        return stock.getContext().stream().sorted(Comparator.comparingInt(Book::getNumberOfPage));
    }
}