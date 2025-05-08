package FantasyBattleSimulator;

import java.util.*;

/**
 *
 * @author Guillermo
 */
public class BattleSimulator {
    private static Map<String, Character> characters = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            initCharacters();
            boolean exit = false;
            while (!exit) {
                System.out.println("\n=== Fantasy Battle Simulator ===");
                System.out.println("1. View Characters");
                System.out.println("2. Attack");
                System.out.println("3. Use Ability");
                System.out.println("4. Exit");
                System.out.print("Choose an action: ");

                int choice = scanner.nextInt();
                scanner.nextLine();  // clear newline

                switch (choice) {
                    case 1:
                        viewCharacters();
                        break;
                    case 2:
                        performAttack();
                        break;
                    case 3:
                        performAbility();
                        break;
                    case 4:
                        exit = true;
                        System.out.println("Exiting game. Thanks for playing!");
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            }
        } finally {
            scanner.close();
            System.out.println("Scanner closed.");
        }
    }

    private static void initCharacters() {
        characters.put("Aragorn", new Warrior("Aragorn"));
        characters.put("Gandalf", new Mage("Gandalf"));
        characters.put("Legolas", new Archer("Legolas"));
        System.out.println("Characters initialized: Aragorn (Warrior), Gandalf (Mage), Legolas (Archer)");
    }

    private static void viewCharacters() {
        System.out.println("\n--- Character Status ---");
        for (Character c : characters.values()) {
            System.out.println(c.getName() + " - Alive: " + c.isAlive());
        }
    }

    private static void performAttack() {
        try {
            Character attacker = selectCharacter("attacker");
            Character target = selectCharacter("target");
            attacker.attack(target);
        } catch (CharacterNotFoundException | InvalidActionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void performAbility() {
        try {
            Character attacker = selectCharacter("attacker");
            Character target = selectCharacter("target");
            attacker.useAbility(target);
        } catch (CharacterNotFoundException | InvalidActionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static Character selectCharacter(String role) throws CharacterNotFoundException {
        System.out.print("Enter name of " + role + ": ");
        String name = scanner.nextLine();
        Character c = characters.get(name);
        if (c == null) {
            throw new CharacterNotFoundException("Character " + name + " not found.");
        }
        return c;
    }
}

