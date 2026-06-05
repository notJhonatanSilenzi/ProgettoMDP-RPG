package it.unicam.cs.mpgc.rpg126164;

import it.unicam.cs.mpgc.rpg126164.gui.GameSession;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.MenuController;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.WorldController;
import it.unicam.cs.mpgc.rpg126164.gui.views.MainMenuPage;
import it.unicam.cs.mpgc.rpg126164.services.GameService;
import it.unicam.cs.mpgc.rpg126164.services.WorldService;
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

    public static void setGameService(GameService gameService) { RPGApplication.gameService = gameService; }

    public static void setWorldService(WorldService worldService) { RPGApplication.worldService = worldService; }

    public static void setGameSession(GameSession session) { RPGApplication.gameSession = session; }

    @Override
    public void start(Stage stage) {
        MenuController menuController = new MenuController(gameService, gameSession);
        WorldController worldController = new WorldController(worldService, gameSession);
        MainMenuPage menu = new MainMenuPage(menuController, worldController);
        stage.setScene(menu.createScene(stage));
        stage.setTitle("RPG Game");
        stage.show();
    }
}
