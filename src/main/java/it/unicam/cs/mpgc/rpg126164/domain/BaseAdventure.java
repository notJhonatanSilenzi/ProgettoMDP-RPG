package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.Level;
import it.unicam.cs.mpgc.rpg126164.abstractions.LevelManager;

import java.util.List;

/**
 * This class represents a basic implementation of the LevelManager interface, managing the progression
 * of a player through a series of levels in an adventure game. It keeps track of the current level,
 * the player's progress, and allows for entering and exiting the adventure.
 */
public class BaseAdventure implements LevelManager {

    private PlayableCharacter player;
    private final List<Level> levels;
    private Level currentLevel;
    private int progressionPercentage;

    /**
     * Creates a basic adventure for a world game
     * @param levels the levels to complete in order to complete this game mode
     */
    public BaseAdventure(List<Level> levels) {
        if (levels == null || levels.isEmpty()) throw new IllegalArgumentException("Levels cannot be null or empty.");

        this.player = null;
        this.levels = levels;
        this.currentLevel = levels.getFirst();
        this.progressionPercentage = 0;
    }

    @Override
    public void enter(PlayableCharacter character) {
        if (this.player != null) throw new IllegalArgumentException("An adventure is already in progress.");

        this.player = character;
    }

    @Override
    public void nextLevel() {
        if (this.currentLevel.playerHasWon()) {
            this.updateProgression();
            this.currentLevel = levels.get(levels.indexOf(currentLevel) + 1);
        }
    }

    /**
     * Updates the progression of the player in this mode, according to the completed levels
     */
    private void updateProgression() {
        this.progressionPercentage = this.levels.indexOf(currentLevel) * 100 / this.levels.size();
    }

    @Override
    public void exit() {
        if (this.player == null) throw new IllegalArgumentException("No adventure in progress.");

        this.player = null;
    }


    // GETTERS

    public PlayableCharacter getPlayer() { return player; }

    public List<Level> getLevels() { return levels; }

    public Level getCurrentLevel() { return currentLevel; }

    public int getProgressionPercentage() { return progressionPercentage; }
}
