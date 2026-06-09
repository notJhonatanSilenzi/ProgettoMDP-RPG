package it.unicam.cs.mpgc.rpg126164.gui.views;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayableCharacter;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.Item;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.equipment.Equipment;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.Consumable;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.Potion;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.MarketController;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.WorldController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EmporiumView {

    private final WorldController worldController;
    private final MarketController marketController;
    private final Runnable onBack;

    public EmporiumView(WorldController worldController, MarketController marketController, Runnable onBack) {
        this.worldController = worldController;
        this.marketController = marketController;
        this.onBack = onBack;
    }

    public Scene createScene(Stage stage) {
        PlayableCharacter player = marketController.getWorldGame().getPlayer();

        // TITLE
        Label title = new Label("EMPORIUM");

        // GOLD INFO
        Label goldLabel = new Label(
                "Unit: " + player.getMoneyCollector().getCurrentAmount()
                        + " / " + player.getMoneyCollector().getMaxAmount()
        );

        // MARKET LIST
        ListView<Item> marketList = new ListView<>();
        marketList.getItems().addAll(marketController.showMarketItems().keySet());
        marketList.setCellFactory(list -> new ListCell<>() {
            @Override
            protected void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) setText(null);
                else setText(item.getName() + " - " + item.getTradeValue() + " " + player.getMoneyCollector().getMoneyName());
            }
        });

        // DETAILS
        Label details = new Label("Select an item");
        details.setWrapText(true);
        marketList.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> {
                    if (newVal == null) {
                        details.setText("Select an item");
                        return;
                    }
                    details.setText(newVal.infoToString() + " " + player.getMoneyCollector().getMoneyName() + " - ");
                });

        VBox center = new VBox(15,
                        marketList,
                        details
                );

        // BUTTONS
        Button buyButton = new Button("Buy Selected");
        Button inventoryButton = new Button("Inventory");
        Button backButton = new Button("Back");
        HBox buttons = new HBox(20);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(20));

        // BUY
        buyButton.setOnAction(e -> {
            Item selected = marketList.getSelectionModel().getSelectedItem();

            if (selected == null) return;

            marketController.buyItem(new ItemStack(selected, 1));

            goldLabel.setText(
                    "Unit: " + player.getMoneyCollector().getCurrentAmount()
                            + " / " + player.getMoneyCollector().getMaxAmount()
            );
        });

        // INVENTORY (SELL MODE)

        inventoryButton.setOnAction(e -> {

            InventoryView inventoryView = new InventoryView(
                worldController,
                marketController,
                () -> stage.setScene(this.createScene(stage)),
                true
            );
            stage.setScene(inventoryView.createScene(stage));
        });

        // BACK
        backButton.setOnAction(e -> {
            marketController.exit();
            onBack.run();
        });
        buttons.getChildren().addAll(buyButton, inventoryButton, backButton);

        // ROOT
        BorderPane root = new BorderPane();
        VBox top = new VBox(10,
            title,
            goldLabel
        );
        root.setTop(top);
        root.setCenter(center);
        root.setBottom(buttons);
        BorderPane.setAlignment(title, Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-image: url('/images/map-wallpaper-2.jpg');");

        title.getStyleClass().add("title");
        root.getStyleClass().add("root");

        return new Scene(root, 800, 600);
    }
}
