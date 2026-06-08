package it.unicam.cs.mpgc.rpg126164.gui.controllers;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayableCharacter;
import it.unicam.cs.mpgc.rpg126164.domain.characters.stats.Archetype;
import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.GameState;
import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.WorldGame;
import it.unicam.cs.mpgc.rpg126164.gui.GameSession;
import it.unicam.cs.mpgc.rpg126164.services.GameService;

/**
 * This class works as a controller for the user interface, dividing it from the game service
 */
public class MenuController {

    private final GameService gameService;
    private final GameSession session;

    /**
     * Creates a menu controller for the game service
     * @param gameService the game service
     * @param session the game session
     */
    public MenuController(GameService gameService, GameSession session) {
        this.gameService = gameService;
        this.session = session;
    }

    /**
     * Creates a new game, means it creates a new character for the user
     * @param name the chosen name
     * @param description the chosen description
     * @param archetype the chosen archetype
     * @return the created player's character
     */
    public PlayableCharacter createNewGame(String name, String description, Archetype archetype) {
        if (name == null || name.isBlank() || description == null || description.isBlank() || archetype == null)
            throw new IllegalArgumentException("Invalid parameters for creating a new game");

        return gameService.createNewGame(name, description, archetype);
    }

    /**
     * Loads the last saved game state
     * @return the last saved game state
     */
    public GameState loadGame() { return gameService.loadGame(); }

    /**
     * Saves the current game state
     */
    public void saveGame() { gameService.saveGame(session.getWorldGame()); }

    /**
     * Cancels the last saved game state
     */
    public void clearSaveSlot() { gameService.deleteSaveSlot(); }

    /**
     * Returns the current world game
     * @return the current world game
     */
    public WorldGame getWorldGame() { return session.getWorldGame(); }
}
