package it.unicam.cs.mpgc.rpg126164.gui.views;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayerFighter;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.MarketController;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.WorldController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * This class works as an inventory view for the game. It can be opened from an emporium view, or from
 * a character sheet view
 */
public class InventoryView {

    private final WorldController worldController;
    private final MarketController marketController;
    private final Runnable onBack;
    private final boolean marketMode;

    /**
     * Creates an inventory view
     * @param worldController the world controller
     * @param marketController the market controller
     * @param onBack the callback to the emporium view or to the character sheet view
     * @param marketMode a flag. True if it's opened from the emporium view, false otherwise
     */
    public InventoryView(WorldController worldController, MarketController marketController, Runnable onBack, boolean marketMode) {
        this.worldController = worldController;
        this.marketController = marketController;
        this.onBack = onBack;
        this.marketMode = marketMode;
    }

    /**
     * Builds a scene of the inventory
     * @return the inventory scene
     */
    public Scene createScene() {
        PlayerFighter player = worldController.getWorldGame().getPlayer();

        // ===================================== TITLE =====================================
        Label title = new Label("INVENTORY");
        title.setAlignment(Pos.CENTER);

        Label sellingDetails = new Label();

        // ===================================== INVENTORY =====================================
        InventoryComponentBuilder builder = new  InventoryComponentBuilder();
        InventoryComponent centerContent = builder.buildInventoryComponent(player, player.getMoneyCollector().getMoneyName());
        HBox center = centerContent.root();
        center.setAlignment(Pos.CENTER);

        // ===================================== BUTTONS =====================================
        Button backButton = new Button("Back");
        backButton.setOnAction(_ -> onBack.run());
        HBox buttons = new HBox(20);

        if (marketMode) {
            Button sellButton = getButton(centerContent, player, sellingDetails);
            buttons.getChildren().addAll(sellButton, backButton);
        } else buttons.getChildren().add(backButton);

        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(20));

        VBox bottom = new VBox(20, sellingDetails, buttons);
        bottom.setAlignment(Pos.CENTER);

        // ===================================== ROOT =====================================
        BorderPane root = new BorderPane();
        root.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);
        root.setCenter(center);
        BorderPane.setAlignment(centerContent.inventoryList(), Pos.CENTER);
        root.setBottom(bottom);
        BorderPane.setAlignment(bottom, Pos.CENTER);

        // ===================================== STYLE =====================================
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("/css/style.css");
        sellingDetails.getStyleClass().add("floating-text");
        title.getStyleClass().add("title");
        root.getStyleClass().add("root");

        return scene;
    }

    /**
     * Sets up the sell button, if the view is on market mode
     * @param inventory the inventory component view of the inventory items
     * @param player the player's character
     * @param details the floating text label
     * @return the sell button
     */
    private Button getButton(InventoryComponent inventory, PlayerFighter player, Label details) {
        Button sellButton = new Button("Sell Selected");
        sellButton.setOnAction(_ -> {
            try {
                ItemStack selected = inventory.getSelectedItem();
                if (selected == null) return;
                marketController.sellItem(selected);
                inventory.refresh(player);
                details.setText("Item sold");
            } catch (Exception ex) {
                details.setText("Cannot sell this weapon, cause it's the only one in your inventory.");
            }
        });

        return sellButton;
    }
}
