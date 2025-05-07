package HvG;

/**
 *
 * @author Guillermo
 */
public class Treasure extends Entity {
    Item treasureItem;

    public Treasure(int x, int y, Item item) {
        super(x, y);
        this.treasureItem = item;
    }

    @Override
    String getSymbol() {
        return "T";
    }
}
