package com.workintech.library.person;

import com.workintech.library.books.Book;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Person implements Comparable<Reader> {
    private List<Book> borrowedBooks = new ArrayList<>();

    public Reader(String name) {
        super(name);
    }

    public String getReaderName() {
        return getName();
    }

    @Override
    public String whoYouAre() {
        return getName() + " borrowed " + borrowedBooks.size() + " books.";
    }

    public void addBook(Book book){
        borrowedBooks.add(book);
    }

    public boolean borrowBook(Book book) {
        if (borrowedBooks.size() >= 5) {
            System.out.println("You cannot borrow more than 5 books.");
            return false;
        } else {
            borrowedBooks.add(book);
            System.out.println(getName() + " borrowed the book: " + book.getName());
            return true;
        }
    }

    public List<Book> showBook(){
        return borrowedBooks;
    }

    public void removeBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "Reader{" +
                "name='" + getName() + '\'' +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }


    @Override
    public int compareTo(Reader object) {
        return this.getName().compareTo(object.getName());
    }

    //    public void returnBook(String returnedBook){
//        if(!books.contains(returnedBook)) {
//            System.out.println(returnedBook + " book is not available in the library.");
//        } else {
//            List<String> purchased = new ArrayList<>();
//            books.removeAll(purchased);
//        }
//    }
}
