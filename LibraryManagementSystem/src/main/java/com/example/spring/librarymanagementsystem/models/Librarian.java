package com.example.spring.librarymanagementsystem.models;


public class Librarian extends Account {
    public Librarian(String id, String password, Person person) {
        super(id, password, person);
    }

    public void addBookItem(BookItem bookItem, Catalog catalog) {

    }
}
