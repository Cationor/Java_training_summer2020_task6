package com.epamcourse.homework6.model.dao.storage;

import com.epamcourse.homework6.model.entity.Book;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stock {
    private static final Stock stock = new Stock();
    private final List<Book> context = new ArrayList<>();

    private Stock() {
    }

    public static Stock getInstance() {
        return stock;
    }

    public List<Book> getContext() {
        return Collections.unmodifiableList(context);
    }

    public void add(Book book) {
        book.setBookId(context.isEmpty() ? 1 : context.get(context.size() - 1).getBookId() + 1);
        context.add(book);
    }

    public void remove(Book book) {
        context.removeIf(b -> b.getBookId() == book.getBookId());
    }
}
