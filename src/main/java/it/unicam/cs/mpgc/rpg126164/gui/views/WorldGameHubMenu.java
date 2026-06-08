package it.unicam.cs.mpgc.rpg126164.gui.views;

import it.unicam.cs.mpgc.rpg126164.gui.controllers.MarketController;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.MenuController;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.WorldController;
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
    private final Runnable onExit;

    public WorldGameHubMenu(MenuController menuController, WorldController worldController, MarketController marketController, Runnable onExit) {
        this.menuController = menuController;
        this.worldController = worldController;
        this.marketController = marketController;
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
}
