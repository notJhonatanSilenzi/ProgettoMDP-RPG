package it.unicam.cs.mpgc.rpg126164.domain.world.gameplay;

import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.Level;

/**
 * This interface represents a generic level manager, and it counts as the world gaming mode. It contains all the
 * levels to proceed with new levels and also to enter and exit this mode
 */
public interface LevelManager {

    /**
     * This method moves the getPlayer to the next level, if the getPlayer has completed the current level
     */
    void nextLevel();

    /**
     * Returns the current level that has to be completed by the getPlayer
     * @return the current level
     */
    Level getCurrentLevel();

    /**
     * Sets the current level to the given one
     * @param level the current level to set
     */
    void setCurrentLevel(Level level);

    /**
     * Checks if the current level is the last one
     * @return true if the player is at the last level, false otherwise
     */
    boolean isLastLevel();

    /**
     * Returns the current progression percentage of the game
     * @return the current progression percentage of the game
     */
    int getProgressionPercentage();
}
