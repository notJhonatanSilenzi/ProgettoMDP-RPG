package it.unicam.cs.mpgc.rpg126164.domain.world.gameplay;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayableCharacter;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.BaseLevel;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.Level;

/**
 * This interface represents a generic level manager, and it counts as the world gaming mode. It contains all the
 * levels to proceed with new levels and also to enter and exit this mode
 */
public interface LevelManager {

    /**
     * Allows the getPlayer to enter this game mode
     * @param character the getPlayer that enters the game mode
     */
    void enter(PlayableCharacter character);

    /**
     * This method moves the getPlayer to the next level, if the getPlayer has completed the current level
     */
    void nextLevel();

    /**
     * This method allows the getPlayer to exit from this game mode
     */
    void exit();

    /**
     * Returns the current level that has to be completed by the getPlayer
     * @return the current level
     */
    Level getCurrentLevel();

    void setCurrentLevel(Level level);

    /**
     * Returns the current progression percentage of the game
     * @return the current progression percentage of the game
     */
    int getProgressionPercentage();

}
