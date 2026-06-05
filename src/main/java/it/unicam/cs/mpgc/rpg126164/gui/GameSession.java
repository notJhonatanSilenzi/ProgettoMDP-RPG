package it.unicam.cs.mpgc.rpg126164.gui;

import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.WorldGame;

/**
 * This class works as a session, and it contains a reference to the current world game
 */
public class GameSession {

    private WorldGame worldGame;

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
}
