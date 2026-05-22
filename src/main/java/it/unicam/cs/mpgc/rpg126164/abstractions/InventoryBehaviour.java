package it.unicam.cs.mpgc.rpg126164.abstractions;

import it.unicam.cs.mpgc.rpg126164.domain.ItemStack;

import java.util.List;

/**
 * This interface allows any object that implements this interface to work as an inventory. The inventory allows
 * to collect, drop items and also to interact with the assigned money collector. Inventories
 * can be used by players and by any type of market in the game.
 */
public interface InventoryBehaviour {

    /**
     * this method allows to collect a certain amount of an item. If there's not enough space for all the items
     * in the inventory but there's still some space for some of the items to collect, the stack will get to
     * its max amount, specified in the item specifics
     * @param stack the stack of item to collect
     */
    void collect(ItemStack stack);

    /**
     * This method allows to drop a certain amount of an item
     * @param stack the stack of item to drop from the inventory
     */
    void drop(ItemStack stack);
}
