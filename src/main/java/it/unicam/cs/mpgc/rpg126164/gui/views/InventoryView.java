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

import java.util.Objects;

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

    public Scene createScene(Stage stage) {

        PlayableCharacter player = worldController.getWorldGame().getPlayer();

        // TITLE
        Label title = new Label("INVENTORY");

        // INVENTORY LIST
        ListView<ItemStack> inventoryList = new ListView<>();
        inventoryList.getItems().addAll(marketController.showPlayerItems(player).values());

        inventoryList.setCellFactory(_ ->
                new ListCell<>() {
                    @Override
                    protected void updateItem(ItemStack stack, boolean empty) {
                        super.updateItem(stack, empty);
                        if (empty || stack == null) setText(null);
                        else setText(stack.getItem().getName() + " (" + stack.getCount() + ")");
                    }
                });

        // DETAILS
        Label details = new Label("Select an item");
        details.setWrapText(true);
        inventoryList.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldValue, newValue) -> {
                    if (newValue == null) {
                        details.setText("Select an item");
                        return;
                    }

                    Item item = newValue.getItem();

                    details.setText(
                            item.getName()
                                    + "\n\n"
                                    + item.getDescription()
                                    + "\n\nQuantity: "
                                    + newValue.getCount()
                                    + "\nValue: "
                                    + item.getTradeValue()
                                    + "\n"
                                    + getStatDesc(item)
                    );
                });

        VBox centerContent = new VBox(15,
                        inventoryList,
                        details
                );

        // BUTTONS
        Button backButton = new Button("Back");

        backButton.setOnAction(e -> onBack.run());

        HBox buttons = new HBox(20);

        if (marketMode) {
            Button sellButton = new Button("Sell Selected");
            sellButton.setOnAction(e -> {
                ItemStack selected = inventoryList.getSelectionModel().getSelectedItem();

                if (selected == null) return;

                marketController.sellItem(selected);

                inventoryList.getItems().setAll(
                        player.getInventory()
                                .getItems()
                                .values()
                );

                details.setText("Item sold.");
            });
            buttons.getChildren().addAll(sellButton, backButton);
        } else buttons.getChildren().add(backButton);

        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(20));

        // ROOT
        BorderPane root = new BorderPane();
        root.setTop(title);
        root.setCenter(centerContent);
        root.setBottom(buttons);
        BorderPane.setAlignment(title, Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-image: url('/images/map-wallpaper-2.jpg');");
        root.getStylesheets().add(Objects.requireNonNull(InventoryView.class.getResource("/css/style.css")).toExternalForm());

        title.getStyleClass().add("title");
        root.getStyleClass().add("root");

        return new Scene(root, 800, 600);
    }

    private String getStatDesc(Item item) {
        if (item instanceof Equipment)
            return ((Equipment) item).useEquipment() + " Damage";
        else if (item instanceof Consumable)
            return ((Potion) item).getStatsModifier() + " " + ((Consumable) item).getStatsType();

        return "NONE";
    }
}
