package it.unicam.cs.mpgc.rpg126164.domain.world.gameplay;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayerFighter;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.domain.inventory.InventoryBehaviour;

/**
 * This interface represents a generic market that can be added to the world game. In this area, players can enter
 * to buy innovative items or to sell their own in exchange for money.
 */
public interface Market {

    /**
     * Allows the player to enter in this area of the game
     * @param player the player that enters the market
     * @throws IllegalArgumentException if the player is null
     */
    void enter(PlayerFighter player);

    /**
     * This method allows the player to buy a certain amount of an item, specified in the item stack, from the market.
     * This action causes the loss of money for the player, but also the collection, and the item gets removed
     * from the items on sale in the market
     * @param itemStack the item stack to buy
     * @throws IllegalArgumentException if there's no player in the market, or if the item stack is null,
     * or if the player can't afford this purchase
     */
    void buy(ItemStack itemStack);

    /**
     * This method allows the player to sell a certain amount of an item, specified in the item stack, to the market.
     * Selling items causes the gain of money for the player, but the sold items are lost forever.
     * @param itemStack the item stack to sell
     * @throws IllegalArgumentException if the stack is null, if there's no player in the market or if
     * the player is trying to sell the last equipment of its inventory
     */
    void sell(ItemStack itemStack);

    /**
     * Allows the player to exit the emporium, leaving this area
     * @throws IllegalArgumentException if the player has already left the market
     */
    void exit();

    /**
     * Returns the inventory of this market
     * @return the inventory of this market
     */
    InventoryBehaviour getWarehouse();
}
