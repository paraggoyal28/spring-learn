package com.example.spring.librarymanagementsystem;

import com.example.spring.librarymanagementsystem.models.BookItem;
import com.example.spring.librarymanagementsystem.models.Author;
import com.example.spring.librarymanagementsystem.models.Book;
import com.example.spring.librarymanagementsystem.models.Person;
import com.example.spring.librarymanagementsystem.models.Member;
import com.example.spring.librarymanagementsystem.service.Catalog;
import com.example.spring.librarymanagementsystem.service.LibrarySystem;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Catalog catalog = new Catalog();
        LibrarySystem librarySystem = new LibrarySystem(catalog);

        Author author = new Author("Joshua Bloch", "Author of Effective Java");
        Book javaBook = new Book("978-016850310", "Effective Java", "Programming",
                "Addison-Wesley", "English", 416, List.of(author));
        BookItem bookItem1 = new BookItem("BARCODE-001", false, 45.00, new Date(), javaBook);
        catalog.addBooKItem(bookItem1);

        Person person = new Person("Alice Smith", "alice@example.com", "9876543210", "123 Main St.");
        Member member = new Member("M001", "password123", person);

        try {
            librarySystem.checkoutBookItem(member, "BARCODE-001");
            librarySystem.returnBookItem(member, "BARCODE-001");
        } catch (Exception e) {
            System.err.println("Error!: " + e.getMessage());
        }
    }
}
