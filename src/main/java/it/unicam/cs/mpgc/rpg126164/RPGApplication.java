package it.unicam.cs.mpgc.rpg126164;

import it.unicam.cs.mpgc.rpg126164.gui.GameSession;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.*;
import it.unicam.cs.mpgc.rpg126164.gui.views.scenes.MainMenuView;
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

    /**
     * Sets the game service at the build of the application
     * @param gameService the game service to set
     */
    public static void setGameService(GameService gameService) { RPGApplication.gameService = gameService; }

    /**
     * Sets the world service at the build of the application
     * @param worldService the world service to set
     */
    public static void setWorldService(WorldService worldService) { RPGApplication.worldService = worldService; }

    /**
     * Sets the market service at the build of the application
     * @param marketService the market service to set
     */
    public static void setMarketService(MarketService marketService) { RPGApplication.marketService = marketService; }

    /**
     * Sets the level service at the build of the application
     * @param levelService the level service to set
     */
    public static void setLevelService(LevelService levelService) { RPGApplication.levelService = levelService; }

    /**
     * Sets the combat service at the build of the application
     * @param combatService the combat service to set
     */
    public static void setCombatService(CombatService combatService) { RPGApplication.combatService = combatService; }

    /**
     * Sets the game session at the build of the application
     * @param session the game session to set
     */
    public static void setGameSession(GameSession session) { RPGApplication.gameSession = session; }

    /**
     * Runs the application
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage stage) {
        MenuController menuController = new MenuController(gameService, gameSession);
        WorldController worldController = new WorldController(worldService, gameSession);
        MarketController marketController = new MarketController(marketService, gameSession);
        LevelController levelController =  new LevelController(levelService, gameSession);
        CombatController combatController = new CombatController(combatService, gameSession);

        MainMenuView menu = new MainMenuView(menuController, worldController, marketController, levelController, combatController);
        stage.setScene(menu.createScene(stage));
        stage.setTitle("RPG Game");
        stage.show();
    }
}
