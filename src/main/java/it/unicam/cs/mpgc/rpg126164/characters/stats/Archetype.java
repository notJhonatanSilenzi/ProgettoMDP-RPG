package it.unicam.cs.mpgc.rpg126164.characters.stats;

import it.unicam.cs.mpgc.rpg126164.inventory.InventoryBehaviour;
import it.unicam.cs.mpgc.rpg126164.inventory.InventoryBuilder;

/**
 * This enum contains all the possible archetypes for player characters and enemies. This enum also helps
 * to create the character sheet for each archetype, with the method getSheet() that returns a new FighterSheet
 * with the stats of the archetype.
 */
public enum Archetype {
    /**
     * The archetype that works as a tank. High HP, High DF, cannot evade
     */
    WARRIOR(120, 14, 10, 0.0),

    /**
     * The archetype that works as a rogue or an assassin. High ATK, low DF and HP, can avoid enemies attack
     */
    BERSERKER(95, 20, 5, 0.10),

    /**
     * The archetype that works as a utility and support. Balanced stats, can evade more difficulty
     */
    CLERIC(110, 12, 8, 0.05),

    /**
     * The archetype that works as the magician. High ATK, low DF and HP, can evade more easily
     */
    SORCERER(80, 24, 3, 0.15);

    private final int HP;
    private final int ATK;
    private final int DEF;
    private final double evadeChance;

    /**
     * Constructor for this archetype
     * @param hp the hp
     * @param atk the standard atk
     * @param def the standard df
     * @param evadeChance the chance to evade an attack, between 0 and 1
     * @throws IllegalArgumentException if hp, atk or def are negative, or if evadeChance is not between 0 and 1
     */
    Archetype(int hp, int atk, int def, double evadeChance) {
        if (hp <= 0 || atk < 0 || def < 0)
            throw new IllegalArgumentException("HP, ATK and DEF must be positive");
        if (evadeChance < 0 || evadeChance > 1)
            throw new IllegalArgumentException("Evade chance must be between 0 and 1");

        this.HP = hp;
        this.ATK = atk;
        this.DEF = def;
        this.evadeChance = evadeChance;
    }

    /**
     * This method returns a new FighterSheet with the stats of this archetype. This method is used to create the
     * sheet given the archetype for a playable character
     * @return the fighter sheet for the character
     */
    public CharacterSheet getSheet() {
        return new FighterSheet(this.HP, this.HP, this.ATK, this.DEF, this.evadeChance);
    }

    /**
     * Returns a new FighterSheet for an enemy, given the archetype and the type. The multiplier allows to adjust
     * the stats given the type of enemy, and it nulls the evade chance, so that enemies cannot evade
     * @param multiplier the multiplier for the stats
     * @return a FighterSheet for an enemy
     */
    public CharacterSheet getSheet(double multiplier) {
        return new FighterSheet(
                (int) (this.HP * multiplier),
                (int) (this.HP * multiplier),
                (int) (this.ATK * multiplier),
                (int) (this.DEF * multiplier),
                0.0);
    }

    /**
     * Returns a specific inventory for this character, given the archetype
     * @return the initial inventory for this character
     */
    public InventoryBehaviour getInventory() { return InventoryBuilder.getInitialInventory(this); }
}
