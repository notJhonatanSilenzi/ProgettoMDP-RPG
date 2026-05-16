package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.CharacterSheet;
import it.unicam.cs.mpgc.rpg126164.abstractions.InventoryBehaviour;

/**
 * This enum contains all the possible archetypes for player characters and enemies. This enum also helps
 * to create the character sheet for each archetype, with the method getSheet() that returns a new FighterSheet
 * with the stats of the archetype.
 */
public enum Archetype {
    /**
     * The archetype that works as a tank. High HP, High DF, Defense Buff
     */
    WARRIOR(120, 14, 10),

    /**
     * The archetype that works as a rogue or an assassin. High ATK, low DF and HP, Can avoid enemies' attack
     */
    BERSERKER(95, 20, 5),

    /**
     * The archetype that works as a utility and support. Balanced stats, can heal himself
     */
    CLERIC(110, 12, 8),

    /**
     * The archetype that works as the magician. High ATK, low DF and HP, Attack Buff
     */
    SORCERER(80, 24, 3);

    private final int HP;
    private final int ATK;
    private final int DEF;

    /**
     * Constructor for this archetype
     * @param hp the hp
     * @param atk the standard atk
     * @param def the standard df
     */
    Archetype(int hp, int atk, int def) {
        this.HP = hp;
        this.ATK = atk;
        this.DEF = def;
    }

    /**
     * This method returns a new FighterSheet with the stats of this archetype. This method is used to create the
     * sheet given the archetype for a playable character
     * @return the fighter sheet for the character
     */
    public CharacterSheet getSheet() {
        return new FighterSheet(this.HP, this.HP, this.ATK, this.DEF);
    }

    /**
     * Returns a new FighterSheet for an enemy, given the archetype and the type. The multiplier allows to adjust
     * the stats given the type of enemy
     * @param multiplier the multiplier for the stats
     * @return a FighterSheet for an enemy
     */
    public CharacterSheet getSheet(double multiplier) {
        return new FighterSheet(
                (int) (this.HP * multiplier),
                (int) (this.HP * multiplier),
                (int) (this.ATK * multiplier),
                (int) (this.DEF * multiplier));
    }

    /**
     * Returns a specific inventory for this character, given the archetype
     * @return the initial inventory for this character
     */
    public InventoryBehaviour getInventory() { return InventoryBuilder.getInitialInventory(this); }
}
