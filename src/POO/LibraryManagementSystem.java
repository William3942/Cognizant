package POO;

/**
 *
 * @author Guillermo
 */ 
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        // Adding books
        Book book1 = new Book("1984", "George Orwell", "12345");
        EBook ebook1 = new EBook("Digital Fortress", "Dan Brown", "54321", "PDF", 2.5);

        library.addBook(book1);
        library.addBook(ebook1);

        // Registering members
        Member member1 = new Member("Alice", "M001");
        PremiumMember member2 = new PremiumMember("Bob", "M002");

        library.registerMember(member1);
        library.registerMember(member2);

        // Display initial state
        library.displayAvailableBooks();
        library.displayMembers();

        // Borrowing books
        library.borrowBook("M001", "12345");
        library.borrowBook("M002", "54321");

        // Display updated state
        library.displayAvailableBooks();
        library.displayMembers();
    }
}

