package introduccion2Java;

import java.util.Scanner;

/**
 *
 * @author Guillermo
 */
public class Part3 {
    public static void main(String[] args) {
        //Parte 3
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingresa tu nombre: ");
        String firstName = scanner.nextLine();
        
        System.out.print("Ingresa tu apellido: ");
        String lastName = scanner.nextLine();
        
        String fullName = firstName + " " + lastName;
        String upperCaseFullName = fullName.toUpperCase();
        
        char firstChar = upperCaseFullName.charAt(0);
        int count = 0;
        for (char c : upperCaseFullName.toCharArray()) {
            if (c == firstChar) {
                count++;
            }
        }
        
        System.out.println("Nombre completo: " + fullName);
        System.out.println("Nombre en mayusculas: " + upperCaseFullName);
        System.out.println("El primer caracter '" + firstChar + "' aparece " + count + " veces.");
        scanner.close();
    }
    
}
