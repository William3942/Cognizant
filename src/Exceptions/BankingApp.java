package Exceptions;

import java.util.Scanner;

/**
 *
 * @author Guillermo
 */
public class BankingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = null;

        try {
            System.out.print("Enter account number: ");
            String accountNumber = scanner.nextLine();

            System.out.print("Enter account holder name: ");
            String accountHolder = scanner.nextLine();

            System.out.print("Enter initial balance: ");
            double initialBalance = scanner.nextDouble();

            account = new BankAccount(accountNumber, accountHolder, initialBalance);
            System.out.println("\nAccount created successfully!");
            account.displayAccountInfo();

            boolean exit = false;

            while (!exit) {
                System.out.println("\n=== Banking Menu ===");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit Funds");
                System.out.println("3. Withdraw Funds");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        account.displayAccountInfo();
                        break;

                    case 2:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        try {
                            account.deposit(depositAmount);
                        } catch (InvalidAmountException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 3:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = scanner.nextDouble();
                        try {
                            account.withdraw(withdrawAmount);
                        } catch (InvalidAmountException | InsufficientFundsException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 4:
                        exit = true;
                        System.out.println("Thank you for using the banking app!");
                        break;

                    default:
                        System.out.println("Invalid option. Please choose again.");
                }
            }

        } catch (InvalidAmountException e) {
            System.out.println("Failed to create account: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

