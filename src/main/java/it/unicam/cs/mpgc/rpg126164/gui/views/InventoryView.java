package it.unicam.cs.mpgc.rpg126164.gui.views;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayerFighter;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.Item;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
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

public class InventoryView {

    private final WorldController worldController;
    private final MarketController marketController;
    private final Runnable onBack;
    private final boolean marketMode;

    public InventoryView(WorldController worldController, MarketController marketController, Runnable onBack, boolean marketMode) {
        this.worldController = worldController;
        this.marketController = marketController;
        this.onBack = onBack;
        this.marketMode = marketMode;
    }

    public Scene createScene() {
        PlayerFighter player = worldController.getWorldGame().getPlayer();

        // ===================================== TITLE =====================================
        Label title = new Label("INVENTORY");
        title.setAlignment(Pos.CENTER);

        // ===================================== DETAILS =====================================
        Label detailsTitle = new Label("ITEM DETAILS");
        Label nameLabel = new Label();
        Label descriptionLabel = new Label();
        Label quantityLabel = new Label();
        Label valueLabel = new Label();
        descriptionLabel.setWrapText(true);
        VBox detailsPane = new VBox(10, detailsTitle, nameLabel, descriptionLabel, quantityLabel, valueLabel);
        detailsPane.setPrefWidth(350);

        Label sellingDetails = new Label();

        // ===================================== LIST VIEW =====================================
        ListView<ItemStack> inventoryList = new ListView<>();
        inventoryList.getItems().addAll(player.getInventory().getItems().values());
        inventoryList.setPrefWidth(350);

        inventoryList.setCellFactory(_ -> new ListCell<>() {
            @Override
            protected void updateItem(ItemStack stack, boolean empty) {
                super.updateItem(stack, empty);
                if (empty || stack == null) {
                    setText(null);
                    return;
                }
                Item item = stack.getItem();
                setText(item.getName() + " (x" + stack.getCount() + ")");
            }
        });

        inventoryList.getSelectionModel()
                .selectedItemProperty()
                .addListener((_, _, selected) -> {
                    if (selected == null) {
                        nameLabel.setText("");
                        descriptionLabel.setText("");
                        quantityLabel.setText("");
                        valueLabel.setText("");
                        return;
                    }
                    Item item = selected.getItem();
                    nameLabel.setText("Name: " + item.getName());
                    descriptionLabel.setText("Description: " + item.getDescription());
                    quantityLabel.setText("Quantity: " + selected.getCount());
                    valueLabel.setText("Value: " + item.getTradeValue() + " " + player.getMoneyCollector().getMoneyName());
                });


        HBox centerContent = new HBox(15, inventoryList, detailsPane);
        centerContent.setAlignment(Pos.CENTER);

        // ===================================== BUTTONS =====================================
        Button backButton = new Button("Back");
        backButton.setOnAction(_ -> onBack.run());
        HBox buttons = new HBox(20);

        if (marketMode) {
            Button sellButton = getButton(inventoryList, player, sellingDetails);
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
        root.setCenter(centerContent);
        BorderPane.setAlignment(centerContent, Pos.CENTER);
        root.setBottom(bottom);
        BorderPane.setAlignment(bottom, Pos.CENTER);

        // ===================================== STYLE =====================================
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("/css/style.css");
        inventoryList.getStyleClass().add("list-view");
        detailsPane.getStyleClass().add("inventory-details");
        sellingDetails.getStyleClass().add("floating-text");
        title.getStyleClass().add("title");
        root.getStyleClass().add("root");

        return scene;
    }

    private Button getButton(ListView<ItemStack> inventoryList, PlayerFighter player, Label details) {
        Button sellButton = new Button("Sell Selected");
        sellButton.setOnAction(_ -> {
            try {
                ItemStack selected = inventoryList.getSelectionModel().getSelectedItem();

                if (selected == null) return;

                marketController.sellItem(selected);
                inventoryList.getItems().setAll(
                        player.getInventory()
                                .getItems()
                                .values()
                );
                details.setText("Item sold");
            } catch (Exception ex) {
                details.setText("Cannot sell this weapon, cause it's the only one in your inventory.");
            }
        });
        return sellButton;
    }
}
