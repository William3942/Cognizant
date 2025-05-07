package LibraryCatalogManagementSystem;

import java.util.HashMap;

/**
 *
 * @author Guillermo
 */
public class LibraryCatalog {
    private HashMap<String, Book> books;

    public LibraryCatalog() {
        books = new HashMap<>();
    }

    public boolean addBook(Book book) {
        if (books.containsKey(book.getBookId())) {
            return false; // book ID already exists
        }
        books.put(book.getBookId(), book);
        return true;
    }

    public Book searchBookById(String bookId) {
        return books.get(bookId);
    }

    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books found in catalog.");
            return;
        }
        for (Book book : books.values()) {
            System.out.println(book);
            System.out.println("---------------");
        }
    }

    public boolean updateAvailability(String bookId, boolean availability) {
        Book book = books.get(bookId);
        if (book != null) {
            book.setAvailable(availability);
            return true;
        }
        return false;
    }

    public boolean bookExists(String bookId) {
        return books.containsKey(bookId);
    }
}