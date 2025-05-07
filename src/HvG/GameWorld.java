package HvG;

import java.util.*;

/**
 *
 * @author Guillermo
 */
public class GameWorld {
    int width, height;
    Human human;
    List<Goblin> goblins = new ArrayList<>();
    List<Treasure> treasures = new ArrayList<>();

    public GameWorld(int width, int height, int numGoblins) {
        this.width = width;
        this.height = height;
        this.human = new Human(width / 2, height / 2);
        spawnGoblins(numGoblins);
    }

    private void spawnGoblins(int numGoblins) {
        Random rand = new Random();
        for (int i = 0; i < numGoblins; i++) {
            int gx = rand.nextInt(width);
            int gy = rand.nextInt(height);
            if (gx == human.x && gy == human.y) {
                i--;
                continue;
            }
            goblins.add(new Goblin(gx, gy));
        }
    }

    public void spawnTreasure() {
        Random rand = new Random();
        int tx = rand.nextInt(width);
        int ty = rand.nextInt(height);
        if (tx == human.x && ty == human.y) return;
        Item item = new Item("Treasure Chest", rand.nextInt(15) + 5);
        treasures.add(new Treasure(tx, ty, item));
        System.out.println("A treasure chest has appeared on the map!");
    }

    public void display() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (human.x == x && human.y == y) {
                    System.out.print(human.getSymbol());
                } else {
                    boolean printed = false;
                    for (Goblin goblin : goblins) {
                        if (goblin.x == x && goblin.y == y && goblin.isAlive()) {
                            System.out.print(goblin.getSymbol());
                            printed = true;
                            break;
                        }
                    }
                    if (!printed) {
                        for (Treasure treasure : treasures) {
                            if (treasure.x == x && treasure.y == y) {
                                System.out.print(treasure.getSymbol());
                                printed = true;
                                break;
                            }
                        }
                    }
                    if (!printed) {
                        System.out.print(".");
                    }
                }
            }
            System.out.println();
        }
    }

    public void moveHuman(String direction) {
        switch (direction.toLowerCase()) {
            case "w": if (human.y > 0) human.y--; break;
            case "s": if (human.y < height - 1) human.y++; break;
            case "d": if (human.x < width - 1) human.x++; break;
            case "a": if (human.x > 0) human.x--; break;
            default: System.out.println("Invalid move.");
        }
    }

    public Goblin checkForCombat() {
        for (Goblin goblin : goblins) {
            if (goblin.isAlive() && goblin.x == human.x && goblin.y == human.y) {
                return goblin;
            }
        }
        return null;
    }

    public Treasure checkForTreasure() {
        Iterator<Treasure> iter = treasures.iterator();
        while (iter.hasNext()) {
            Treasure t = iter.next();
            if (t.x == human.x && t.y == human.y) {
                iter.remove();
                return t;
            }
        }
        return null;
    }
}

