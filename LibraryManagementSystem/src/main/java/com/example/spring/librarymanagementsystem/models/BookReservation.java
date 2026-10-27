package com.example.spring.librarymanagementsystem.models;

import com.example.spring.librarymanagementsystem.enums.ReservationStatus;

import java.util.Date;

public class BookReservation {
    private String bookItemBarcode;
    private String memberId;
    private Date creationDate;
    private ReservationStatus status;

    public BookReservation(String bookItemBarcode, String memberId) {
        this.bookItemBarcode = bookItemBarcode;
        this.memberId = memberId;
        this.creationDate = new Date();
        this.status = ReservationStatus.WAITING;
    }

    public static BookReservation fetchReservationDetails(String barCode) {
        return null;
    }

    public String getBookItemBarcode() {
        return bookItemBarcode;
    }

    public String getMemberId() {
        return memberId;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
