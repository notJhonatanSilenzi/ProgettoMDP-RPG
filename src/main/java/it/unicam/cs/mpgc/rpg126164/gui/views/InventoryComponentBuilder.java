package it.unicam.cs.mpgc.rpg126164.gui.views;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayerFighter;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.Item;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.equipment.Equipment;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.Consumable;
import it.unicam.cs.mpgc.rpg126164.domain.world.gameplay.Market;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * This class works as a builder for a HBox that contains a list view of items from the inventory,
 * and a details pane that contains all the specific info about the selected item
 */
public class InventoryComponentBuilder {

    /**
     * Builds an inventory component for a market
     * @param market the market
     * @param moneyName the moneyName
     * @return the inventory component for the market
     */
    public InventoryComponent buildInventoryComponent(Market market, String moneyName) {
        ObservableList<ItemStack> marketList = FXCollections.observableArrayList();
        marketList.addAll(market.getWarehouse().getItems().values());
        return this.build(marketList, moneyName);
    }

    /**
     * Builds the inventory component for a player's character
     * @param player the player's character
     * @return the inventory component
     */
    public InventoryComponent buildInventoryComponent(PlayerFighter player, String moneyName) {
        ObservableList<ItemStack> playerItems = FXCollections.observableArrayList();
        playerItems.addAll(player.getInventory().getItems().values());
        return this.build(playerItems, moneyName);
    }

    /**
     * Builds the inventory component for a fight items view
     * @param player the player
     * @param mode the inventory mode (POTION or WEAPON)
     * @return the inventory component
     */
    public InventoryComponent buildInventoryComponent(PlayerFighter player, InventoryMode mode) {
        ObservableList<ItemStack> playerItems = FXCollections.observableArrayList();
        for (ItemStack stack : player.getInventory().getItems().values()) {
            Item item = stack.getItem();
            if (mode == InventoryMode.POTION && !(item instanceof Consumable)) continue;
            if (mode == InventoryMode.WEAPON && !(item instanceof Equipment)) continue;
            playerItems.add(stack);
        }
        return this.build(playerItems, player.getMoneyCollector().getMoneyName());
    }

    /**
     * Builds an inventory component
     * @param inventoryItems the list of item stacks
     * @param moneyName the money name
     * @return the inventory component
     */
    private InventoryComponent build(ObservableList<ItemStack> inventoryItems, String moneyName) {
        // ===================================== DETAILS =====================================
        Label detailsTitle = new Label("Item Details");
        detailsTitle.setAlignment(Pos.CENTER);
        Label nameLabel = new Label();
        Label descriptionLabel = new Label();
        Label statsLabel = new Label();
        Label quantityLabel = new Label();
        Label valueLabel = new Label();
        descriptionLabel.setWrapText(true);
        HBox detailsTitleBox = new HBox(10, detailsTitle);
        detailsTitleBox.setAlignment(Pos.CENTER);
        VBox detailsPane = new VBox(5, detailsTitleBox, nameLabel, descriptionLabel, statsLabel, quantityLabel, valueLabel);
        detailsPane.setPrefWidth(400);
        detailsPane.setAlignment(Pos.CENTER_LEFT);
        detailsPane.setFillWidth(true);

        // ===================================== LIST VIEW =====================================
        ListView<ItemStack> inventoryList = getItemStackListView(inventoryItems);

        inventoryList.getSelectionModel()
                .selectedItemProperty()
                .addListener((_, _, selected) -> {
                    if (selected == null) {
                        nameLabel.setText("");
                        descriptionLabel.setText("");
                        statsLabel.setText("");
                        quantityLabel.setText("");
                        valueLabel.setText("");
                        return;
                    }
                    Item item = selected.getItem();
                    nameLabel.setText("Name: " + item.getName());
                    descriptionLabel.setText("Description: " + item.getDescription());
                    statsLabel.setText("Effect: " + selected.getItem().getStatsDesc());
                    quantityLabel.setText("Quantity: " + selected.getCount());
                    valueLabel.setText("Value: " + item.getTradeValue() + " " + moneyName
                            + " (" + item.getTradeValue()/2 + " if sold)");
                });

        HBox centerContent = new HBox(15, inventoryList, detailsPane);
        centerContent.setAlignment(Pos.CENTER);

        // ===================================== STYLE =====================================
        centerContent.getStylesheets().add("/css/style.css");
        detailsTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 28px;");
        setLabelStyle(List.of(detailsTitle, nameLabel, descriptionLabel, statsLabel, quantityLabel, valueLabel));
        inventoryList.getStyleClass().add("list-view");
        detailsPane.getStyleClass().add("inventory-details");

        return new InventoryComponent(centerContent, inventoryList, inventoryItems);
    }

    private static ListView<ItemStack> getItemStackListView(ObservableList<ItemStack> inventoryItems) {
        ListView<ItemStack> inventoryList = new ListView<>();
        inventoryList.setItems(inventoryItems);
        inventoryList.setPrefWidth(350);
        inventoryList.setFixedCellSize(-1);
        inventoryList.setCellFactory(_ -> new ListCell<>() {
            @Override
            protected void updateItem(ItemStack stack, boolean empty) {
                super.updateItem(stack, empty);
                if (empty || stack == null || stack.getItem() == null) {
                    setText(null);
                    setGraphic(null);
                    return;
                }
                Item item = stack.getItem();
                setText(item.getName() + " (x" + stack.getCount() + ")");
            }
        });
        return inventoryList;
    }

    /**
     * Sets the styles for the given components
     * @param title the title
     * @param buyButton the buy button
     * @param inventoryButton the inventory button
     * @param backButton the callback button
     * @param root the root pane
     */
    public Scene setStyles(Label title, Button buyButton, Button inventoryButton, Button backButton, BorderPane root) {
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("/css/style.css");
        title.getStyleClass().add("title");
        root.getStyleClass().add("root");
        buyButton.getStyleClass().add("button");
        inventoryButton.getStyleClass().add("button");
        backButton.getStyleClass().add("button");
        return scene;
    }

    /**
     * Sets the style for the given list of labels
     * @param labels the list of labels
     */
    private void setLabelStyle(List<Label> labels) {
        for (Label label : labels)
            label.getStyleClass().add("inventory-text");
    }
}
