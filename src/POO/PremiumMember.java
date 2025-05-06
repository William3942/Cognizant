package POO;

/**
 *
 * @author Guillermo
 */
public class PremiumMember extends Member {
    private static final int PREMIUM_BORROW_LIMIT = 5;

    public PremiumMember(String name, String memberId) {
        super(name, memberId);
    }

    @Override
    public boolean borrowBook(Book book) {
        if (getBorrowedBooks().size() < PREMIUM_BORROW_LIMIT && book.isAvailable()) {
            getBorrowedBooks().add(book);
            book.setAvailable(false);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + "\n(Premium Member)";
    }
}
