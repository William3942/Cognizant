package HvG;

import java.util.Scanner;

/**
 *
 * @author Guillermo
 */
public class HumansVsGoblinsGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameWorld world = new GameWorld(10, 10, 5);

        while (world.human.isAlive()) {
            world.display();
            System.out.println("Human HP: " + world.human.hp);
            world.human.showInventory();
            System.out.print("Move (a/w/s/d): ");
            String move = scanner.nextLine();

            world.moveHuman(move);

            Goblin enemy = world.checkForCombat();
            if (enemy != null) {
                System.out.println("Combat starts with a Goblin!");
                while (world.human.isAlive() && enemy.isAlive()) {
                    world.human.attack(enemy);
                    if (enemy.isAlive()) {
                        enemy.attack(world.human);
                    } else {
                        System.out.println("Goblin defeated!");
                        world.human.addItem(enemy.dropItem());
                        world.spawnTreasure();
                    }
                }
                if (!world.human.isAlive()) {
                    System.out.println("You have been slain by a Goblin!");
                    break;
                }
            }

            Treasure chest = world.checkForTreasure();
            if (chest != null) {
                System.out.println("You found a treasure chest!");
                world.human.addItem(chest.treasureItem);
            }

            if (world.goblins.stream().noneMatch(Goblin::isAlive)) {
                System.out.println("All goblins defeated! You win!");
                break;
            }
        }
    }
}
