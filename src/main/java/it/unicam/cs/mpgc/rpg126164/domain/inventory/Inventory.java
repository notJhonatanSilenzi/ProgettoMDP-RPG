package it.unicam.cs.mpgc.rpg126164.domain.inventory;

import it.unicam.cs.mpgc.rpg126164.domain.collectibles.Item;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.equipment.Equipment;

import java.io.Serializable;
import java.util.Map;

/**
 * This class represents a concrete inventory for a playable character or a market. It implements InventoryBehaviour, and
 * it contains all the items that the object has currently stored in
 */
@SuppressWarnings("ClassCanBeRecord")
public class Inventory implements InventoryBehaviour, Serializable {

    private final Map<Item, ItemStack> items;

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
        // Item is already in the inventory
        if (this.items.containsKey(item)) {
            ItemStack itemStack = this.items.get(item);
            itemStack.setCount(Math.min(itemStack.getCount() + stack.getCount(), item.getMaxAmount()));
        } else this.items.put(stack.getItem(), stack); // Item isn't in the inventory
    }

    @Override
    public void drop(ItemStack stack) {
        if (stack == null) throw new IllegalArgumentException("Invalid parameters");

        Item item = stack.getItem();
        ItemStack itemStack = this.items.get(item);

        if (itemStack == null || stack.getCount() > itemStack.getCount())
            throw new IllegalArgumentException("Invalid parameters");

        int remaining = itemStack.getCount() - stack.getCount();
        if (remaining > 0) itemStack.setCount(remaining);
        else this.items.remove(item);
    }

    @Override
    public boolean canAdd(ItemStack stack) {
        if (stack == null) throw new IllegalArgumentException("Invalid parameters");

        if (!this.items.containsKey(stack.getItem())) return true;
        return items.get(stack.getItem()).getCount() + stack.getCount() <= stack.getItem().getMaxAmount();
    }


    @Override
    public int getWeaponCount() {
        int count = 0;
        for (Item entry : this.items.keySet())
            if (entry instanceof Equipment)
                count += 1;
        return count;
    }



    // GETTERS

    @Override
    public Map<Item, ItemStack> getItems() { return items; }
}
