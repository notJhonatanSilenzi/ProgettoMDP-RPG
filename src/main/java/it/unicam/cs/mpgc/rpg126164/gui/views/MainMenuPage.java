package it.unicam.cs.mpgc.rpg126164.gui.views;

import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.GameState;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.*;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class works as a view for the main menu's game
 */
public class MainMenuPage {

    private final MenuController menuController;
    private final WorldController worldController;
    private final MarketController marketController;
    private final LevelController levelController;
    private final CombatController combatController;

    /**
     * Creates a main menu view
     * @param menuController the menu controller
     * @param worldController the world controller
     * @param marketController the market controller
     * @param levelController the level controller
     * @param combatController the combat controller
     */
    public MainMenuPage(MenuController menuController, WorldController worldController, MarketController marketController, LevelController levelController, CombatController combatController) {
        this.menuController = menuController;
        this.worldController = worldController;
        this.marketController = marketController;
        this.levelController = levelController;
        this.combatController = combatController;
    }

    /**
     * Creates a main menu page for the given stage
     * @param stage the current stage
     * @return the main menu page
     */
    public Scene createScene(Stage stage) {
        // ===================================== TITLE =====================================
        Label title = new Label("WELCOME");
        title.setAlignment(Pos.CENTER);

        // ===================================== BUTTONS =====================================
        Label deletedGame = new Label();
        Button newGameButton = newGameActionButton(stage);
        Button loadButton = loadGameActionButton(stage, deletedGame);
        Button deleteButton = deleteGameActionButton(deletedGame);

        Button infoButton = new Button("Info");
        Button exitButton = new Button("Exit");
        infoButton.setOnAction(_ -> {
            InfoPage infoPage = new InfoPage(() -> stage.setScene(createScene(stage)));
            stage.setScene(infoPage.createScene());
        });
        exitButton.setOnAction(_ -> stage.close());

        VBox menuBox = new VBox(15, newGameButton, loadButton, deleteButton, infoButton, exitButton);
        menuBox.setAlignment(Pos.CENTER);

        // ===================================== ROOT =====================================
        BorderPane root = new BorderPane();
        root.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);
        root.setCenter(menuBox);
        root.setBottom(deletedGame);
        BorderPane.setAlignment(deletedGame, Pos.CENTER);

        // ===================================== STYLE =====================================
        InventoryComponentBuilder builder = new InventoryComponentBuilder();
        Scene scene = builder.setStyles(title, newGameButton, loadButton, deleteButton, root);
        infoButton.getStyleClass().add("button");
        exitButton.getStyleClass().add("button");
        deletedGame.getStyleClass().add("floating-text");

        return scene;
    }

    /**
     * Sets up the button to create a new game
     * @param stage the current stage
     * @return the new game button
     */
    Button newGameActionButton(Stage stage) {
        Button button = new Button("New Game");
        button.setOnAction(_ -> {
            CreateCharacterPage ccp = new CreateCharacterPage(
                    menuController,
                    worldController,
                    marketController,
                    levelController,
                    combatController,
                    () -> stage.setScene(createScene(stage)));
            stage.setScene(ccp.createScene(stage));
        });
        return button;
    }

    /**
     * Sets up the load button
     * @param stage the current stage
     * @param deletedGame the floating label for the message
     * @return the load button
     */
    Button loadGameActionButton(Stage stage, Label deletedGame) {
        Button button = new Button("Load Game");
        button.setOnAction(_ -> {
            try {
                GameState gameState = menuController.loadGame();
                worldController.loadWorldGame(gameState);
                WorldGameHubMenu gameHub = new WorldGameHubMenu(
                        menuController,
                        worldController,
                        marketController,
                        levelController,
                        combatController,
                        () -> stage.setScene(createScene(stage)));
                stage.setScene(gameHub.createScene(stage));
            } catch (Exception ex) {
                deletedGame.setText("No save file found");
            }
        });
        return button;
    }

    Button deleteGameActionButton(Label deletedGame) {
        Button button = new Button("Delete Game");
        button.setOnAction(_ -> {
            menuController.clearSaveSlot();
            deletedGame.setText("Game deleted successfully");
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(_ -> deletedGame.setText(""));
            pause.play();
        });
        return button;
    }
}
