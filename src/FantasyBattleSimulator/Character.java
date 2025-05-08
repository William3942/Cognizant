package FantasyBattleSimulator;

/**
 *
 * @author Guillermo
 */
public abstract class Character {
    protected String name;
    protected int health;
    protected int attackPower;
    protected int defensePower;
    protected boolean isAlive;

    public Character(String name, int health, int attackPower, int defensePower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.defensePower = defensePower;
        this.isAlive = true;
    }

    public String getName() { return name; }
    public boolean isAlive() { return isAlive; }

    public void receiveDamage(int damage) {
        int reducedDamage = Math.max(damage - defensePower, 0);
        health -= reducedDamage;
        System.out.println(name + " received " + reducedDamage + " damage! Remaining HP: " + health);
        if (health <= 0) {
            isAlive = false;
            System.out.println(name + " has been defeated!");
        }
    }

    public void attack(Character target) throws InvalidActionException {
        if (!this.isAlive) {
            throw new InvalidActionException(this.name + " cannot attack because they are defeated.");
        }
        if (!target.isAlive) {
            throw new InvalidActionException("Cannot attack " + target.name + " because they are already defeated.");
        }
        System.out.println(this.name + " attacks " + target.name + "!");
        target.receiveDamage(this.attackPower);
    }

    public abstract void useAbility(Character target) throws InvalidActionException;
}

