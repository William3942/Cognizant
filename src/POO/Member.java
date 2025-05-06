package POO;

import java.util.*;

/**
 *
 * @author Guillermo
 */
public class Member {
    private String name;
    private String memberId;
    private List<Book> borrowedBooks;
    private static final int BORROW_LIMIT = 3;

    public Member(String name, String memberId) {
        this.name = name;
        this.memberId = memberId;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getMemberId() { return memberId; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }

    public boolean borrowBook(Book book) {
        if (borrowedBooks.size() < BORROW_LIMIT && book.isAvailable()) {
            borrowedBooks.add(book);
            book.setAvailable(false);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name + " (ID: " + memberId + ")\nBorrowed books:");
        if (borrowedBooks.isEmpty()) {
            sb.append(" None");
        } else {
            for (Book book : borrowedBooks) {
                sb.append("\n- ").append(book.getTitle());
            }
        }
        return sb.toString();
    }
}
