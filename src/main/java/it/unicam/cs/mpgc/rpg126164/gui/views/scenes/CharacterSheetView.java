package it.unicam.cs.mpgc.rpg126164.gui.views.scenes;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayerFighter;
import it.unicam.cs.mpgc.rpg126164.domain.characters.stats.Archetype;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.MarketController;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.WorldController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

/**
 * This class works as a character sheet view in the world game hub menu
 */
public class CharacterSheetView {

    private final WorldController worldController;
    private final MarketController marketController;
    private final Runnable onBack;

    /**
     * Creates a character sheet view
     * @param worldController the world controller
     * @param marketController the market controller
     * @param onBack the callback to the hub menu
     */
    public CharacterSheetView(WorldController worldController, MarketController marketController, Runnable onBack) {
        this.worldController = worldController;
        this.marketController = marketController;
        this.onBack = onBack;
    }

    /**
     * Creates a character sheet scene for the given stage
     * @param stage the current stage
     * @return the character sheet scene
     */
    public Scene createScene(Stage stage) {
        PlayerFighter player = worldController.getWorldGame().getPlayer();
        player.getSheet().reset();

        // ===================================== TITLE =====================================
        Label title = new Label("YOUR CHARACTER");
        title.setAlignment(Pos.CENTER);

        // ===================================== INFO =====================================
        Label name = new Label("Name:");
        Label name2 = new Label(player.getName());
        Label description = new Label("Description:");
        Label description2 = new Label(player.getDescription());
        Label archetype = new Label("Archetype:");
        Label archetype2 = new Label("" + player.getArchetype());
        Label hp = new Label("HP:");
        Label hp2 = new Label("" + player.getSheet().getHP());
        Label atk =  new Label("ATK:");
        Label atk2 = new Label("" + player.getSheet().getATK());
        Label df = new Label("DF:");
        Label df2 = new Label("" + player.getSheet().getDF());
        Label evade = new Label("Evade Chance:");
        Label evade2 = new Label("" + player.getSheet().getEvadeChance());
        Label weapon = new Label("Equipment:");
        Label weapon2 = new Label(player.getCurrentEquipment().getName() + " (" +
                player.getCurrentEquipment().useEquipment() + " ATK)");
        description2.setWrapText(true);
        description2.setMaxWidth(300);

        HBox rowName = new HBox(10, name, name2);
        HBox rowArchetype = new HBox(10, archetype, archetype2);
        HBox rowHp = new HBox(10, hp, hp2);
        HBox rowAtk = new HBox(10, atk, atk2);
        HBox rowDf = new HBox(10, df, df2);
        HBox rowEvade = new HBox(10, evade, evade2);
        HBox rowWeapon = new HBox(10, weapon, weapon2);

        VBox info = new VBox(10, rowName, rowArchetype, rowHp, rowAtk, rowDf, rowEvade, rowWeapon, description, description2);

        // ===================================== IMAGE =====================================
        ImageView image = new ImageView(this.getArchetypeImage(player.getArchetype()));
        image.setFitWidth(290);
        image.setFitHeight(350);

        StackPane portrait = new StackPane(image);
        portrait.setPrefSize(250, 350);
        portrait.setMinSize(250, 350);
        portrait.setMaxSize(250, 350);

        HBox centerContent = new HBox(20, info, portrait);
        centerContent.setPadding(new Insets(30));
        centerContent.setAlignment(Pos.CENTER);

        // ===================================== BUTTONS =====================================
        Button inv = inventoryOnAction(stage);
        Button back = new Button("Back");
        back.setOnAction(_ -> onBack.run());

        HBox buttons = new HBox(20, inv, back);
        buttons.setAlignment(Pos.CENTER);

        // ===================================== ROOT =====================================
        BorderPane root = new BorderPane();
        root.setTop(title);
        root.setCenter(centerContent);
        root.setBottom(buttons);
        BorderPane.setAlignment(buttons, Pos.CENTER);
        BorderPane.setAlignment(centerContent, Pos.CENTER);
        BorderPane.setAlignment(title, Pos.CENTER);

        // ===================================== STYLE =====================================
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("/css/style.css");
        title.getStyleClass().add("title");
        root.getStyleClass().add("sheet-root");
        inv.getStyleClass().add("button");
        back.getStyleClass().add("button");
        image.getStyleClass().add("character-image");
        setLabelStyle(List.of(name, description, archetype, hp, atk, df, evade, weapon));
        setValueStyle(List.of(name2, description2, archetype2, hp2, atk2, df2, evade2, weapon2));
        return scene;
    }

    /**
     * Sets up the inventory button of this view
     * @param stage the current stage
     * @return the inventory button
     */
    private Button inventoryOnAction(Stage stage) {
        Button inv = new Button("Open Inventory");
        inv.setOnAction(_ -> {
            InventoryView inventoryView = new InventoryView(
                    worldController,
                    marketController,
                    () -> stage.setScene(createScene(stage)),
                    false
            );
            stage.setScene(inventoryView.createScene());
        });
        return inv;
    }

    /**
     * Returns the correct image, given the archetype
     * @param archetype the selected archetype
     * @return the image of the player's character
     */
    private String getArchetypeImage(Archetype archetype) {
        return switch (archetype) {
            case WARRIOR -> "/images/warrior-player.png";
            case BERSERKER -> "/images/berserker-player.png";
            case CLERIC -> "/images/cleric-player-2.png";
            case SORCERER -> "/images/sorcerer-player.png";
        };
    }

    private void setLabelStyle(List<Label> labels) {
        for (Label label : labels)
            label.getStyleClass().add("character-label");
    }

    private void setValueStyle(List<Label> values) {
        for (Label label : values)
            label.getStyleClass().add("character-value");
    }
}
