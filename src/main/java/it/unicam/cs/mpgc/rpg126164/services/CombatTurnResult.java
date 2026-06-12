package it.unicam.cs.mpgc.rpg126164.services;

public record CombatTurnResult(
    String playerTurn,
    String enemyTurn,
    boolean enemyDied
) {}