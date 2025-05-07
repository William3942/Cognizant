package HvG;

import java.util.*;

/**
 *
 * @author Guillermo
 */
public class Human extends Entity {
    int hp = 100;
    List<Item> inventory = new ArrayList<>();
    Random rand = new Random();

    public Human(int x, int y) {
        super(x, y);
    }

    @Override
    String getSymbol() {
        return "H";
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void attack(Goblin goblin) {
        int baseDamage = rand.nextInt(20) + 5;
        int bonus = inventory.stream().mapToInt(item -> item.bonusDamage).sum();
        int totalDamage = baseDamage + bonus;
        goblin.hp -= totalDamage;
        System.out.println("Human hits Goblin for " + totalDamage + " damage!");
    }

    public void addItem(Item item) {
        inventory.add(item);
        System.out.println("Picked up: " + item);
    }

    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Inventory:");
            for (Item item : inventory) {
                System.out.println(" - " + item);
            }
        }
    }
}
