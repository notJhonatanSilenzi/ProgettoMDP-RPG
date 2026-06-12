package it.unicam.cs.mpgc.rpg126164.gui.views;

import it.unicam.cs.mpgc.rpg126164.gui.controllers.*;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class works as a world game hub menu, after creating a world game
 */
public class WorldGameHubMenu {

    private final MenuController menuController;
    private final WorldController worldController;
    private final MarketController marketController;
    private final LevelController levelController;
    private final CombatController combatController;
    private final Runnable onExit;

    /**
     * Creates a world game hub menu
     * @param menuController the menu controller
     * @param worldController the world controller
     * @param marketController the market controller
     * @param levelController the level controller
     * @param combatController the combat controller
     * @param onExit the callback to the main menu
     */
    public WorldGameHubMenu(MenuController menuController, WorldController worldController, MarketController marketController, LevelController levelController, CombatController combatController, Runnable onExit) {
        this.menuController = menuController;
        this.worldController = worldController;
        this.marketController = marketController;
        this.levelController = levelController;
        this.combatController = combatController;
        this.onExit = onExit;
    }

    /**
     * Creates a world game hub menu scene for the given stage
     * @param stage the current stage
     * @return the world game hub menu view
     */
    public Scene createScene(Stage stage) {
        // ===================================== TITLE =====================================
        Label title = new Label("WORLD GAME HUB");
        title.setAlignment(Pos.CENTER);

        // ===================================== BUTTONS =====================================
        Label gameSaved = new Label();

        Button adventure = new Button("Adventure");
        adventure.setOnAction(_ -> openCombat(stage));
        Button market = marketOnAction(stage);
        Button sheet = sheetOnAction(stage);
        Button save = saveOnAction(gameSaved);
        Button exit = new Button("Exit");
        exit.setOnAction(_ -> onExit.run());

        VBox buttons = new VBox(10, adventure, market, sheet, save, exit);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(40));
        buttons.setSpacing(10);

        // ===================================== ROOT =====================================
        BorderPane root = new BorderPane();
        root.setTop(title);
        root.setCenter(buttons);
        root.setBottom(gameSaved);
        BorderPane.setAlignment(gameSaved, Pos.CENTER);
        BorderPane.setAlignment(title, Pos.CENTER);

        // ===================================== STYLE =====================================
        Scene scene = new Scene(root, 800, 600);

        scene.getStylesheets().add("/css/style.css");
        root.getStyleClass().add("hub-root");
        title.getStyleClass().add("hub-title");
        gameSaved.getStyleClass().add("hub-message");
        buttons.getStyleClass().add("hub-panel");
        adventure.getStyleClass().add("hub-button");
        market.getStyleClass().add("hub-button");
        sheet.getStyleClass().add("hub-button");
        save.getStyleClass().add("hub-button");
        exit.getStyleClass().add("hub-button");

        return scene;
    }

    /**
     * Initializes the adventure mode of this game, through a level combat view
     * @param stage the current stage
     */
    private void openCombat(Stage stage) {
        levelController.moveToNextLevel();
        levelController.enterLevel();
        combatController.startFight();
        LevelCombatView combatView = new LevelCombatView(
                levelController,
                combatController,
                () -> openVictory(stage),
                () -> openDefeat(stage)
        );
        stage.setScene(combatView.createScene(stage));
    }

    /**
     * Sets up the victory view, in case of completing a level
     * @param stage the current stage
     */
    private void openVictory(Stage stage) {
        ResultView type = levelController.getWorldGame().getLevelManager().isLastLevel()
                        ? ResultView.GAME_COMPLETED
                        : ResultView.LEVEL_COMPLETED;
        FightResultView view = new FightResultView(
                levelController,
                combatController,
                () -> openCombat(stage),
                () -> stage.setScene(createScene(stage)),
                type
        );
        stage.setScene(view.createScene(stage));
    }

    /**
     * Sets up the defeat view, in case of losing the fight
     * @param stage the current stage
     */
    private void openDefeat(Stage stage) {
        FightResultView view = new FightResultView(
                levelController,
                combatController,
                () -> openCombat(stage),
                () -> stage.setScene(createScene(stage)),
                ResultView.LEVEL_FAILED
        );
        stage.setScene(view.createScene(stage));
    }

    /**
     * Sets up the emporium button, in order to show an emporium view for this game
     * @param stage the current stage
     * @return the emporium button
     */
    private Button marketOnAction(Stage stage) {
        Button market = new Button("Emporium");
        market.setOnAction(_ -> {
            worldController.enterMarket();
            EmporiumView emp = new EmporiumView(
                    worldController,
                    marketController,
                    () -> stage.setScene(createScene(stage))
            );
            stage.setScene(emp.createScene(stage));
        });
        return market;
    }

    /**
     * Sets up the character sheet button, in order to show a character sheet view
     * @param stage the current stage
     * @return the character sheet button
     */
    private Button sheetOnAction(Stage stage) {
        Button sheet = new Button("View Sheet");
        sheet.setOnAction(_ -> {
            CharacterSheetView csv = new CharacterSheetView(
                    worldController,
                    marketController,
                    () -> stage.setScene(createScene(stage))
            );
            stage.setScene(csv.createScene(stage));
        });
        return sheet;
    }

    /**
     * Sets up the save button, in order to save the game
     * @param gameSaved the floating text label
     * @return the save button
     */
    private Button saveOnAction(Label gameSaved) {
        Button save = new Button("Save");
        save.setOnAction(_ -> {
            menuController.saveGame();
            gameSaved.setText("Game saved successfully");
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(_ -> gameSaved.setText(""));
            pause.play();
        });
        return save;
    }
}
