package it.unicam.cs.mpgc.rpg126164.gui.views.utilities;

/**
 * This enum works as a mode selector, in order to show the correct view after ending a fight
 */
public enum ResultView {

    /**
     * The user completed the game, but there's still at least one level to complete still
     */
    LEVEL_COMPLETED,

    /**
     * The user didn't complete the current level
     */
    LEVEL_FAILED,

    /**
     * The user completed the last level
     */
    GAME_COMPLETED
}
