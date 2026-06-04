package it.unicam.cs.mpgc.rpg126164.services;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayableCharacter;
import it.unicam.cs.mpgc.rpg126164.domain.characters.stats.Archetype;
import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.GameState;
import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.SaveManager;
import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.WorldGame;
import it.unicam.cs.mpgc.rpg126164.persistance.repositories.*;

/**
 * This class initializes a game service, the base handler for this game. It receives all the repositories
 */
public class GameService {

    private final SaveManager saveManager;

    /**
     * Creates a game service
     * @param saveManager the save slot
     */
    public GameService(SaveManager saveManager) {

        if (saveManager == null)
            throw new IllegalArgumentException("Invalid parameters");

        this.saveManager = saveManager;
    }

    /**
     * Creates a new gameplay
     * @param name the name of the getPlayer's character
     * @param description its description
     * @param archetype its chosen archetype
     * @return the new world game
     */
    public PlayableCharacter createNewGame(String name, String description, Archetype archetype) {
        return new PlayableCharacter(name, description, archetype);
    }

    /**
     * Creates a gameplay, based on the saved data in the save slot
     * @return the last saved world game, according to the loaded game state
     */
    public GameState loadGame() {
        GameState gameState = saveManager.load();
        if (gameState == null) throw new IllegalStateException("No saved game found");
        return gameState;
    }

    /**
     * Saves the current game state
     * @param worldGame the world game to save
     */
    public void saveGame(WorldGame worldGame) { worldGame.save(); }

    /**
     * Deletes the game state saved in the last game session
     */
    public void deleteSaveSlot() { saveManager.delete(); }

    /**
     * Closes the current game play
     * @param worldGame the world to close
     */
    public void exit(WorldGame worldGame) { worldGame.exit(); }
}

