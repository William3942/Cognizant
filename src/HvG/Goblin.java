package HvG;

import java.util.Random;

/**
 *
 * @author Guillermo
 */
public class Goblin extends Entity {
    int hp = 50;
    Item drop;
    Random rand = new Random();

    public Goblin(int x, int y) {
        super(x, y);
        // Assign a random drop
        String[] lootNames = {"Rusty Sword", "Health Potion", "Magic Ring"};
        int[] bonusValues = {5, 0, 10};
        int index = rand.nextInt(lootNames.length);
        this.drop = new Item(lootNames[index], bonusValues[index]);
    }

    @Override
    String getSymbol() {
        return "G";
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void attack(Human human) {
        int damage = rand.nextInt(15) + 3;
        human.hp -= damage;
        System.out.println("Goblin hits Human for " + damage + " damage!");
    }

    public Item dropItem() {
        return drop;
    }
}
