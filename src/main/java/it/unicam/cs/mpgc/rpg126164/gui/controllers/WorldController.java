package it.unicam.cs.mpgc.rpg126164.gui.controllers;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayableCharacter;
import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.GameState;
import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.SaveManager;
import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.SaveSlot;
import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.WorldGame;
import it.unicam.cs.mpgc.rpg126164.gui.GameSession;
import it.unicam.cs.mpgc.rpg126164.services.WorldService;

/**
 * This class works as a controller for the user interface, separating it from the world service
 */
public class WorldController {

    private final WorldService worldService;
    private final GameSession session;

    /**
     * Creates a world controller
     * @param worldService the world service
     * @param session the game session
     */
    public WorldController(WorldService worldService, GameSession session) {
        this.worldService = worldService;
        this.session = session;
    }

    /**
     * Creates a new world game
     * @param player the player that has been created recently
     */
    public void createWorld(PlayableCharacter player) {
        WorldGame world = worldService.buildNewWorldGame(player);
        session.setWorldGame(world);
    }

    /**
     * Creates a world game, based on the last saved game state
     * @param gameState the last saved game state
     */
    public void loadWorldGame(GameState gameState) {
        WorldGame world = worldService.buildSavedWorldGame(gameState);
        session.setWorldGame(world);
    }

    /**
     * allows the user to quit the game hub, and return to the main menu
     */
    public void exitGame() { worldService.exit(session.getWorldGame()); }

    /**
     * Returns the current world game
     * @return the current world game
     */
    public WorldGame getWorldGame() { return session.getWorldGame(); }
}
