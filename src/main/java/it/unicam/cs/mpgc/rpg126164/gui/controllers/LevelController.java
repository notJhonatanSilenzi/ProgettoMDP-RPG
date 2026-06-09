package it.unicam.cs.mpgc.rpg126164.gui.controllers;

import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.combat.Fight;
import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.WorldGame;
import it.unicam.cs.mpgc.rpg126164.gui.GameSession;
import it.unicam.cs.mpgc.rpg126164.services.LevelService;

/**
 * This class works as a controller for the level service
 */
public class LevelController {

    private final LevelService levelService;
    private final GameSession session;

    /**
     * Creates a level controller
     * @param levelService the level service
     * @param session the current game session
     */
    public LevelController(LevelService levelService, GameSession session) {
        this.levelService = levelService;
        this.session = session;
    }

    /**
     * Makes the player enter the level, so starting the fight
     */
    public void enterLevel() {
        Fight fight = levelService.enterLevel(
                this.getWorldGame().getPlayer(),
                this.getWorldGame().getLevelManager()
        );
        this.session.setCurrentFight(fight);
    }

    /**
     * Makes the player move to the next level of the game
     */
    public void moveToNextLevel() {
        levelService.moveToNextLevel(
                this.getWorldGame().getPlayer(),
                this.getWorldGame().getLevelManager()
        );
    }

    /**
     * Makes the player exit the level, so it must return to the world game hub
     */
    public void exit() { levelService.exit(this.getWorldGame().getLevelManager()); }

    /**
     * Returns the world game of this game session
     * @return the current world game
     */
    public WorldGame getWorldGame() { return session.getWorldGame(); }
}
