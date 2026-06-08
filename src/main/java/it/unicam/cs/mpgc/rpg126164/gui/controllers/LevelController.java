package it.unicam.cs.mpgc.rpg126164.gui.controllers;

import it.unicam.cs.mpgc.rpg126164.gui.GameSession;
import it.unicam.cs.mpgc.rpg126164.services.LevelService;

public class LevelController {

    private final LevelService levelService;
    private final GameSession session;

    public LevelController(LevelService levelService, GameSession session) {
        this.levelService = levelService;
        this.session = session;
    }
}
