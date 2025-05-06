package loops;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author Guillermo
 */
public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain;

        do {
            int min = 1;
            int max = 50;
            int randomNumber = random.nextInt(max - min + 1) + min;
            int attempts = 0;
            int maxAttempts = 10;
            int userGuess;
            playAgain = false;

            System.out.println("\nWelcome to the Number Guessing Game!");
            System.out.println("I have selected a number between " + min + " and " + max + ".");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");

                if (scanner.hasNextInt()) {
                    userGuess = scanner.nextInt();
                    attempts++;

                    if (userGuess < min || userGuess > max) {
                        System.out.println("Please enter a number within the range " + min + "-" + max + ".");
                    } else if (userGuess < randomNumber) {
                        System.out.println("Too low! Try again.");
                    } else if (userGuess > randomNumber) {
                        System.out.println("Too high! Try again.");
                    } else {
                        System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                        break;
                    }
                } else {
                    System.out.println("Invalid input. Please enter an integer.");
                    scanner.next();
                }

                if (attempts == maxAttempts) {
                    System.out.println("Sorry, you've reached the maximum attempts. The number was " + randomNumber + ".");
                }
            }

            System.out.print("\nDo you want to play again? (yes/no): ");
            String response = scanner.next().toLowerCase();
            if (response.equals("yes")) {
                playAgain = true;
            }

        } while (playAgain);

        System.out.println("Thanks you so much for played my game");
        scanner.close();
    }
}
