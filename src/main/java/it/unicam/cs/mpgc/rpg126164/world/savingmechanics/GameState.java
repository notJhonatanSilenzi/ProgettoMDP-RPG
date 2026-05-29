package it.unicam.cs.mpgc.rpg126164.world.savingmechanics;

import it.unicam.cs.mpgc.rpg126164.characters.PlayableCharacter;
import it.unicam.cs.mpgc.rpg126164.inventory.InventoryBehaviour;

import java.io.Serializable;

/**
 * This record works as a DTO for serialization for saving the progresses of the game. It contains
 * the player, the id of the current level, and the percentage of progression in the current level.
 * This record is used to save the game state, and to recreate the game state when loading the game.
 * @param player the player's character
 * @param currentLevelId the id of the current level
 * @param progressPercentage the percentage of progression in the game
 */
public record GameState(
        PlayableCharacter player,
        String currentLevelId,
        int progressPercentage,
        InventoryBehaviour emporiumInventory
) implements Serializable {
}
