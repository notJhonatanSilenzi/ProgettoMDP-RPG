package it.unicam.cs.mpgc.rpg126164.domain.gamemechanics;

import it.unicam.cs.mpgc.rpg126164.domain.characters.Fighter;
import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayableCharacter;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.combat.GameAction;

import java.util.Set;

/**
 * This interface represents a generic level in the world game, and it refers to a fight. It contains different
 * methods: starting the level, processing a turn, verifying if the fight is over, resetting and giving a price
 * to the getPlayer if the level is completed.
 */
public interface Level {

    /**
     * Starts this level, allowing to process actions in this level
     */
    void startLevel(PlayableCharacter player, Set<Fighter> enemies, ItemStack price);

    /**
     * Processes a turn through the given game action, passing it to the fight field in the class
     * @param gameAction the action to process
     */
    void processTurn(GameAction gameAction);

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
     * Returns the id of this level
     * @return the id of this level
     */
    String getId();
}
