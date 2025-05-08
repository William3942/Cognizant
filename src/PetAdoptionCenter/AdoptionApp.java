package PetAdoptionCenter;

import java.util.Scanner;

/**
 *
 * @author Guillermo
 */
public class AdoptionApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PetAdoptionCenter center = PetAdoptionCenter.loadData("adoption_data.ser");

        boolean running = true;
        while (running) {
            System.out.println("\n=== Pet Adoption Center ===");
            System.out.println("1. Add Pet");
            System.out.println("2. Register Adopter");
            System.out.println("3. View Available Pets");
            System.out.println("4. Adopt Pet");
            System.out.println("5. Save & Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter pet type (Dog/Cat/Bird): ");
                        String type = scanner.nextLine();
                        System.out.print("ID: "); int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Name: "); String name = scanner.nextLine();
                        System.out.print("Age: "); int age = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Breed: "); String breed = scanner.nextLine();

                        Pet pet = null;
                        if (type.equalsIgnoreCase("Dog")) {
                            System.out.print("Is trained (true/false): ");
                            boolean trained = scanner.nextBoolean();
                            pet = new Dog(id, name, age, breed, trained);
                        } else if (type.equalsIgnoreCase("Cat")) {
                            System.out.print("Is indoor (true/false): ");
                            boolean indoor = scanner.nextBoolean();
                            pet = new Cat(id, name, age, breed, indoor);
                        } else if (type.equalsIgnoreCase("Bird")) {
                            System.out.print("Can talk (true/false): ");
                            boolean talk = scanner.nextBoolean();
                            pet = new Bird(id, name, age, breed, talk);
                        } else {
                            throw new InvalidPetDataException("Unknown pet type.");
                        }
                        center.addPet(pet);
                        break;
                    case 2:
                        System.out.print("Adopter ID: "); int aid = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Name: "); String aname = scanner.nextLine();
                        System.out.print("Contact: "); String contact = scanner.nextLine();
                        center.registerAdopter(new Adopter(aid, aname, contact));
                        break;
                    case 3:
                        center.displayAvailablePets();
                        break;
                    case 4:
                        System.out.print("Adopter ID: "); int adId = scanner.nextInt();
                        System.out.print("Pet ID: "); int petId = scanner.nextInt();
                        scanner.nextLine();
                        Adopter adopter = center.adopters.stream().filter(a -> a.adopterId == adId).findFirst().orElseThrow(() -> new Exception("Adopter not found."));
                        Pet petToAdopt = center.findPetById(petId);
                        petToAdopt.adopt(adopter);
                        System.out.println(adopter.getName() + " adopted " + petToAdopt.getName());
                        break;
                    case 5:
                        center.saveData("adoption_data.ser");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}

