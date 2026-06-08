package it.unicam.cs.mpgc.rpg126164.gui.views;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayableCharacter;
import it.unicam.cs.mpgc.rpg126164.domain.characters.stats.Archetype;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.MarketController;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.MenuController;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.WorldController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class works as a view for the creation of a playable character
 */
public class CreateCharacterPage {

    private final MenuController menuController;
    private final WorldController worldController;
    private final MarketController marketController;
    private final Runnable onBack;

    public CreateCharacterPage(MenuController mc, WorldController wc, MarketController mkc, Runnable onBack) {
        this.menuController = mc;
        this.worldController = wc;
        this.marketController = mkc;
        this.onBack = onBack;
    }

    public Scene createScene(Stage stage) {
        // TITLE
        Label title = new Label("Create Your Character");
        title.setStyle(
                "-fx-font-family: sans-serif;" +
                "-fx-text-fill: black;" +
                "-fx-font-size: 50px;" +
                "-fx-alignment: center;"
        );
        title.setPadding(new Insets(40));

        // FORM ELEMENTS
        TextField nameBox = new TextField();
        TextArea descriptionBox = new TextArea();
        ComboBox<Archetype> archetypes = new ComboBox<>();
        archetypes.getItems().addAll(Archetype.values());
        Button createButton = new Button("Create");
        Button goBack = new Button("Go Back");

        HBox hBox = new HBox(createButton, goBack);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);

        VBox createMenu = new VBox(10,
                new Label("Name: "), nameBox,
                new Label("Description: "), descriptionBox,
                new Label("Archetype: "), archetypes,
                hBox
        );
        createMenu.setPadding(new Insets(20));

        // ACTIONS
        createButton.setOnAction(e -> {
            PlayableCharacter player = menuController.createNewGame(nameBox.getText(), descriptionBox.getText(), archetypes.getValue());
            worldController.createWorld(player);
            WorldGameHubMenu gameHub = new WorldGameHubMenu(
                    menuController,
                    worldController,
                    marketController,
                    onBack);
            stage.setScene(gameHub.createScene(stage));
        });
        goBack.setOnAction(e -> onBack.run());

        BorderPane root = new BorderPane();
        root.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);
        root.setCenter(createMenu);

        // WALLPAPER
        root.setStyle(
                "-fx-background-image: url('/images/map-wallpaper-2.jpg');" +
                        "-fx-background-repeat: no-repeat;" +
                        "-fx-background-position: center;" +
                        "-fx-background-size: cover;");

        return new Scene(root, 800, 600);
    }
}
