package it.unicam.cs.mpgc.rpg126164.gui;

import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.combat.Fight;
import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.WorldGame;

/**
 * This class works as a session, and it contains a reference to the current world game
 */
public class GameSession {

    private WorldGame worldGame;
    private Fight currentFight;

    /**
     * Returns the world game of this session
     * @return the world game of this session
     */
    public WorldGame getWorldGame() { return worldGame; }

    /**
     * Sets the world game of this session to the recently built world game
     * @param worldGame the world game that has been built
     */
    public void setWorldGame(WorldGame worldGame) { this.worldGame = worldGame; }

    /**
     * Returns the current fight during this game
     * @return the current fight
     */
    public Fight getCurrentFight() { return currentFight; }

    /**
     * Updates the current fight of this game session
     * @param currentFight the new current fight
     */
    public void setCurrentFight(Fight currentFight) { this.currentFight = currentFight; }

    /**
     * clears the current fight field from any value
     */
    public void clearCurrentFight() { this.currentFight = null; }
}
