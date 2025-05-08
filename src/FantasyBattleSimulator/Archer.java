package FantasyBattleSimulator;

/**
 *
 * @author Guillermo
 */
public class Archer extends Character {
    public Archer(String name) {
        super(name, 120, 18, 8);
    }

    @Override
    public void useAbility(Character target) throws InvalidActionException {
        if (!this.isAlive) throw new InvalidActionException(this.name + " is defeated and cannot use abilities.");
        if (!target.isAlive) throw new InvalidActionException("Target " + target.name + " is already defeated.");
        System.out.println(this.name + " fires Piercing Arrow at " + target.name + "!");
        target.receiveDamage(this.attackPower + 10);
    }
}

