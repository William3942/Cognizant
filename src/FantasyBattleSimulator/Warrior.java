package FantasyBattleSimulator;

/**
 *
 * @author Guillermo
 */
public class Warrior extends Character {
    public Warrior(String name) {
        super(name, 150, 20, 10);
    }

    @Override
    public void useAbility(Character target) throws InvalidActionException {
        if (!this.isAlive) throw new InvalidActionException(this.name + " is defeated and cannot use abilities.");
        if (!target.isAlive) throw new InvalidActionException("Target " + target.name + " is already defeated.");
        System.out.println(this.name + " uses Power Strike on " + target.name + "!");
        target.receiveDamage(this.attackPower + 15);
    }
}

