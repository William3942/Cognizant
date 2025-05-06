package POO;

import java.util.*;

/**
 *
 * @author Guillermo
 */
public class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerMember(Member member) {
        members.add(member);
    }

    public boolean borrowBook(String memberId, String ISBN) {
        Member member = findMemberById(memberId);
        Book book = findBookByISBN(ISBN);

        if (member != null && book != null) {
            boolean success = member.borrowBook(book);
            System.out.println(success ? "Book borrowed successfully." : "Borrowing failed.");
            return success;
        }
        System.out.println("Member or Book not found.");
        return false;
    }

    public void displayAvailableBooks() {
        System.out.println("Available books:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book);
            }
        }
    }

    public void displayMembers() {
        System.out.println("Registered members:");
        for (Member member : members) {
            System.out.println(member);
        }
    }

    private Member findMemberById(String memberId) {
        for (Member member : members) {
            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }

    private Book findBookByISBN(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                return book;
            }
        }
        return null;
    }
}