package HvG;

/**
 *
 * @author Guillermo
 */
public class Land extends Entity {
    public Land(int x, int y) {
        super(x, y);
    }

    @Override
    String getSymbol() {
        return "."; // Dot for empty land
    }
}
