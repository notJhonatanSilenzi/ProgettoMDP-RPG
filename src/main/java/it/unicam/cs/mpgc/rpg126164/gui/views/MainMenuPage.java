package it.unicam.cs.mpgc.rpg126164.gui.views;

import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.GameState;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.MenuController;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.WorldController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * This class works as a view for the main menu's game
 */
public class MainMenuPage {

    private final MenuController menuController;
    private final WorldController worldController;

    public MainMenuPage(MenuController menuController, WorldController worldController) {
        this.menuController = menuController;
        this.worldController = worldController;
    }

    public Scene createScene(Stage stage) {
        // TITLE
        Label title = new Label("WELCOME");
        title.setStyle(
                "-fx-font-family: sans-serif;" +
                "-fx-text-fill: black;" +
                "-fx-font-size: 50px;"
        );
        title.setAlignment(Pos.CENTER);
        title.setPadding(new Insets(40));

        // BUTTONS
        Button newGameButton = new Button("New Game");
        Button loadButton = new Button("Load Game");
        Button infoButton = new Button("Info");
        Button exitButton = new Button("Exit");

        newGameButton.setPrefWidth(200);
        loadButton.setPrefWidth(200);
        infoButton.setPrefWidth(200);
        exitButton.setPrefWidth(200);

        // ACTIONS
        newGameButton.setOnAction(e -> {
            CreateCharacterPage ccp = new CreateCharacterPage(
                    menuController,
                    worldController,
                    () -> stage.setScene(createScene(stage)));
            stage.setScene(ccp.createScene());
        });
        loadButton.setOnAction(e -> {
            GameState gameState = menuController.loadGame();
            worldController.loadWorldGame(gameState);
        });
        infoButton.setOnAction(e -> {
            InfoPage infoPage = new InfoPage(() -> stage.setScene(createScene(stage)));
            stage.setScene(infoPage.createScene());
        });
        exitButton.setOnAction(e -> stage.close());

        VBox menuBox = new VBox(15, newGameButton, loadButton, infoButton, exitButton);
        menuBox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);
        root.setCenter(menuBox);

        // WALLPAPER
        root.setStyle("-fx-background-color: #8b6f47;");

        return new Scene(root, 800, 600);
    }
}
