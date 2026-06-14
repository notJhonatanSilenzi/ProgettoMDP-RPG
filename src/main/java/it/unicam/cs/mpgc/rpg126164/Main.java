package it.unicam.cs.mpgc.rpg126164;

import it.unicam.cs.mpgc.rpg126164.gui.GameSession;
import it.unicam.cs.mpgc.rpg126164.services.*;
import it.unicam.cs.mpgc.rpg126164.services.utils.GameBootstrap;
import javafx.application.Application;

/**
 * In this class, the game gets initialized through the game bootstrap. It also initializes all the
 * services and the game session, and it launches the application
 */
public class Main {

    /**
     * Builds the game services, the game bootstrap and the game session
     * @param args the args
     */
    static void main(String[] args) {

        // ============================ SERVICES AND SESSION ============================
        GameBootstrap bootstrap = new GameBootstrap();
        GameService gameService = bootstrap.initGamePlay();
        WorldService worldService = bootstrap.initWorldGame();
        MarketService marketService = bootstrap.initMarket();
        LevelService levelService = bootstrap.initLevelManager();
        CombatService combatService = bootstrap.initCombat();
        GameSession session = new GameSession();

        // ============================ APPLICATION SETUP ============================
        RPGApplication.setGameService(gameService);
        RPGApplication.setWorldService(worldService);
        RPGApplication.setMarketService(marketService);
        RPGApplication.setLevelService(levelService);
        RPGApplication.setCombatService(combatService);
        RPGApplication.setGameSession(session);

        Application.launch(RPGApplication.class, args);
    }
}
