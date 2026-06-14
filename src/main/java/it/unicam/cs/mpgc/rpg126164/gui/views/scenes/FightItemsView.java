package it.unicam.cs.mpgc.rpg126164.gui.views.scenes;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayerFighter;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.combat.FightResult;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.CombatController;
import it.unicam.cs.mpgc.rpg126164.gui.views.utilities.InventoryComponent;
import it.unicam.cs.mpgc.rpg126164.gui.views.utilities.InventoryComponentBuilder;
import it.unicam.cs.mpgc.rpg126164.gui.views.utilities.InventoryMode;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

/**
 * This class works as a view for specific categories of items during a fight
 */
public class FightItemsView {

    private final CombatController combatController;
    private final InventoryMode mode;
    private final Consumer<ItemStack> itemSelected;
    private final Runnable onBack;

    /**
     * Creates a fight items view
     * @param combatController the combat controller
     * @param itemToShow the inventory mode required
     * @param consumer the selected item to consume or use
     * @param onBack the callback to the level combat view
     */
    public FightItemsView(CombatController combatController, InventoryMode itemToShow, Consumer<ItemStack> consumer, Runnable onBack) {
        this.combatController = combatController;
        this.mode = itemToShow;
        this.itemSelected = consumer;
        this.onBack = onBack;
    }

    /**
     * Creates a scene to view potions or weapons
     * @return the fight items scene
     */
    public Scene createScene() {
        PlayerFighter player = combatController.getWorldGame().getPlayer();

        // ===================================== TITLE =====================================
        Label title = new Label(mode == InventoryMode.POTION ? "SELECT A POTION" : "SELECT A WEAPON");
        title.setAlignment(Pos.CENTER);

        // ===================================== INVENTORY =====================================
        InventoryComponentBuilder builder = new  InventoryComponentBuilder();
        InventoryComponent centerContent = builder.buildInventoryComponent(player, mode);
        HBox center = centerContent.root();
        center.setAlignment(Pos.CENTER);

        // ===================================== BUTTONS =====================================
        Label details = new Label();
        HBox buttons = getHBox(centerContent, details);
        buttons.setAlignment(Pos.CENTER);
        VBox bottom = new VBox(10, buttons, details);
        details.setAlignment(Pos.CENTER);
        bottom.setAlignment(Pos.CENTER);

        // ===================================== ROOT =====================================
        BorderPane root = new BorderPane();
        root.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);
        root.setCenter(center);
        BorderPane.setAlignment(buttons, Pos.CENTER);
        root.setBottom(bottom);
        BorderPane.setAlignment(center, Pos.CENTER);
        BorderPane.setMargin(center, new Insets(0, 0, 20, 0));

        // ===================================== STYLE =====================================
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("/css/style.css");
        root.getStyleClass().add("root");
        title.getStyleClass().add("title");
        details.getStyleClass().add("floating-text");

        return scene;
    }

    /**
     * Sets up the buttons for this view
     * @param inventory the inventory component to show
     * @return the button box
     */
    private HBox getHBox(InventoryComponent inventory, Label details) {
        // ===================================== BUTTONS =====================================
        Button actionButton = new Button(mode == InventoryMode.POTION ? "Consume" : "Equip");
        Button backButton = new Button("Back");
        actionButton.setOnAction(_ -> {
            ItemStack selected = inventory.getSelectedItem();
            if (selected == null) return;
            itemSelected.accept(selected);
            details.setText((mode == InventoryMode.POTION) ? "Potion consumed!" : "Weapon equipped!");
            if (!(combatController.getCurrentFight().getFinalResult() == FightResult.LOSE)
                    && !(combatController.getCurrentFight().getFinalResult() == FightResult.WIN))
                onBack.run();
        });
        backButton.setOnAction(_ -> onBack.run());

        HBox buttons = new HBox(20, actionButton, backButton);
        buttons.setAlignment(Pos.CENTER);
        buttons.getStylesheets().add("/css/style.css");
        actionButton.getStyleClass().add("button");
        backButton.getStyleClass().add("button");
        return buttons;
    }
}
