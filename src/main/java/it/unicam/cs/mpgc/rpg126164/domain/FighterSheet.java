package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.CharacterSheet;

/**
 * This class represents a base Sheet for a character able to fight, and it contains all the stats and methods
 * to maintain and edit the stats throughout the time and the fights. The only stats are HP, HP_MAX, ATK and DF.
 * The Javadoc for the implemented methods are in the interface
 */
public class FighterSheet implements CharacterSheet {

    private int HP;
    private final int HP_MAX;
    private int ATK;
    private int DF;

    /**
     * Creates a Fighter Sheet for a character able to fight
     * @param HP the amount of hp
     * @param HP_MAX the maximum amount of hp reachable
     * @param ATK the attack
     * @param DF the defense
     */
    public FighterSheet(int HP, int HP_MAX, int ATK, int DF) {
        if (HP_MAX <= 0 || ATK <= 0 || DF <= 0 || HP != HP_MAX)
            throw new IllegalArgumentException("Arguments are invalid");

        this.HP = HP;
        this.HP_MAX = HP_MAX;
        this.ATK = ATK;
        this.DF = DF;
    }

    @Override
    public void getDamage(int damage) {
        checkNotBelowZero(damage);
        if (damage >= this.HP) this.HP = 0;
        this.HP -= damage;
    }

    @Override
    public void heal(int heal) {
        checkNotBelowZero(heal);
        if (heal + this.HP > this.HP_MAX) this.HP = this.HP_MAX;
        this.HP += heal;
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

    @Override
    public boolean isAlive() { return this.HP != 0; }

    /**
     * This method checks if the given number is more than zero
     * @param n the number to check
     * @throws IllegalArgumentException if the number is less than or equal to zero
     */
    private void checkNotBelowZero(int n) {
        if (n <= 0) throw new IllegalArgumentException("Argument must be greater than 0");
    }


    // GETTERS

    public int getHP() { return HP; }

    public int getATK() { return ATK; }

    public int getDF() { return DF; }
}
