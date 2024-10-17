package com.workintech.library.books;


import java.time.LocalDate;
import java.util.Objects;

public abstract class Book {
    private int book_ID;
    private String author;
    private String name;
    private double price;
    private BookStatus status;
    private LocalDate date_of_purchase;

    public Book(String author, int book_ID, LocalDate date_of_purchase, String name, double price, BookStatus status) {
        this.author = author;
        this.book_ID = book_ID;
        this.date_of_purchase = date_of_purchase;
        this.name = name;
        this.price = price;
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getBook_ID() {
        return book_ID;
    }

    public void setBook_ID(int book_ID) {
        this.book_ID = book_ID;
    }

    public LocalDate getDate_of_purchase() {
        return date_of_purchase;
    }

    public void setDate_of_purchase(LocalDate date_of_purchase) {
        this.date_of_purchase = date_of_purchase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Book book = (Book) object;
        return book_ID == book.book_ID && Objects.equals(author, book.author) && Objects.equals(name, book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book_ID, author, name);
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", book_ID=" + book_ID +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", date_of_purchase=" + date_of_purchase +
                '}';
    }
}
