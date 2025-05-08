package FantasyBattleSimulator;

/**
 *
 * @author Guillermo
 */
public class Mage extends Character {
    public Mage(String name) {
        super(name, 100, 25, 5);
    }

    @Override
    public void useAbility(Character target) throws InvalidActionException {
        if (!this.isAlive) throw new InvalidActionException(this.name + " is defeated and cannot use abilities.");
        if (!target.isAlive) throw new InvalidActionException("Target " + target.name + " is already defeated.");
        System.out.println(this.name + " casts Fireball on " + target.name + "!");
        target.receiveDamage(this.attackPower + 20);
    }
}

