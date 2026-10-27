package com.example.spring.librarymanagementsystem.models;

import com.example.spring.librarymanagementsystem.enums.BookStatus;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class BookItem {
    private String barcode;
    private boolean isReferenceOnly;
    private double price;
    private BookStatus status;
    private Date dateOfPurchase;
    private Date publicationDate;
    private Book book;

    public BookItem(String barcode,
                    boolean isReferenceOnly,
                    double price,
                    Date publicationDate,
                    Book book) {
        this.barcode = barcode;
        this.isReferenceOnly = isReferenceOnly;
        this.price = price;
        this.status = BookStatus.AVAILABLE;
        this.dateOfPurchase = new Date();
        this.publicationDate = publicationDate;
        this.book = book;
    }

    public String getBarcode() {
        return barcode;
    }

    public boolean isReferenceOnly() {
        return isReferenceOnly;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public Book getBook() {
        return book;
    }
}
