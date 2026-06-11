package it.unicam.cs.mpgc.rpg126164.domain.world.gameplay;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayerFighter;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayableCharacter;
import it.unicam.cs.mpgc.rpg126164.domain.inventory.InventoryBehaviour;

/**
 * This interface represents a generic market that can be added to the world game. In this area, players can enter
 * to buy innovative items or to sell their own in exchange for money.
 */
public interface Market {

    /**
     * Allows the getPlayer to enter in this area of the game
     * @param player the getPlayer that enters the market
     */
    void enter(PlayerFighter player);

    /**
     * This method allows the getPlayer to buy a certain amount of an item, specified in the item stack, from the market.
     * This action causes the loss of money for the getPlayer, but also the collection, and the item gets removed
     * from the items on sale in the market
     * @param itemStack the item stack to buy
     */
    void buy(ItemStack itemStack);

    /**
     * This method allows the getPlayer to sell a certain amount of an item, specified in the item stack, to the market.
     * Selling items causes the gain of money for the getPlayer, but the sold items are lost forever.
     * @param itemStack the item stack to sell
     */
    void sell(ItemStack itemStack);

    /**
     * Allows the getPlayer to exit the emporium, leaving this area
     */
    void exit();

    /**
     * Returns the inventory of this market
     * @return the inventory of this market
     */
    InventoryBehaviour getWarehouse();
}
