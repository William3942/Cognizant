package HvG;

/**
 *
 * @author Guillermo
 */
public class Item {
    String name;
    int bonusDamage;

    public Item(String name, int bonusDamage) {
        this.name = name;
        this.bonusDamage = bonusDamage;
    }

    @Override
    public String toString() {
        return name + " (Bonus Damage: " + bonusDamage + ")";
    }
}
