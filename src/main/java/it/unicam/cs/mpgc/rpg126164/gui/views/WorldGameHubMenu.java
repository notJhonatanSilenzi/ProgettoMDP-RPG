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

import java.util.Objects;

public class WorldGameHubMenu {

    private final MenuController menuController;
    private final WorldController worldController;
    private final MarketController marketController;
    private final LevelController levelController;
    private final CombatController combatController;
    private final Runnable onExit;

    public WorldGameHubMenu(MenuController menuController, WorldController worldController, MarketController marketController, LevelController levelController, CombatController combatController, Runnable onExit) {
        this.menuController = menuController;
        this.worldController = worldController;
        this.marketController = marketController;
        this.levelController = levelController;
        this.combatController = combatController;
        this.onExit = onExit;
    }

    public Scene createScene(Stage stage) {
        // TITLE
        Label title = new Label("WORLD GAME HUB");

        // BUTTONS
        Button adventure = new Button("Adventure");
        Button market = new Button("Emporium");
        Button sheet = new Button("View Sheet");
        Button save = new Button("Save Game");
        Button exit = new Button("Exit");

        Label gameSaved = new Label();

        adventure.setOnAction(event -> openCombat(stage));

        market.setOnAction(_ -> {
            worldController.enterMarket();
            EmporiumView emp = new EmporiumView(
                    worldController,
                    marketController,
                    () -> stage.setScene(createScene(stage))
            );
            stage.setScene(emp.createScene(stage));
        });

        sheet.setOnAction(_ -> {
            CharacterSheetView csv = new CharacterSheetView(
                    worldController,
                    marketController,
                    () -> stage.setScene(createScene(stage))
            );
            stage.setScene(csv.createScene(stage));
        });
        save.setOnAction(e -> {
                menuController.saveGame();
                gameSaved.setText("Game saved successfully");
                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(event -> gameSaved.setText(""));
                pause.play();
        });
        exit.setOnAction(_ -> onExit.run());

        VBox buttons = new VBox(10, adventure, market, sheet, save, exit);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(40));
        buttons.setSpacing(10);

        BorderPane root = new BorderPane();
        root.setTop(title);
        root.setCenter(buttons);
        root.setBottom(gameSaved);
        root.setStyle("-fx-background-image: url('/images/hub-wallpaper.jpg');");

        Scene scene = new Scene(root, 800, 600);

        scene.getStylesheets().add(Objects.requireNonNull(WorldGameHubMenu.class.getResource("/css/style.css")).toExternalForm());
        root.getStyleClass().add("menu");
        title.getStyleClass().add("title");
        title.setAlignment(Pos.CENTER);
        gameSaved.getStyleClass().add("pauseText");
        gameSaved.setStyle("-fx-text-fill: white");

        return scene;
    }

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

    private void openVictory(Stage stage) {
        ResultView type = levelController.getWorldGame().getLevelManager().isLastLevel()
                        ? ResultView.GAME_COMPLETED
                        : ResultView.LEVEL_COMPLETED;
        System.out.println("RESULT VIEW = " + type);

        FightResultView view = new FightResultView(
                levelController,
                combatController,
                // pulsante principale
                () -> openCombat(stage),
                // pulsante secondario
                () -> stage.setScene(createScene(stage)),
                type
        );

        stage.setScene(view.createScene(stage));
    }

    private void openDefeat(Stage stage) {
        FightResultView view = new FightResultView(
                levelController,
                combatController,
                // retry
                () -> openCombat(stage),
                // hub
                () -> stage.setScene(createScene(stage)),
                ResultView.LEVEL_FAILED
        );

        stage.setScene(view.createScene(stage));
    }
}
