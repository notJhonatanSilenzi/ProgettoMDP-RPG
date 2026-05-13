package it.unicam.cs.mpgc.rpg126164.domain;

/**
 * This enum contains all the possible archetypes for player characters and enemies
 */
public enum Archetype {
    /**
     * The archetype that works as a tank. High HP, High DF, Defense Buff
     */
    WARRIOR,

    /**
     * The archetype that works as a rogue or an assassin. High ATK, low DF and HP, Can avoid enemies' attack
     */
    BERSERKER,

    /**
     * The archetype that works as an utility and support. Balanced stats, can heal himself
     */
    CLERIC,

    /**
     * The archetype that works as the magician. High ATK, low DF and HP, Attack Buff
     */
    SORCERER
}
