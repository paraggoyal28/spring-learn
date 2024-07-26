package org.example.Behavioral.Iterator;

import java.util.List;

public class Library {
    private final List<Book> bookList;

    public Library(List<Book> bookList) {
        this.bookList = bookList;
    }

    public Iterator createIterator() {
        return new BookIterator(bookList);
    }
}
