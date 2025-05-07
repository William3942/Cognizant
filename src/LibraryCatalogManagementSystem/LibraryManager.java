package LibraryCatalogManagementSystem;

import java.util.Scanner;

/**
 *
 * @author Guillermo
 */
public class LibraryManager {
    public static void main(String[] args) {
        LibraryCatalog catalog = new LibraryCatalog();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n=== LIBRARY CATALOG MENU ===");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book by ID");
            System.out.println("4. Update Book Availability");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            option = getIntInput(scanner);

            switch (option) {
                case 1:
                    addBookFlow(catalog, scanner);
                    break;
                case 2:
                    catalog.displayAllBooks();
                    break;
                case 3:
                    searchBookFlow(catalog, scanner);
                    break;
                case 4:
                    updateAvailabilityFlow(catalog, scanner);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }

        } while (option != 0);

        scanner.close();
    }

    private static void addBookFlow(LibraryCatalog catalog, Scanner scanner) {
        System.out.print("Enter Book ID (format B000): ");
        String bookId = scanner.nextLine().trim();
        if (!bookId.matches("B\\d{3}")) {
            System.out.println("Invalid Book ID format. Must be B followed by 3 digits.");
            return;
        }
        if (catalog.bookExists(bookId)) {
            System.out.println("Book ID already exists.");
            return;
        }

        System.out.print("Enter Title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine().trim();
        System.out.print("Enter Genre (Fiction, Non-Fiction, Science, History): ");
        String genre = scanner.nextLine().trim();
        if (!isValidGenre(genre)) {
            System.out.println("Invalid genre.");
            return;
        }

        Book book = new Book(bookId, title, author, genre, true);
        catalog.addBook(book);
        System.out.println("Book added successfully.");
    }

    private static void searchBookFlow(LibraryCatalog catalog, Scanner scanner) {
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine().trim();
        Book book = catalog.searchBookById(bookId);
        if (book == null) {
            System.out.println("Book not found.");
        } else {
            System.out.println(book);
        }
    }

    private static void updateAvailabilityFlow(LibraryCatalog catalog, Scanner scanner) {
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine().trim();
        Book book = catalog.searchBookById(bookId);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        System.out.print("Mark as available? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        if (response.equals("yes") || response.equals("y")) {
            catalog.updateAvailability(bookId, true);
            System.out.println("Book marked as available.");
        } else if (response.equals("no") || response.equals("n")) {
            catalog.updateAvailability(bookId, false);
            System.out.println("Book marked as unavailable.");
        } else {
            System.out.println("Invalid input.");
        }
    }

    private static boolean isValidGenre(String genre) {
        String[] validGenres = {"Fiction", "Non-Fiction", "Science", "History"};
        for (String g : validGenres) {
            if (g.equalsIgnoreCase(genre)) return true;
        }
        return false;
    }

    private static int getIntInput(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}