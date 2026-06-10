package it.unicam.cs.mpgc.rpg126164.gui.views;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayableCharacter;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.Item;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.equipment.Equipment;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.Consumable;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.CombatController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.function.Consumer;

public class FightItemsView {

    private final CombatController combatController;
    private final InventoryMode mode;
    private final Consumer<ItemStack> itemSelected;
    private final Runnable onBack;

    public FightItemsView(CombatController combatController, InventoryMode itemToShow, Consumer<ItemStack> consumer, Runnable onBack) {
        this.combatController = combatController;
        this.mode = itemToShow;
        this.itemSelected = consumer;
        this.onBack = onBack;
    }

    public Scene createScene(Stage stage) {
        PlayableCharacter player = combatController.getWorldGame().getPlayer();

        Label title = new Label(
                mode == InventoryMode.POTION
                        ? "SELECT A POTION"
                        : "SELECT A WEAPON"
        );

        ListView<ItemStack> inventoryList = new ListView<>();

        for (ItemStack stack : combatController.showInventory().values()) {
            Item item = stack.getItem();
            if (mode == InventoryMode.POTION && !(item instanceof Consumable)) continue;
            if (mode == InventoryMode.WEAPON && !(item instanceof Equipment)) continue;
            inventoryList.getItems().add(stack);
        }

        inventoryList.setCellFactory(param ->
                new ListCell<>() {
                    @Override
                    protected void updateItem(ItemStack stack, boolean empty) {
                        super.updateItem(stack, empty);
                        if (empty || stack == null) {
                            setText(null);
                            return;
                        }
                        Item item = stack.getItem();
                        setText(item.getName()
                                        + " (x"
                                        + stack.getCount()
                                        + ") - "
                                        + item.getDescription()
                        );
                    }
                });

        HBox buttons = getHBox(inventoryList);

        BorderPane root = new BorderPane();

        root.setTop(title);
        root.setCenter(inventoryList);
        root.setBottom(buttons);

        BorderPane.setAlignment(title, Pos.CENTER);

        root.setPadding(new Insets(20));

        root.setStyle("-fx-background-image: url('/images/map-wallpaper-2.jpg');");
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(Objects.requireNonNull(FightItemsView.class.getResource("/css/style.css")).toExternalForm());
        root.getStyleClass().add("root");
        title.getStyleClass().add("title");
        return scene;
    }

    private HBox getHBox(ListView<ItemStack> inventoryList) {
        Button actionButton = new Button(mode == InventoryMode.POTION ? "Consume" : "Equip"
        );

        Button backButton = new Button("Back");

        actionButton.setOnAction(e -> {

            ItemStack selected =
                    inventoryList.getSelectionModel()
                            .getSelectedItem();

            if (selected == null)
                return;

            itemSelected.accept(selected);

            onBack.run();
        });

        backButton.setOnAction(e -> onBack.run());

        HBox buttons =
                new HBox(20, actionButton, backButton);

        buttons.setAlignment(Pos.CENTER);
        return buttons;
    }
}
