import com.workintech.library.Library;
import com.workintech.library.books.Book;
import com.workintech.library.books.BookStatus;
import com.workintech.library.books.Novel;
import com.workintech.library.person.Reader;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Library instance oluşturma
        Library library = new Library();

        //Id ile kitap aramak
        Book searchingWithId = library.searchBookById(2);
        System.out.println("Id ile arama sonucu: " + searchingWithId);

        //İsim ile kitap aramak
        Book searchingWithBookName = library.searchBookByName("Science Journal");
        System.out.println("Kitap ismi ile arama sonucu: " + searchingWithBookName);

        //Yazar ismi ile kitap aramak(Aynı yazarın birden fazla kitabı varsa bulduğu ilk kitabı döner.)
        Book searchingWithAuthor = library.searchBookByAuthor("Zülfü Livaneli");
        System.out.println("Yazar ismi ile arama sonucu: " + searchingWithAuthor);

        //Yeni kitap ekleme
        Book newBook = new Novel("Zülfü Livaneli",11, LocalDate.of(2023,6,15),"Serenad",20,BookStatus.IN_LIBRARY);
        library.addBook(newBook);
        System.out.println("Yeni eklenen kitap: " + newBook);

        //Bütün kitapları listele
        System.out.println("Bütün kitaplar: " + library);
        System.out.println("Kitap sayısı: " + library.getBookCount());

        //Kitap bilgisi güncelle
        Book newVersion = new Novel("Zülfü Livaneli",11, LocalDate.of(2023,6,15),"Serenad",22,BookStatus.IN_LIBRARY);
        library.editBook(11, newVersion);
        System.out.println("Güncellenmiş kitap: " + newBook);

        //Kitap silme
        library.removeBook(11);
        System.out.println("Kitap silindikten sonra toplam kitap sayısı: " + library.getBookCount());

        //Duruma göre kitap listele
        List<Book> borrowedBooks = library.booksByStatus(BookStatus.BORROWED);
        System.out.println("Statüsü borrowed olanlar: " + borrowedBooks);

        //Kategoriye göre kitap listele
        List<Book> novels = library.booksByType(Novel.class);
        System.out.println("Roman türündeki kitaplar: " + novels);

        //Yazara göre kitap listele
        List<Book> authorsBooks = library.booksBySameAuthors("Can Yayınları");
        System.out.println("Can yayınlarına ait kitaplar: " + authorsBooks);

        //Yeni okuyucular ekleme
        Reader reader1 = new Reader("Mert");
        Reader reader2 = new Reader("Okuyucu");
        library.addReader(reader1);
        library.addReader(reader2);
        System.out.println(library.getReaders());

//

        //Kitap ödünç alma durumu
        library.borrowBook(reader1, library.searchBookByName("Sevda Sözleri"));
        library.borrowBook(reader1, library.searchBookByName("Huzursuzluk"));
        library.borrowBook(reader2, library.searchBookByName("Math Book"));
        library.borrowBook(reader2, library.searchBookByName("Sevda Sözleri"));
        System.out.println("Kitap ödünç alındıktan sonra son durum: " + library);
        System.out.println("Reader1 kitapları: " + reader1);

        //Ödünç alındıktan sonra duruma göre kitap listele
        List<Book> borrowedBooks1 = library.booksByStatus(BookStatus.BORROWED);
        System.out.println("Statüsü borrowed olanlar: " + borrowedBooks1);

        //Ödünç alınan kitabı iade etme durumu
        library.returnBook(reader1,library.searchBookByName("Huzursuzluk"));
        System.out.println("Kitap iade edildikten sonra son durum: " + library);
        System.out.println("İade sonrası reader1 kitapları: " + reader1);

        library.showBorrowedBooks();
    }
}