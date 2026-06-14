package it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics;

/**
 * This interface represents a generic save manager for this game, which allows to save the progresses
 * of the game. The getPlayer can complete the game in multiple game sessions
 */
public interface SaveManager {

    /**
     * Saves the game, given a game state
     * @param gameState the status of the game to save
     */
    void save(GameState gameState);

    /**
     * Loads the game from the last saved state
     * @return the last saved state of the game
     * @throws IllegalStateException if there's no save file in the game
     */
    GameState load();

    /**
     * Deletes the last saved state
     */
    void delete();
}
