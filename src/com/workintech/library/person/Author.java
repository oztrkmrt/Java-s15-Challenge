package com.workintech.library.person;

import java.util.Set;

public class Author extends Person {
    Set<String> books;

    public Author(String name, Set<String> books) {
        super(name);
        this.books = books;
    }

    public Set<String> showBook(){
        return books;
    }

    public void newBook(String newBook){
        books.add(newBook);
    }

    @Override
    public String toString() {
        return "Author{" +
                "books=" + books +
                '}';
    }

    @Override
    public String whoYouAre() {
        return getName() + "is the author of " + books.size() + " books.";
    }
}


