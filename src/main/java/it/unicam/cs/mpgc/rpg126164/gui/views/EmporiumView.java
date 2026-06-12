package it.unicam.cs.mpgc.rpg126164.gui.views;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayerFighter;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.Item;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.domain.world.gameplay.Market;
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
import javafx.stage.Stage;

/**
 * This class works as an emporium view
 */
public class EmporiumView {

    private final WorldController worldController;
    private final MarketController marketController;
    private final Runnable onBack;

    /**
     * Creates an emporium view
     * @param worldController the world controller
     * @param marketController the market controller
     * @param onBack the callback to the world game hub menu
     */
    public EmporiumView(WorldController worldController, MarketController marketController, Runnable onBack) {
        this.worldController = worldController;
        this.marketController = marketController;
        this.onBack = onBack;
    }

    /**
     * Creates an emporium scene for the given stage
     * @param stage the current stage
     * @return the emporium scene
     */
    public Scene createScene(Stage stage) {
        PlayerFighter player = marketController.getWorldGame().getPlayer();

        // ===================================== TITLE =====================================
        Label title = new Label("EMPORIUM");
        title.setAlignment(Pos.CENTER);

        // ===================================== GOLD INFO =====================================
        Label goldLabel = new Label("Unit: " + player.getMoneyCollector().getCurrentAmount() + " / " + player.getMoneyCollector().getMaxAmount());

        // ===================================== EMPORIUM INVENTORY =====================================
        InventoryComponentBuilder builder =  new InventoryComponentBuilder();
        InventoryComponent center = builder.buildInventoryComponent(
                marketController.getWorldGame().getMarket(),
                player.getMoneyCollector().getMoneyName()
        );

        // ===================================== BUTTONS =====================================
        Button buyButton = purchaseOnAction(center, player, marketController.getWorldGame().getMarket(), goldLabel);
        Button inventoryButton = inventoryOnAction(stage);
        Button backButton = new Button("Back");
        backButton.setOnAction(_ -> {
            marketController.exit();
            onBack.run();
        });

        HBox buttons = new HBox(20);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(buyButton, inventoryButton, backButton);

        // ===================================== ROOT =====================================
        BorderPane root = new BorderPane();
        VBox top = new VBox(10, title, goldLabel);
        root.setTop(top);
        root.setCenter(center.root());
        BorderPane.setMargin(center.root(), new Insets(0, 0, 20, 0));
        root.setBottom(buttons);
        BorderPane.setAlignment(title, Pos.CENTER);

        // ===================================== STYLE =====================================
        Scene scene = builder.setStyles(title, buyButton, inventoryButton, backButton, root);
        goldLabel.getStyleClass().add("floating-text");

        return scene;
    }

    /**
     * Sets up the buy button
     * @param inventory the inventory component for this scene
     * @param player the player's character
     * @param market the market
     * @param goldLabel the floating label to update
     * @return the buy button
     */
    private Button purchaseOnAction(InventoryComponent inventory, PlayerFighter player, Market market, Label goldLabel) {
        Button purchaseButton = new Button("Buy");
        purchaseButton.setOnAction(_ -> {
            Item selected = inventory.getSelectedItem().getItem();

            if (selected == null) return;
            marketController.buyItem(new ItemStack(selected, 1));
            inventory.refresh(market);
            goldLabel.setText("Unit: " + player.getMoneyCollector().getCurrentAmount() + " / " + player.getMoneyCollector().getMaxAmount());
        });
        return purchaseButton;
    }

    /**
     * Sets up the open inventory button
     * @param stage the current stage
     * @return the open inventory button
     */
    private Button inventoryOnAction(Stage stage) {
        Button inventoryButton = new Button("Inventory");
        inventoryButton.setOnAction(_ -> {
            InventoryView inventoryView = new InventoryView(
                    worldController,
                    marketController,
                    () -> stage.setScene(this.createScene(stage)),
                    true
            );
            stage.setScene(inventoryView.createScene());
        });
        return inventoryButton;
    }
}
