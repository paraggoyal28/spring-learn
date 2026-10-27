package com.example.spring.librarymanagementsystem.models;

import java.util.Date;

public class Member extends Account {
    private Date dateOfMemberShip;
    private int totalBooksCheckedOut;
    private static final int MAX_BOOKS_ALLOWED = 5;

    public Member(String id, String password, Person person) {
        super(id, password, person);
        this.dateOfMemberShip = new Date();
        this.totalBooksCheckedOut = 0;
    }

    public int getTotalBooksCheckedOut() {
        return totalBooksCheckedOut;
    }

    public void incrementCheckoutCount() {
        this.totalBooksCheckedOut += 1;
    }

    public void decrementCheckoutCount() {
        if (this.totalBooksCheckedOut > 0) {
            this.totalBooksCheckedOut--;
        }
    }

    public boolean canCheckoutMoreBooks() {
        return this.totalBooksCheckedOut < MAX_BOOKS_ALLOWED;
    }
}
