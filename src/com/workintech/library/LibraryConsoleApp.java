package com.workintech.library;

import com.workintech.library.books.*;
import com.workintech.library.person.Reader;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class LibraryConsoleApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        boolean running = true;

        while (running) {
            System.out.println("Library Management System");
            System.out.println("1. List all books");
            System.out.println("2. Add a new book");
            System.out.println("3. Delete a book");
            System.out.println("4. Select a book by ID");
            System.out.println("5. Select a book by name");
            System.out.println("6. Select a book by author");
            System.out.println("7. Update an existing book");
            System.out.println("8. List all books in a category");
            System.out.println("9. List all books by an author");
            System.out.println("10. List all books by a status");
            System.out.println("11. Add a new reader");
            System.out.println("12. Borrow a book");
            System.out.println("13. Return a borrowed book");
            System.out.println("14. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    System.out.println(library.getBooks());
                    break;
                case 2:
                    System.out.println("Select book type: ");
                    System.out.println("1. Novel");
                    System.out.println("2. Poetry");
                    System.out.println("3. Magazine");
                    System.out.println("4. StudyBook");
                    System.out.println("5. Journal");
                    int bookType = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter book ID: ");
                    int bookID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter purchase date (YYYY-MM-DD): ");
                    String dateString = scanner.nextLine();
                    LocalDate dateOfPurchase = LocalDate.parse(dateString);
                    System.out.print("Enter book name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter book price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter status (IN_LIBRARY or BORROWED): ");
                    String statusString = scanner.nextLine();
                    BookStatus status = BookStatus.valueOf(statusString);

                    Book newBook;
                    switch (bookType) {
                        case 1:
                            newBook = new Novel(author, bookID, dateOfPurchase, name, price, status);
                            break;
                        case 2:
                            newBook = new Poetry(author, bookID, dateOfPurchase, name, price, status);
                            break;
                        case 3:
                            newBook = new Magazine(author, bookID, dateOfPurchase, name, price, status);
                            break;
                        case 4:
                            newBook = new StudyBook(author, bookID, dateOfPurchase, name, price, status);
                            break;
                        case 5:
                            newBook = new Journal(author, bookID, dateOfPurchase, name, price, status);
                            break;
                        default:
                            System.out.println("Invalid book type selected. Book not added.");
                            return;
                    }

                    library.addBook(newBook);
                    System.out.println("Book added successfully.");
                    break;
                case 3:
                    System.out.print("Enter the book ID to delete: ");
                    int idToDelete = scanner.nextInt();
                    library.removeBook(idToDelete);
                    break;
                case 4:
                    System.out.print("Enter the book ID: ");
                    int idToSelect = scanner.nextInt();
                    Book selectedBookById = library.searchBookById(idToSelect);
                    System.out.println(selectedBookById);
                    break;
                case 5:
                    System.out.print("Enter the book name: ");
                    String nameToSelect = scanner.nextLine();
                    Book selectedBookByName = library.searchBookByName(nameToSelect);
                    System.out.println(selectedBookByName);
                    break;
                case 6:
                    System.out.print("Enter the author name: ");
                    String authorToSelect = scanner.nextLine();
                    Book selectedBookByAuthor = library.searchBookByAuthor(authorToSelect);
                    System.out.println(selectedBookByAuthor);
                    break;
                case 7:
                    System.out.print("Enter the ID of the book to update: ");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter the type of the book (Novel/Poetry/etc.): ");
                    String newBookType = scanner.nextLine();

                    System.out.print("Enter new author: ");
                    String newAuthor = scanner.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new price: ");
                    double newPrice = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter new status (IN_LIBRARY/BORROWED): ");
                    String newStatus = scanner.nextLine();
                    System.out.print("Enter purchase date (YYYY-MM-DD): ");
                    String newDate = scanner.nextLine();
                    LocalDate newDateOfPurchase = LocalDate.parse(newDate);

                    Book updatedBook;
                    if (newBookType.equalsIgnoreCase("Novel")) {
                        updatedBook = new Novel(newAuthor, idToUpdate, newDateOfPurchase, newName, newPrice, BookStatus.valueOf(newStatus));
                    } else if (newBookType.equalsIgnoreCase("Poetry")) {
                        updatedBook = new Poetry(newAuthor, idToUpdate, newDateOfPurchase, newName, newPrice, BookStatus.valueOf(newStatus));
                    }
                    else if (newBookType.equalsIgnoreCase("Journal")) {
                        updatedBook = new Journal(newAuthor, idToUpdate, newDateOfPurchase, newName, newPrice, BookStatus.valueOf(newStatus));
                    }
                    else if (newBookType.equalsIgnoreCase("Magazine")) {
                        updatedBook = new Magazine(newAuthor, idToUpdate, newDateOfPurchase, newName, newPrice, BookStatus.valueOf(newStatus));
                    }
                    else if (newBookType.equalsIgnoreCase("Study Book")) {
                        updatedBook = new StudyBook(newAuthor, idToUpdate, newDateOfPurchase, newName, newPrice, BookStatus.valueOf(newStatus));
                    }else {
                        System.out.println("Invalid book type.");
                        break;
                    }

                    library.editBook(idToUpdate, updatedBook);
                    break;

                case 8:
                    System.out.print("Enter the category (Novel/Poetry/etc.): ");
                    String categoryToSelect = scanner.nextLine();

                    Class<?> bookClass = null;
                    switch (categoryToSelect.toLowerCase()) {
                        case "novel":
                            bookClass = Novel.class;
                            break;
                        case "journal":
                            bookClass = Journal.class;
                            break;
                        case "magazine":
                            bookClass = Magazine.class;
                            break;
                        case "poetry":
                            bookClass = Poetry.class;
                            break;
                        case "study book":
                            bookClass = StudyBook.class;
                            break;
                        default:
                            System.out.println("Invalid category.");
                            break;
                    }

                    // Kategoriye göre kitapları listele
                    List<Book> filteredBooks = library.booksByType(bookClass);
                    if (filteredBooks.isEmpty()) {
                        System.out.println("No books found in this category.");
                    } else {
                        System.out.println("Books in the category " + categoryToSelect + ":");
                        for (Book book : filteredBooks) {
                            System.out.println("- " + book.getName());
                        }
                    }
                    break;

                case 9:
                    System.out.print("Enter the author name: ");
                    String authorForBooks = scanner.nextLine();
                    List<Book> filteredBooksByAuthor = library.booksBySameAuthors(authorForBooks);
                    System.out.println(filteredBooksByAuthor);
                    break;
                case 10:
                    System.out.print("Enter the status (IN_LIBRARY/BORROWED): ");
                    String statusInput = scanner.nextLine();

                    // BookStatus enum değerine dönüştür
                    BookStatus status1;
                    try {
                        status1 = BookStatus.valueOf(statusInput.toUpperCase());
                        List<Book> filteredBooks1 = library.booksByStatus(status1);

                        // Kitapları listele
                        if (filteredBooks1.isEmpty()) {
                            System.out.println("No books found with the status " + statusInput + ".");
                        } else {
                            System.out.println("Books with the status " + statusInput + ":");
                            for (Book book : filteredBooks1) {
                                System.out.println("- " + book.getName() + " by " + book.getAuthor());
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid status. Please enter either IN_LIBRARY or BORROWED.");
                    }
                    break;
                case 11:
                    System.out.print("Enter reader name: ");
                    String readerName = scanner.nextLine();
                    Reader reader1 = new Reader(readerName);
                    library.addReader(reader1);
                    System.out.println("Readers: " + library.getReaders());
                    break;
                case 12:
                    System.out.print("Enter reader name: ");
                    String borrowerName = scanner.nextLine();
                    System.out.print("Enter book ID to borrow: ");
                    int bookIdToBorrow = scanner.nextInt();
                    Reader borrower = library.getReadersByName(borrowerName);
                    Book bookToBorrow = library.searchBookById(bookIdToBorrow);
                    if (borrower != null && bookToBorrow != null) {
                        library.borrowBook(borrower, bookToBorrow);
                    } else {
                        if (borrower == null) {
                            System.out.println("Reader not found.");
                        }
                        if (bookToBorrow == null) {
                            System.out.println("Book not found.");
                        }
                    }
                    break;
                case 13:
                    System.out.print("Enter reader name: ");
                    String returnerName = scanner.nextLine();
                    System.out.print("Enter book ID to return: ");
                    int bookIdToReturn = scanner.nextInt();
                    Reader returner = library.getReadersByName(returnerName);
                    Book bookToReturn = library.searchBookById(bookIdToReturn);
                    if (returner != null && bookToReturn != null) {
                        library.returnBook(returner, bookToReturn);
                    } else {
                        if (returner == null) {
                            System.out.println("Reader not found.");
                        }
                        if (bookToReturn == null) {
                            System.out.println("Book not found.");
                        }
                    }
                    break;
                case 14:
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        scanner.close();

    }
}
