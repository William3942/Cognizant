package ContactManagementApplication;

/**
 *
 * @author Guillermo
 */
import java.util.Scanner;

public class ContactManager {
    public static void main(String[] args) {
        ContactDirectory directory = new ContactDirectory();
        Scanner scanner = new Scanner(System.in);
        int option;
        int contactIdCounter = 1;

        do {
            System.out.println("\n=== CONTACT MANAGER MENU ===");
            System.out.println("1. Agregar contacto");
            System.out.println("2. Actualizar contacto");
            System.out.println("3. Buscar contacto por nombre");
            System.out.println("4. Mostrar todos los contactos");
            System.out.println("5. Mostrar contactos ordenados por nombre");
            System.out.println("6. Mostrar tipos únicos de contacto");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Nombre: ");
                    String name = scanner.nextLine();
                    System.out.print("Teléfono: ");
                    String phone = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Tipo (Personal/Profesional): ");
                    String type = scanner.nextLine();

                    Contact newContact = new Contact(contactIdCounter++, name, phone, email, type);
                    directory.addContact(newContact);
                    System.out.println("Contacto agregado correctamente.");
                    break;

                case 2:
                    System.out.print("Nombre del contacto a actualizar: ");
                    String updateName = scanner.nextLine();
                    System.out.print("Nuevo teléfono: ");
                    String newPhone = scanner.nextLine();
                    System.out.print("Nuevo email: ");
                    String newEmail = scanner.nextLine();
                    System.out.print("Nuevo tipo: ");
                    String newType = scanner.nextLine();

                    directory.updateContact(updateName, newPhone, newEmail, newType);
                    break;

                case 3:
                    System.out.print("Nombre del contacto a buscar: ");
                    String searchName = scanner.nextLine();
                    directory.searchContactByName(searchName);
                    break;

                case 4:
                    directory.displayContacts();
                    break;

                case 5:
                    directory.displaySortedContacts();
                    break;

                case 6:
                    directory.displayContactTypes();
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }

        } while (option != 0);

        scanner.close();
    }
}


