package com.workintech.library;

import com.workintech.library.books.*;
import com.workintech.library.person.Reader;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books = new ArrayList<>();
    private Set<Reader> readers = new HashSet<>();
    private Map<Reader, List<Book>> borrowedBooks = new TreeMap<>();


    public Library() {
        Book book1 = new Novel("Zülfü Livaneli",1, LocalDate.of(2023,6,15),"Serenad",20,BookStatus.IN_LIBRARY);
        Book book2 = new Magazine("Can Yayınları",2,LocalDate.of(2024,3,1),"Socrates Dergi",10,BookStatus.IN_LIBRARY);
        Book book3 = new Poetry("Cemal Süreya",3,LocalDate.of(2022,4,12),"Sevda Sözleri",12,BookStatus.IN_LIBRARY);
        Book book4 = new Journal("Journalist",4,LocalDate.of(2020,1,8),"Science Journal",20,BookStatus.IN_LIBRARY);
        Book book5 = new StudyBook("StudyBook Author",5,LocalDate.of(2014,10,28),"Science Book",25,BookStatus.IN_LIBRARY);
        Book book6 = new StudyBook("StudyBook Author2",6,LocalDate.of(2018,3,16),"Math Book",20,BookStatus.IN_LIBRARY);
        Book book7 = new Novel("Novel Author",7,LocalDate.of(2019,2,19),"Novel Book",16,BookStatus.IN_LIBRARY);
        Book book8 = new Poetry("Poetry Author",8,LocalDate.of(1998,7,5),"Poetry Book",30,BookStatus.IN_LIBRARY);
        Book book9 = new Novel("Zülfü Livaneli",9, LocalDate.of(2020,1,15),"Huzursuzluk",20,BookStatus.IN_LIBRARY);
        Book book10 = new Magazine("Can Yayınları",10,LocalDate.of(2024,4,1),"Socrates Dergi",10,BookStatus.IN_LIBRARY);

        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        books.add(book6);
        books.add(book7);
        books.add(book8);
        books.add(book9);
        books.add(book10);
    }

    public void borrowBook(Reader reader, Book book) {
        if (book.getStatus() == BookStatus.IN_LIBRARY) {
            if (borrowedBooks.containsKey(reader)) {
                List<Book> borrowed = borrowedBooks.get(reader);
                if (borrowed.size() < 5) { // Kullanıcının kitap limiti kontrol ediliyor
                    borrowed.add(book);
                    book.setStatus(BookStatus.BORROWED);
                    reader.addBook(book);
                    System.out.println(reader.getName() + " borrowed the book: " + book.getName());
                } else {
                    System.out.println(reader.getName() + " has already reached the book limit of 5.");
                }
            } else {
                List<Book> borrowed = new ArrayList<>();
                borrowed.add(book);
                borrowedBooks.put(reader, borrowed);
                book.setStatus(BookStatus.BORROWED);
                reader.addBook(book); // Reader'a kitabı ekliyoruz
                System.out.println(reader.getName() + " borrowed the book: " + book.getName());
            }
        } else {
            System.out.println("The book is not available.");
        }
    }


    public void returnBook(Reader reader, Book book) {
        if (borrowedBooks.containsKey(reader)) {
            List<Book> borrowed = borrowedBooks.get(reader);
            if (borrowed.contains(book)) {
                borrowed.remove(book);
                book.setStatus(BookStatus.IN_LIBRARY);
                reader.removeBook(book); // Reader'dan kitabı çıkarıyoruz
                System.out.println(reader.getName() + " returned the book: " + book.getName());
            } else {
                System.out.println(reader.getName() + " has not borrowed the book: " + book.getName());
            }
        } else {
            System.out.println("No record of this reader borrowing books.");
        }
    }


    public void showBorrowedBooks() {
        for (Map.Entry<Reader, List<Book>> entry : borrowedBooks.entrySet()) {
            Reader reader = entry.getKey();
            List<Book> books = entry.getValue();
            System.out.println(reader.getName() + " has borrowed the following books:");
            for (Book book : books) {
                System.out.println("- " + book.getName());
            }
        }
    }



    public Book searchBookById(int id){
        for(Book book : books){
            if(book.getBook_ID() == id){
                return book;
            }
        }
        System.out.println("Book with id " + id + " not found.");
        return null;
    }

    public Book searchBookByName(String name){
        for(Book book : books){
            if(book.getName().equals(name)){
                return book;
            }
        }
        System.out.println("Book named " + name + " is not found.");
        return null;
    }

    public Book searchBookByAuthor(String author){
        for(Book book : books){
            if(book.getAuthor().equals(author)){
                return book;
            }
        }
        System.out.println("No book written by " + author + " is found.");
        return null;
    }

    public void addBook(Book book){
        books.add(book);
    }

    public void removeBook(int bookID) {
        Book bookToRemove = null;
        for (Book book : books) {
            if (book.getBook_ID() == bookID) {
                bookToRemove = book;
                break;
            }
        }
        if (bookToRemove != null) {
            books.remove(bookToRemove);
            System.out.println("Book with ID " + bookID + " was removed from the library.");
        } else {
            System.out.println("Book with ID " + bookID + " was not found in the library.");
        }
    }


    public void editBook(int bookID, Book newDetails) {
        for (Book book : books) {
            if (book.getBook_ID() == bookID) {
                book.setAuthor(newDetails.getAuthor());
                book.setName(newDetails.getName());
                book.setPrice(newDetails.getPrice());
                book.setStatus(newDetails.getStatus());
                book.setDate_of_purchase(newDetails.getDate_of_purchase());
                System.out.println("Book with ID " + bookID + " has been updated.");
                return; // Kitap bulundu ve güncellendi, metodu sonlandırıyoruz
            }
        }
        System.out.println("Book with ID " + bookID + " was not found in the library.");
    }


    public List<Book> booksBySameAuthors(String author){
        List<Book> sameAuthorsBookSet = new ArrayList<>();

        for (Book book : books){
            if (book.getAuthor().toLowerCase(new Locale("tr")).contains(author.toLowerCase(new Locale("tr")))){
                sameAuthorsBookSet.add(book);
            }
        }
        return sameAuthorsBookSet;
    }

    public List<Book> booksByStatus(BookStatus status){
        return books.stream()
                .filter(book -> book.getStatus().equals(status))
                .sorted(Comparator.comparing(Book::getAuthor))
                .collect(Collectors.toList());
    }

    public List<Book> booksByType(Class<?> bookType){
        List<Book> filteredBooks = new ArrayList<>();

        for (Book book : books){
            if(book.getClass() == bookType){
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }

    public int getBookCount() {
        return books.size();
    }

    public List<Book> getBooks() {
        return books;
    }

    public Set<Reader> getReaders() {
        Set<Reader> allReaders = new HashSet<>();
        for (Reader reader : readers){
            allReaders.add(reader);
        }
        return allReaders;
    }

    public Reader getReadersByName(String name) {
        for(Reader reader : readers){
            if(reader.getName().equals(name)){
                return reader;
            }
        }
        return null;
    }

    public void addReader(Reader reader) {
        readers.add(reader);
        System.out.println("Reader " + reader.getName() + " has been added to the library.");
    }

//    public Book searchBookByName(String name){
//        for(Book book : books){
//            if(book.getName() == name){
//                return book;
//            }
//        }
//        System.out.println("Book named " + name + " is not found.");
//        return null;
//    }

    @Override
    public String toString() {
        return "Library{" +
                "books=" + books +
                '}';
    }

//    @Override
//    public boolean equals(Object object) {
//        if (this == object) return true;
//        if (object == null || getClass() != object.getClass()) return false;
//        Library library = (Library) object;
//        return Objects.equals(books, library.books) && Objects.equals(readers, library.readers);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(books, readers);
//    }
}
