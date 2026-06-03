package it.unicam.cs.mpgc.rpg126164;

import it.unicam.cs.mpgc.rpg126164.services.GameBootstrap;
import it.unicam.cs.mpgc.rpg126164.services.GameService;

/**
 * In this class, the game gets initialized through the game bootstrap
 */
public class Main {

    public static void main(String[] args) {

        GameBootstrap bootstrap = new GameBootstrap();
        GameService gameService = bootstrap.init();
    }
}
