package com.example.spring.librarymanagementsystem.models;

import java.util.Calendar;
import java.util.Date;

public class BookLending {
    private String bookItemBarcode;
    private String memberId;
    private Date creationDate;
    private Date dueDate;
    private Date returnDate;

    public BookLending(String bookItemBarcode,
                       String memberId) {
        this.bookItemBarcode = bookItemBarcode;
        this.memberId = memberId;
        this.creationDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.creationDate);
        calendar.add(Calendar.DAY_OF_MONTH, 14);
        this.dueDate = calendar.getTime();
    }

    public static BookLending fetchLendingDetails(String barCode) {
        return null;
    }

    public String getBookItemBarcode() {
        return bookItemBarcode;
    }

    public String getMemberId() {
        return memberId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
