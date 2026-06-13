package it.unicam.cs.mpgc.rpg126164.gui.views;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayerFighter;
import it.unicam.cs.mpgc.rpg126164.domain.characters.stats.Archetype;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.*;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class works as a view for the creation of a playable character
 */
public class CreateCharacterPage {

    private final MenuController menuController;
    private final WorldController worldController;
    private final MarketController marketController;
    private final LevelController levelController;
    private final CombatController combatController;
    private final Runnable onBack;

    /**
     * Creates a create character view for the game
     * @param mc the menu controller
     * @param wc the world controller
     * @param mkc the market controller
     * @param lc the level controller
     * @param cc the combat controller
     * @param onBack the callback to the main menu
     */
    public CreateCharacterPage(MenuController mc, WorldController wc, MarketController mkc, LevelController lc, CombatController cc, Runnable onBack) {
        this.menuController = mc;
        this.worldController = wc;
        this.marketController = mkc;
        this.levelController = lc;
        this.combatController = cc;
        this.onBack = onBack;
    }

    /**
     * Creates a creation character scene for the given stage
     * @param stage the current stage
     * @return the creation character scene
     */
    public Scene createScene(Stage stage) {
        // ===================================== TITLE =====================================
        Label title = new Label("Create Your Character");

        // ===================================== FORM =====================================
        Label details = new Label();
        details.setPadding(new Insets(15));
        details.setAlignment(Pos.CENTER);
        TextField nameBox = new TextField();
        TextArea descriptionBox = new TextArea();
        ComboBox<Archetype> archetypes = new ComboBox<>();
        archetypes.getItems().addAll(Archetype.values());
        Button createButton = createActionButton(stage, nameBox, descriptionBox, archetypes, details);
        Button goBack = new Button("Go Back");
        goBack.setOnAction(_ -> onBack.run());

        HBox hBox = new HBox(createButton, goBack);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);

        Label name = new Label("Name:");
        Label description = new Label("Description:");
        Label archetype = new Label("Archetype:");
        VBox createMenu = new VBox(10,
                name, nameBox,
                description, descriptionBox,
                archetype, archetypes,
                hBox
        );
        createMenu.setPadding(new Insets(15));

        // ===================================== ROOT =====================================
        BorderPane root = new BorderPane();
        root.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);
        root.setCenter(createMenu);
        root.setBottom(details);
        BorderPane.setAlignment(details, Pos.CENTER);

        // ===================================== STYLE =====================================
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("/css/style.css");
        root.getStyleClass().add("root");
        title.getStyleClass().add("title");
        name.getStyleClass().add("normal-text");
        description.getStyleClass().add("normal-text");
        archetype.getStyleClass().add("normal-text");
        createButton.getStyleClass().add("button");
        goBack.getStyleClass().add("button");
        nameBox.getStyleClass().add("text-field");
        descriptionBox.getStyleClass().add("text-area");
        archetypes.getStyleClass().add("combo-box");
        details.getStyleClass().add("floating-text");

        return scene;
    }

    /**
     * Sets up the button to create a new character and a new world game
     * @param stage the current stage
     * @param name the text field with the name
     * @param desc the text area with the description
     * @param archetype the combo box with the archetypes
     * @return the create button
     */
    private Button createActionButton(Stage stage, TextField name, TextArea desc, ComboBox<Archetype> archetype, Label details) {
        Button createButton = new Button("Create");
        createButton.setOnAction(_ -> {
            try {
                PlayerFighter player = menuController.createNewGame(name.getText(), desc.getText(), archetype.getValue());
                worldController.createWorld(player);
                WorldGameHubMenu gameHub = new WorldGameHubMenu(
                        menuController,
                        worldController,
                        marketController,
                        levelController,
                        combatController,
                        onBack);
                stage.setScene(gameHub.createScene(stage));
            } catch (Exception e) {
                details.setText(e.getMessage());
                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(_ -> details.setText(""));
                pause.play();
            }
        });
        return createButton;
    }
}
