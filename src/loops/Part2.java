package loops;

import java.util.Scanner;

/**
 *
 * @author Guillermo
 */
public class Part2 {
    public static void main(String[] args) {
        // Prompt the user until a positive integer is entered
        Scanner scanner = new Scanner(System.in);
        int number;
                
        System.out.print("Enter a positive integer: ");
        while (true) {
            number = scanner.nextInt();

            if (number > 0) {
                break;
            } else {
                System.out.print("Invalid input. Please enter a positive integer: ");
            }
        }

        System.out.println("You entered: " + number);
        
        //Sum the Digits:
        System.out.print("Enter a number: ");
        int num = scanner.nextInt();
        int sum = 0;

        while (num > 0) {
            int lastDigit = num % 10; // Extract the last digit
            sum += lastDigit; // Add it to the total sum
            num /= 10; // Remove the last digit
        }

        //Output the Result
        System.out.println("The sum of the digits is: " + sum);
        
        //Optional Enhancements
        int number2;
                
        System.out.print("Enter a positive integer: ");
        while (true) {
            number2 = scanner.nextInt();

            if (number2 > 0) {
                System.out.print("Enter a number: ");
                int num2 = scanner.nextInt();
                int sum2 = 0;

                while (num2 > 0) {
                    int lastDigit2 = num2 % 10; // Extract the last digit
                    sum2 += lastDigit2; // Add it to the total sum
                    num2 /= 10; // Remove the last digit
                }

                //Output the Result
                System.out.println("The sum of the digits is: " + sum2);
            } else {
                System.out.print("Invalid input. Please enter a positive integer: ");
            }
            System.out.println("Do you want continue? Y/N");
            char choose = scanner.next().charAt(0);
            if (choose == 'N' || choose == 'n') {
                break;
            }
        }
        
        scanner.close();
    }
}
