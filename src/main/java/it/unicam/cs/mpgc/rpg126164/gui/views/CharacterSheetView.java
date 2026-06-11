package it.unicam.cs.mpgc.rpg126164.gui.views;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayableCharacter;
import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayerFighter;
import it.unicam.cs.mpgc.rpg126164.domain.characters.stats.Archetype;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.MarketController;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.WorldController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class CharacterSheetView {

    private final WorldController worldController;
    private final MarketController marketController;
    private final Runnable onBack;

    public CharacterSheetView(WorldController worldController, MarketController marketController, Runnable onBack) {
        this.worldController = worldController;
        this.marketController = marketController;
        this.onBack = onBack;
    }

    public Scene createScene(Stage stage) {
        PlayerFighter player = worldController.getWorldGame().getPlayer();

        // TITLE
        Label title = new Label("YOUR CHARACTER");

        // INFO
        Label name = new Label("Name: " +  player.getName());
        Label description = new Label("Description:");
        Label description2 = new Label(player.getDescription());
        Label archetype = new Label("Archetype: " + player.getArchetype());
        Label hp = new Label("HP: " + player.getSheet().getHP());
        Label atk =  new Label("ATK: " + player.getSheet().getATK());
        Label df = new Label("DF: " + player.getSheet().getDF());
        Label evade = new Label("Evade Chance: " + player.getSheet().getEvadeChance());
        Label weapon = new Label("Equipment: " + player.getCurrentEquipment().getName() + " (" +
                player.getCurrentEquipment().useEquipment() + ")");
        description2.setWrapText(true);
        description2.setMaxWidth(300);

        Button inv = new Button("Open Inventory");
        Button back = new Button("Back");
        inv.setOnAction(e -> {
            InventoryView inventoryView = new InventoryView(
                    worldController,
                    marketController,
                    () -> stage.setScene(createScene(stage)),
                    false
            );
            stage.setScene(inventoryView.createScene(stage));
        });
        back.setOnAction(_ -> onBack.run());

        VBox info = new VBox(10, name,
                description, description2,
                archetype,
                hp,
                atk,
                df,
                evade,
                weapon
        );
        ImageView image = new ImageView(this.getArchetypeImage(player.getArchetype()));
        image.setFitWidth(250);
        image.setFitHeight(350);
        image.setPreserveRatio(true);

        HBox centerContent = new HBox(20, info, image);
        centerContent.setPadding(new Insets(30));

        HBox buttons = new HBox(inv, back);

        BorderPane root = new BorderPane();
        root.setTop(title);
        root.setCenter(centerContent);
        root.setBottom(buttons);
        root.setStyle("-fx-background-image: url('/images/map-wallpaper-2.jpg');");

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(Objects.requireNonNull(CharacterSheetView.class.getResource("/css/style.css")).toExternalForm());
        title.getStyleClass().add("title");
        root.getStyleClass().add("root");
        return scene;
    }

    private String getArchetypeImage(Archetype archetype) {
        return switch (archetype) {
            case WARRIOR -> "/images/warrior-player.png";
            case BERSERKER -> "/images/berserker-player.png";
            case CLERIC -> "/images/cleric-player.png";
            case SORCERER -> "/images/sorcerer-player.png";
        };
    }
}
