package it.unicam.cs.mpgc.rpg126164.services.utils;

/**
 * This record works as a combat turn result to consult and resolve after completing a combat turn in a
 * level fight. It's useful for the UI
 * @param playerTurn the output string for player's turn
 * @param enemyTurn the output string for enemy's turn
 * @param enemyDied the boolean that says if the enemy died or not
 */
public record CombatTurnResult(
    String playerTurn,
    String enemyTurn,
    boolean enemyDied
) {}