package it.unicam.cs.mpgc.rpg126164.domain.world.gameplay;

import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.Level;

import java.util.List;

/**
 * This class represents a basic implementation of the LevelManager interface, managing the progression
 * of a getPlayer through a series of levels in an adventure game. It keeps track of the current level,
 * the getPlayer's progress, and allows for entering and exiting the adventure.
 */
public class BaseAdventure implements LevelManager {

    private final List<Level> levels;
    private Level currentLevel;
    private int currentLevelIndex;

    /**
     * Creates a basic adventure for a world game
     * @param levels the levels to complete in order to complete this game mode
     */
    public BaseAdventure(List<Level> levels) {
        if (levels == null || levels.isEmpty()) throw new IllegalArgumentException("Levels cannot be null or empty.");

        this.levels = levels;
        this.currentLevel = levels.getFirst();
        this.currentLevelIndex = 0;
    }

    @Override
    public void nextLevel() {
        if (this.currentLevel.isCompleted()) {
            currentLevelIndex += 1;
            this.currentLevel = levels.get(currentLevelIndex);
        }
    }

    @Override
    public boolean isLastLevel() { return this.currentLevelIndex == levels.size() - 1; }


    // GETTERS

    public List<Level> getLevels() { return levels; }

    @Override
    public Level getCurrentLevel() { return currentLevel; }

    @Override
    public int getCurrentLevelIndex() { return currentLevelIndex; }

    @Override
    public void setCurrentLevel(int progress) {
        if (progress < 0 || progress >= levels.size()) throw new IllegalArgumentException("Invalid progress.");

        this.currentLevelIndex = progress;
        this.currentLevel = levels.get(currentLevelIndex);
    }
}
