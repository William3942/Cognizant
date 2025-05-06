package loops;

import java.util.Scanner;

/**
 *
 * @author Guillermo
 */
public class Part1 {
    public static void main(String[] args) {
        // Print Numbers
        for(int i = 1; i <= 10; i++ ){
            System.out.println(i);
        }
        
        //Calculate Sum:
        int sum = 0;

        for (int i = 1; i <= 10; i++) {
            sum += i;
        }
         System.out.println(sum);  
         
         //Multiplication table
         Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int num = scanner.nextInt();

        System.out.println("Multiplication table of " + num + ":");
        for (int i = 1; i <= 10; i++) {
            System.out.println(num + " x " + i + " = " + (num * i));
        }

        scanner.close();
    }
    
}
