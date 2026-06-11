package it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayableCharacter;
import it.unicam.cs.mpgc.rpg126164.domain.inventory.InventoryBehaviour;

import java.io.Serializable;

/**
 * This record works as a DTO for serialization for saving the progresses of the game. It contains
 * the , the id of the current level, and the percentage of progression in the current level.
 * This record is used to save the game state, and to recreate the game state when loading the game.
 * @param player the player's character
 * @param currentLevelId the id of the current level
 * @param currentLevelIndex the percentage of progression in the game
 * @param emporiumInventory the inventory of the emporium, which is needed to save the game state,
 *                          since the player can buy items from the emporium, and those items need
 *                          to be saved in the game state
 */
public record GameState(
        PlayableCharacter player,
        String currentLevelId,
        int currentLevelIndex,
        InventoryBehaviour emporiumInventory
) implements Serializable {
}
