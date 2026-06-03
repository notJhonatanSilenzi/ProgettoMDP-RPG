package it.unicam.cs.mpgc.rpg126164.domain.inventory;

import it.unicam.cs.mpgc.rpg126164.domain.collectibles.Item;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;

import java.io.Serializable;
import java.util.Map;

/**
 * This class represents a concrete inventory for a playable character or a market. It implements InventoryBehaviour and
 * it contains all the items that the object has currently stored in
 */
public class Inventory implements InventoryBehaviour, Serializable {

    private final Map<Item, ItemStack> items;
    @SuppressWarnings("FieldCanBeLocal")
    private final int INVENTORY_SIZE = 5;

    /**
     * Creates an inventory
     * @param items the starting items collection
     */
    public Inventory(Map<Item, ItemStack> items) {
        if (items == null)
            throw new IllegalArgumentException("Invalid parameters for inventory");

        this.items = items;
    }

    @Override
    public void collect(ItemStack stack) {
        if (stack == null) throw new IllegalArgumentException("Parameters cannot be null");

        Item item = stack.getItem();
        ItemStack itemStack = this.items.get(item);
        // Item is already in the inventory
        if (this.items.containsKey(item))
            itemStack.setCount(Math.min(itemStack.getCount() + stack.getCount(), item.getMaxAmount()));

        // Item isn't already in the inventory
        if (this.items.size() < INVENTORY_SIZE)
            this.items.put(stack.getItem(), stack);
        else throw new IllegalArgumentException("Cannot collect this item, inventory is full or item is already in the inventory");
    }

    @Override
    public void drop(ItemStack stack) {
        if (stack == null || stack.getCount() > this.items.get(stack.getItem()).getCount()
                || !(this.items.containsKey(stack.getItem())))
            throw new IllegalArgumentException("Invalid parameters");

        Item item = stack.getItem();
        if (this.items.get(item).getCount() - stack.getCount() > 0) // Item remains in the inventory
            this.items.get(item).setCount(this.items.get(item).getCount() - stack.getCount());
        else this.items.remove(item); // Item doesn't remain in the inventory
    }



    // GETTERS

    public Map<Item, ItemStack> getItems() { return items; }
}
