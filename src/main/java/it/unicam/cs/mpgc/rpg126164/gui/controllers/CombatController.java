package it.unicam.cs.mpgc.rpg126164.gui.controllers;

import it.unicam.cs.mpgc.rpg126164.gui.GameSession;
import it.unicam.cs.mpgc.rpg126164.services.CombatService;

public class CombatController {

    private final CombatService service;
    private final GameSession session;

    public CombatController(CombatService service, GameSession session) {
        this.service = service;
        this.session = session;
    }
}
