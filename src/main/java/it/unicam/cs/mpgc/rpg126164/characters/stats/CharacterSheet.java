package it.unicam.cs.mpgc.rpg126164.characters.stats;

/**
 * This interface allows to any object that implements it to work as a character sheet. The sheet contains all the
 * stats, the methods to maintain stats during time and fights, and to check if the character is alive or not.
 * This sheet refers exclusively to character that are able to fight with other character
 */
public interface CharacterSheet {

    /**
     * This method allows to get damage from other fighting characters
     * @param damage the hp that this character loses
     */
    void damage(int damage);

    /**
     * This method allows this character to heal himself
     * @param heal the hp that this character gains
     */
    void heal(int heal);

    /**
     * This method allows to increase the attack of this character
     * @param increase the additive of ATK that this character gains
     */
    void increaseAttack(int increase);

    /**
     * This method allows to decrease the attack of this character
     * @param decrease the additive of ATK that this character loses
     */
    void decreaseAttack(int decrease);

    /**
     * This method allows to increase the defense of this character
     * @param increase the additive of DF that this character gains
     */
    void increaseDefense(int increase);

    /**
     * This method allows to decrease the defense of this character
     * @param decrease the additive of DF that this character loses
     */
    void decreaseDefense(int decrease);

    /**
     * This method allows to check if this character is still alive
     * @return true if the character is still alive, false otherwise
     */
    boolean isAlive();

    /**
     * Uses the evadeChance field to determine if the character has evaded during a fight, so he doesn't receive
     * damage during a turn of a fight
     * @return true if the character has evaded, false otherwise
     */
    boolean hasEvaded();

    /**
     * Returns the current amount of HP of this character
     * @return the current amount of HP
     */
    int getHP();

    /**
     * Returns the ATK of this character
     * @return the ATK of this character
     */
    int getATK();

    /**
     * Returns the DF of this character
     * @return the DF of this character
     */
    int getDF();

    /**
     * Returns the evade chance of this character
     * @return the evade chance of this character
     */
    double getEvadeChance();

    /**
     * Resets the sheet by resetting the amount of hp of this fighter to the maximum amount permitted
     */
    void reset();
}
