package it.unicam.cs.mpgc.rpg126164.domain.gamemechanics;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayableCharacter;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;

/**
 * This interface represents a generic level in the world game, and it refers to a fight. It contains different
 * methods: starting the level, processing a turn, verifying if the fight is over, resetting and giving a price
 * to the getPlayer if the level is completed.
 */
public interface Level {

    /**
     * Checks if the fight has ended. In particular:
     * - it gives the price to the getPlayer, if they won the fight, and signs the level as completed
     * - it resets the level if the getPlayer lost the fight
     * - otherwise it doesn't do anything
     */
    void checkLevelStatus(PlayableCharacter player);

    /**
     * Checks if the getPlayer has won the fight
     * @return true if the getPlayer won the fight, false otherwise
     */
    boolean playerHasWon();

    /**
     * Checks if the getPlayer has lost the fight
     * @return true if the getPlayer lost the fight, false otherwise
     */
    boolean playerHasLost();

    /**
     * Resets the level to the initial status, in case of loss
     */
    void reset();

    /**
     * Sets the given item stack as a prize for completing the level
     * @param prize the prize of the level
     */
    void setPrize(ItemStack prize);

    /**
     * Returns the id of this level
     * @return the id of this level
     */
    String getId();
}
