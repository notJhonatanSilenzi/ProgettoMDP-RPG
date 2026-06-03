package it.unicam.cs.mpgc.rpg126164.world.gameplay;

import it.unicam.cs.mpgc.rpg126164.characters.PlayableCharacter;
import it.unicam.cs.mpgc.rpg126164.gamemechanics.Level;

/**
 * This interface represents a generic level manager, and it counts as the world gaming mode. It contains all the
 * levels to proceed with new levels and also to enter and exit this mode
 */
public interface LevelManager {

    /**
     * Allows the player to enter this game mode
     * @param character the player that enters the game mode
     */
    void enter(PlayableCharacter character);

    /**
     * This method moves the player to the next level, if the player has completed the current level
     */
    void nextLevel();

    /**
     * This method allows the player to exit from this game mode
     */
    void exit();

    /**
     * Returns the current level that has to be completed by the player
     * @return the current level
     */
    Level getCurrentLevel();

    /**
     * Returns the current progression percentage of the game
     * @return the current progression percentage of the game
     */
    int getProgressionPercentage();

}
