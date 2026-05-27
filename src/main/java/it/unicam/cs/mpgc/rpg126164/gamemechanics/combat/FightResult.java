package it.unicam.cs.mpgc.rpg126164.gamemechanics.combat;

/**
 * This enum represents the status of a fight between a player and a set of enemies
 */
public enum FightResult {
    /**
     * This status is reached if the player is still alive and has defeated all enemies, and it cannot be updated
     * anymore. It remains permanent
     */
    WIN,

    /**
     * This status is reached if the player died during the fight, and it remains permanent
     */
    LOSE,

    /**
     * This status notifies that the player is still alive and is fighting with at least one enemy, and it
     * can be updated to WIN or LOSE, depending on the events
     */
    ON_GOING
}
