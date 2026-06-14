package it.unicam.cs.mpgc.rpg126164.domain.characters.stats;

import java.io.Serializable;

/**
 * This class represents a base Sheet for a character able to fight, and it contains all the stats and methods
 * to maintain and edit the stats throughout the time and the fights. The only stats are HP, HP_MAX, ATK, DF
 * and evade chance. The Javadoc for the implemented methods are in the interface
 */
public class FighterSheet implements CharacterSheet, Serializable {

    private int HP;
    private int ATK;
    private int DF;
    private final double evadeChance;

    private final int HP_MAX;
    private final int ATK_BASE;
    private final int DF_BASE;

    /**
     * Creates a Fighter Sheet for a character able to fight
     * @param HP the amount of hp
     * @param HP_MAX the maximum amount of hp reachable
     * @param ATK the attack
     * @param DF the defense
     * @param evadeChance the chance to evade an attack, between 0 and 1
     * @throws IllegalArgumentException if any of the parameters is 0, and if evade chance isn't
     * between 0 and 1
     */
    public FighterSheet(int HP, int HP_MAX, int ATK, int DF, double evadeChance) {
        if (HP_MAX <= 0 || ATK <= 0 || DF <= 0 || HP != HP_MAX || evadeChance < 0 || evadeChance > 1)
            throw new IllegalArgumentException("Arguments are invalid");

        this.HP = HP;
        this.ATK = ATK;
        this.DF = DF;
        this.evadeChance = evadeChance;
        this.HP_MAX = HP_MAX;
        this.ATK_BASE = ATK;
        this.DF_BASE = DF;
    }

    @Override
    public void damage(int damage) {
        checkNotBelowZero(damage);
        if (damage > this.HP) this.HP = 0;
        else this.HP -= damage;
    }

    @Override
    public void heal(int heal) {
        checkNotBelowZero(heal);
        if (heal + this.HP > this.HP_MAX) this.HP = this.HP_MAX;
        else this.HP += heal;
    }

    @Override
    public void increaseAttack(int increase) {
        checkNotBelowZero(increase);
        this.ATK += increase;
    }

    @Override
    public void decreaseAttack(int decrease) {
        checkNotBelowZero(decrease);
        this.ATK -= decrease;
    }

    @Override
    public void increaseDefense(int increase) {
        checkNotBelowZero(increase);
        this.DF += increase;
    }

    @Override
    public void decreaseDefense(int decrease) {
        checkNotBelowZero(decrease);
        this.DF -= decrease;
    }

    /**
     * This method checks if the given number is more than zero
     * @param n the number to check
     * @throws IllegalArgumentException if the number is less than or equal to zero
     */
    private void checkNotBelowZero(int n) {
        if (n <= 0) throw new IllegalArgumentException("Argument must be greater than 0");
    }

    @Override
    public boolean isDead() { return this.getHP() <= 0; }

    @Override
    public boolean hasEvaded() {
        if (this.evadeChance == 0) return false;
        return this.evadeChance > Math.random(); // Verify if the attack has been evaded
    }

    @Override
    public void reset() {
        this.HP = this.HP_MAX;
        this.ATK = this.ATK_BASE;
        this.DF = this.DF_BASE;
    }


    // GETTERS

    @Override
    public int getHP() { return HP; }

    @Override
    public int getATK() { return ATK; }

    @Override
    public int getDF() { return DF; }

    @Override
    public double getEvadeChance() { return evadeChance; }
}
