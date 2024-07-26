package org.example.Behavioral.Iterator;

public class Book {
    private int price;
    private String bookName;

    public Book(int price, String bookName) {
        this.price = price;
        this.bookName = bookName;
    }

    public int getPrice() {
        return price;
    }

    public String getBookName() {
        return bookName;
    }

    @Override
    public String toString() {
        return "Book{" +
                "price=" + price +
                ", bookName='" + bookName + '\'' +
                '}';
    }
}
