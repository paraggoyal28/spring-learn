package com.example.spring.librarymanagementsystem.service;

import com.example.spring.librarymanagementsystem.enums.BookStatus;
import com.example.spring.librarymanagementsystem.models.BookItem;
import com.example.spring.librarymanagementsystem.models.BookLending;
import com.example.spring.librarymanagementsystem.models.Fine;
import com.example.spring.librarymanagementsystem.models.Member;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class LibrarySystem {
    private Catalog catalog;
    private ConcurrentHashMap<String, BookLending> activeLendings;

    public LibrarySystem(Catalog catalog) {
        this.catalog = catalog;
        this.activeLendings = new ConcurrentHashMap<>();
    }

    public synchronized void checkoutBookItem(Member member, String barcode) {
        if (!member.canCheckoutMoreBooks()) {
            throw new IllegalStateException("Member has reached the maximum checkout limit");
        }
        BookItem bookItem = catalog.getBookItem(barcode);
        if (bookItem == null) {
            throw new IllegalArgumentException("Book item cannot be found");
        }
        if (bookItem.isReferenceOnly()) {
            throw new UnsupportedOperationException("This book is reference-only and cannot be checked out");
        }
        if (bookItem.getStatus() != BookStatus.AVAILABLE) {
            throw new IllegalStateException("Book item is currently unavailable. Status: " + bookItem.getStatus());
        }

        bookItem.setStatus(BookStatus.LOANED);
        member.incrementCheckoutCount();

        BookLending lending = new BookLending(barcode, member.getId());
        activeLendings.put(barcode, lending);

        System.out.println("Success: Book [" + barcode + "] checkout successfully by: " + member.getPerson().getName());
    }

    public synchronized void returnBookItem(Member member, String barcode) {
        BookItem bookItem = catalog.getBookItem(barcode);
        if (bookItem == null) {
            throw new IllegalArgumentException("Book item not found.");
        }
        BookLending lending = activeLendings.remove(barcode);
        if (lending == null) {
            throw new IllegalStateException("No active lending record found for this book.");
        }

        Date today = new Date();
        if (today.after(lending.getDueDate())) {
            long diff = today.getTime() - lending.getDueDate().getTime();
            long daysLate = diff / (24 * 60 * 60 * 1000);
            double fineAmount = daysLate * 2.0;
            Fine fine = new Fine(member.getId(), barcode, fineAmount);
            System.out.println("Late Return Notice: Fine of $" + fineAmount + " applied to member: " + member.getId());
        }

        bookItem.setStatus(BookStatus.AVAILABLE);
        member.decrementCheckoutCount();
        System.out.println("Success: Book [" + barcode + "] returned successfully.");
    }
}
