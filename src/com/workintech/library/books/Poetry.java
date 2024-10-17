package com.workintech.library.books;

import java.time.LocalDate;

public class Poetry extends Book{
    public Poetry(String author, int book_ID, LocalDate date_of_purchase, String name, double price, BookStatus status) {
        super(author, book_ID, date_of_purchase, name, price, status);
    }
}
