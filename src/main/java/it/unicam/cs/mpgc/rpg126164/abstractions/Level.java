package it.unicam.cs.mpgc.rpg126164.abstractions;

import it.unicam.cs.mpgc.rpg126164.domain.PlayableCharacter;

/**
 * This interface represents a generic level in the world game, and it refers to a fight. It contains different
 * methods: starting the level, processing a turn, verifying if the fight is over, resetting and giving a price
 * to the player if the level is completed.
 */
public interface Level {

    /**
     * Starts this level, allowing to process actions in this level
     */
    void startLevel();

    /**
     * Processes a turn through the given game action, passing it to the fight field in the class
     * @param gameAction the action to process
     */
    void processTurn(GameAction gameAction);

    /**
     * Checks if the player has won the fight
     * @return true if the player won the fight, false otherwise
     */
    boolean playerHasWon();

    /**
     * Checks if the player has lost the fight
     * @return true if the player lost the fight, false otherwise
     */
    boolean playerHasLost();

    /**
     * Resets the level to the initial status, in case of loss
     */
    void reset();
}
