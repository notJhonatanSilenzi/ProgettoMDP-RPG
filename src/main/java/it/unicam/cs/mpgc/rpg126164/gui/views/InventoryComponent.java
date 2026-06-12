package it.unicam.cs.mpgc.rpg126164.gui.views;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayerFighter;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.domain.world.gameplay.Market;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

/**
 * This record helps to maintain the list views of an inventory in order to make them dynamic, and it
 * contains also the inventory HBox for the views
 * @param root the panel for the views
 * @param inventoryList the current inventory list, which has to be updated
 * @param inventoryItems the list observed by the inventory list
 */
public record InventoryComponent(
        HBox root,
        ListView<ItemStack> inventoryList,
        ObservableList<ItemStack> inventoryItems
) {
    /**
     * Refreshes the inventory list of a player's character
     * @param player the player's character
     */
    public void refresh(PlayerFighter player) {
        inventoryItems.setAll(player.getInventory().getItems().values());
    }

    /**
     * Refreshes the inventory list of a market
     * @param market the market
     */
    public void refresh(Market market) {
        inventoryItems.setAll(market.getWarehouse().getItems().values());
    }

    /**
     * Returns the selected item in an inventory list
     * @return the selected item
     */
    public ItemStack getSelectedItem() {
        return inventoryList.getSelectionModel().getSelectedItem();
    }
}