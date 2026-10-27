package com.example.spring.librarymanagementsystem.models;

import java.util.*;

public class Catalog {
    private Map<String, List<Book>>  bookTitles;
    private Map<String, List<Book>> bookAuthors;
    private Map<String, List<Book>> bookSubjects;
    private Map<String, BookItem> bookItems;

    public Catalog() {
        this.bookTitles = new HashMap<>();
        this.bookAuthors = new HashMap<>();
        this.bookSubjects = new HashMap<>();
        this.bookItems = new HashMap<>();
    }

    public void addBookItems(BookItem bookItem) {
        bookItems.put(bookItem.getBarcode(), bookItem);
        Book book = bookItem.getBook();

        bookTitles.computeIfAbsent(book.getTitle().toLowerCase(), k->new ArrayList<>()).add(book);
        bookSubjects.computeIfAbsent(book.getSubject().toLowerCase(), k -> new ArrayList<>()).add(book);

        for (var author: book.getAuthors()) {
            bookAuthors.computeIfAbsent(author.getName().toLowerCase(), k -> new ArrayList<>()).add(book);
        }
    }

    public List<Book> searchByTitle(String title) {
        return bookTitles.getOrDefault(title.toLowerCase(), Collections.emptyList());
    }

    public List<Book> searchBySubject(String subject) {
        return bookSubjects.getOrDefault(subject.toLowerCase(), Collections.emptyList());
    }

    public BookItem getBookItem(String barCode) {
        return bookItems.get(barCode);
    }
}
