package it.unicam.cs.mpgc.rpg126164;

import it.unicam.cs.mpgc.rpg126164.gui.GameSession;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.*;
import it.unicam.cs.mpgc.rpg126164.gui.views.MainMenuPage;
import it.unicam.cs.mpgc.rpg126164.services.*;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class represents the application of this game, and it contains references to the services and
 * the game session.
 */
public class RPGApplication extends Application {

    private static GameService gameService;
    private static WorldService worldService;
    private static GameSession gameSession;
    private static MarketService marketService;
    private static LevelService levelService;
    private static CombatService combatService;

    public static void setGameService(GameService gameService) { RPGApplication.gameService = gameService; }

    public static void setWorldService(WorldService worldService) { RPGApplication.worldService = worldService; }

    public static void setMarketService(MarketService marketService) { RPGApplication.marketService = marketService; }

    public static void setLevelService(LevelService levelService) { RPGApplication.levelService = levelService; }

    public static void setCombatService(CombatService combatService) { RPGApplication.combatService = combatService; }

    public static void setGameSession(GameSession session) { RPGApplication.gameSession = session; }

    @Override
    public void start(Stage stage) {
        MenuController menuController = new MenuController(gameService, gameSession);
        WorldController worldController = new WorldController(worldService, gameSession);
        MarketController marketController = new MarketController(marketService, gameSession);
        LevelController levelController =  new LevelController(levelService, gameSession);
        CombatController combatController = new CombatController(combatService, gameSession);

        MainMenuPage menu = new MainMenuPage(menuController, worldController, marketController, levelController, combatController);
        stage.setScene(menu.createScene(stage));
        stage.setTitle("RPG Game");
        stage.show();
    }
}
