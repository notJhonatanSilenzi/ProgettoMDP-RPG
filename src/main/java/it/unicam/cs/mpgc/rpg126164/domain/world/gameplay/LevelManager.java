package it.unicam.cs.mpgc.rpg126164.domain.world.gameplay;

import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.Level;

/**
 * This interface represents a generic level manager, and it counts as the world gaming mode. It contains all the
 * levels to proceed with new levels
 */
public interface LevelManager {

    /**
     * This method moves the player to the next level, if the player has completed the current level
     */
    void nextLevel();

    /**
     * Returns the current level that has to be completed by the getPlayer
     * @return the current level
     */
    Level getCurrentLevel();

    /**
     * Sets the current level to the given one
     * @param progress the progression percentage of the game, which corresponds to the last completed
     *                 level
     * @throws IllegalArgumentException if progress is out of bounds according to the levels list size
     */
    void setCurrentLevel(int progress);

    /**
     * Checks if the current level is the last one
     * @return true if the player is at the last level, false otherwise
     */
    boolean isLastLevel();

    /**
     * Returns the current progression percentage of the game
     * @return the current progression percentage of the game
     */
    int getCurrentLevelIndex();
}
