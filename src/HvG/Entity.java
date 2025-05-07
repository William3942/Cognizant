package HvG;

/**
 *
 * @author Guillermo
 */
public abstract class Entity {
    int x, y;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    abstract String getSymbol();
}
