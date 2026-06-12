package it.unicam.cs.mpgc.rpg126164.domain.gamemechanics;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayableCharacter;
import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayerFighter;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.combat.Fight;

/**
 * This interface represents a generic level in the world game, and it refers to a fight. It contains different
 * methods: starting the level, processing a turn, verifying if the fight is over, resetting and giving a price
 * to the getPlayer if the level is completed.
 */
public interface Level {

    /**
     * Allows the player to enter the level
     * @param fight the fight that starts at the enter of the level
     */
    void enterLevel(Fight fight);

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
     * Gives a price to the getPlayer, if they completed the level by winning the fight
     * @param player the getPlayer that receives the price
     */
    ItemStack givePrizeToPlayer(PlayerFighter player);

    /**
     * Sets the given item stack as a prize for completing the level
     * @param prize the prize of the level
     */
    void setPrize(ItemStack prize);

    /**
     * Checks if this level has been completed by the player
     * @return true if the level is completed, false otherwise
     */
    boolean isCompleted();

    /**
     * Returns the id of this level
     * @return the id of this level
     */
    String getId();

    /**
     * Returns the name of the level
     * @return the name of the level
     */
    String getName();

}
