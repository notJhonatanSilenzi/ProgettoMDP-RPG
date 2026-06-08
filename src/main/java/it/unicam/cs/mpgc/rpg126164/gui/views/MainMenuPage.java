package it.unicam.cs.mpgc.rpg126164.gui.views;

import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.GameState;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.MarketController;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.MenuController;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.WorldController;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
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

    public MainMenuPage(MenuController menuController, WorldController worldController, MarketController marketController) {
        this.menuController = menuController;
        this.worldController = worldController;
        this.marketController = marketController;
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
        Button deleteButton = new Button("Delete Game");
        Button infoButton = new Button("Info");
        Button exitButton = new Button("Exit");

        newGameButton.setPrefWidth(200);
        loadButton.setPrefWidth(200);
        deleteButton.setPrefWidth(200);
        infoButton.setPrefWidth(200);
        exitButton.setPrefWidth(200);

        Label deletedGame = new Label();

        // ACTIONS
        newGameButton.setOnAction(e -> {
            CreateCharacterPage ccp = new CreateCharacterPage(
                    menuController,
                    worldController,
                    marketController,
                    () -> stage.setScene(createScene(stage)));
            stage.setScene(ccp.createScene(stage));
        });
        loadButton.setOnAction(e -> {
            try {
                GameState gameState = menuController.loadGame();
                worldController.loadWorldGame(gameState);
                WorldGameHubMenu gameHub = new WorldGameHubMenu(
                        menuController,
                        worldController,
                        marketController,
                        () -> stage.setScene(createScene(stage)));
                stage.setScene(gameHub.createScene(stage));
            } catch (Exception ex) {
                deletedGame.setText(ex.getMessage());
            }
        });
        deleteButton.setOnAction(_ -> {
            menuController.clearSaveSlot();
            deletedGame.setText("Game deleted successfully");
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(_ -> deletedGame.setText(""));
            pause.play();
        });
        infoButton.setOnAction(_ -> {
            InfoPage infoPage = new InfoPage(() -> stage.setScene(createScene(stage)));
            stage.setScene(infoPage.createScene());
        });
        exitButton.setOnAction(e -> stage.close());

        VBox menuBox = new VBox(15, newGameButton, loadButton, deleteButton, infoButton, exitButton);
        menuBox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);
        root.setCenter(menuBox);
        root.setBottom(deletedGame);
        deletedGame.getStyleClass().add("pauseText");

        // WALLPAPER
        root.setStyle("-fx-background-image: url('/images/map-wallpaper-2.jpg');");

        return new Scene(root, 800, 600);
    }
}
