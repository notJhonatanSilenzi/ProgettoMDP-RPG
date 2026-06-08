package it.unicam.cs.mpgc.rpg126164;

import it.unicam.cs.mpgc.rpg126164.gui.GameSession;
import it.unicam.cs.mpgc.rpg126164.services.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * In this class, the game gets initialized through the game bootstrap. It also initializes all the
 * services and the game session, and it launches the application
 */
public class Main {

    public static void main(String[] args) {

        GameBootstrap bootstrap = new GameBootstrap();
        GameService gameService = bootstrap.initGamePlay();
        WorldService worldService = bootstrap.initWorldGame();
        MarketService marketService = bootstrap.initMarket();
        LevelService levelService = bootstrap.initLevelManager();
        CombatService combatService = bootstrap.initCombat();
        GameSession session = new GameSession();

        RPGApplication.setGameService(gameService);
        RPGApplication.setWorldService(worldService);
        RPGApplication.setMarketService(marketService);
        RPGApplication.setLevelService(levelService);
        RPGApplication.setCombatService(combatService);
        RPGApplication.setGameSession(session);

        Application.launch(RPGApplication.class, args);
    }
}
