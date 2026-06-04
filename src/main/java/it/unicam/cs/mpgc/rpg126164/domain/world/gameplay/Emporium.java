package it.unicam.cs.mpgc.rpg126164.domain.world.gameplay;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayableCharacter;
import it.unicam.cs.mpgc.rpg126164.domain.inventory.InventoryBehaviour;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;

/**
 * This class represents an implementation of the Market interface, and it works as an emporium for the world game.
 * In this area, players can enter to buy and sell items, and it's represented by the getPlayer in the market and by
 * the inventory with the items on sale
 */
public class Emporium implements Market {

    private PlayableCharacter player;
    private final InventoryBehaviour itemsForSale;

    /**
     * Creates an emporium for this world
     */
    public Emporium(InventoryBehaviour itemsForSale) {
        if (itemsForSale == null) throw new IllegalArgumentException("Items for sale cannot be null");

        this.player = null;
        this.itemsForSale = itemsForSale;
    }

    @Override
    public void enter(PlayableCharacter player) {
        if (player == null) throw new IllegalArgumentException("Player cannot be null");

        this.player = player;
    }

    @Override
    public void buy(ItemStack itemStack) {
        if (itemStack == null)
            throw new IllegalArgumentException("Item stack cannot be null");
        if (this.player == null) throw new IllegalStateException("No getPlayer is in the emporium");

        itemsForSale.drop(itemStack);
        player.collectItem(itemStack);
        player.getMoneyCollector().spend(itemStack.getCount() * itemStack.getItem().getTradeValue());;
    }

    @Override
    public void sell(ItemStack itemStack) {
        if (itemStack == null) throw new IllegalArgumentException("Item stack cannot be null");
        if (this.player == null) throw new IllegalStateException("No getPlayer is in the emporium");

        player.dropItem(itemStack);
        player.getMoneyCollector().cash(itemStack.getCount() * itemStack.getItem().getTradeValue() / 2);
    }

    @Override
    public void exit() {
        if (this.player == null) throw new IllegalStateException("No getPlayer is in the emporium but tried to exit");

        this.player = null;
    }


    // GETTERS

    @Override
    public InventoryBehaviour getWarehouse() { return this.itemsForSale; }
}
