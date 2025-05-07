package LibraryCatalogManagementSystem;

/**
 *
 * @author Guillermo
 */
public class Book {
    private String bookId;
    private String title;
    private String author;
    private String genre;
    private boolean isAvailable;

    public Book(String bookId, String title, String author, String genre, boolean isAvailable) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isAvailable = isAvailable;
    }

    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public boolean isAvailable() { return isAvailable; }

    public void setAvailable(boolean available) { this.isAvailable = available; }

    @Override
    public String toString() {
        return "Book ID: " + bookId + "\nTitle: " + title + "\nAuthor: " + author +
               "\nGenre: " + genre + "\nAvailable: " + (isAvailable ? "Yes" : "No");
    }
}