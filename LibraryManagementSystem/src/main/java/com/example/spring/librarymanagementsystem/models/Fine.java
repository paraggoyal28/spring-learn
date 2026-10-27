package com.example.spring.librarymanagementsystem.models;

import java.util.Date;

public class Fine {
    private String memberId;
    private Date creationDate;
    private String bookItemBarcode;
    private double amount;

    public Fine(String memberId, String bookItemBarcode, double amount) {
        this.memberId = memberId;
        this.bookItemBarcode = bookItemBarcode;
        this.creationDate = new Date();
        this.amount = amount;
    }

    public static void collectFine(String memberId, double amount) {
        // business logic to process fine payment
    }
}
