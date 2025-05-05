package introduccion2Java;

import java.util.Scanner;

/**
 *
 * @author Guillermo
 */
public class Part5 {
    public static void main(String[] args) {
        double num1 = 0;
        double num2 = 0;
        double result = 0;
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingresa el primer numero: ");
        num1 = scanner.nextDouble();
        
        System.out.print("Ingresa el segundo numero: ");
        num2 = scanner.nextDouble();
        
        System.out.println("Que operacion quieres realizar: +,-,*,/");
        String operation = scanner.next();
        
        switch (operation) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    System.out.println("Error: División por cero.");
                    return;
                }
                break;
            default:
                System.out.println("Operación no válida.");
                return;
        }
        
        System.out.println("Resultado: " + result);
        
        System.out.print("¿Quieres incrementar (++) o decrementar (--) el resultado? Ingresa ++ o --: ");
        String incrementDecrement = scanner.next();
        
        switch (incrementDecrement) {
            case "++":
                result++;
                break;
            case "--":
                result--;
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
        
        System.out.println("Resultado final: " + result);
        scanner.close();
    }
    
}
