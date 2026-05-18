package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.Equipment;
import it.unicam.cs.mpgc.rpg126164.abstractions.InventoryBehaviour;
import it.unicam.cs.mpgc.rpg126164.abstractions.Item;
import it.unicam.cs.mpgc.rpg126164.abstractions.MoneyCollector;

import java.util.Map;

/**
 * This class represents a concrete inventory for a playable character. It contains the current equipment, the
 * items collection with their quantity and the wallet. It implements InventoryBehaviour
 */
public class Inventory implements InventoryBehaviour {

    private Equipment current;
    private final Map<Item, ItemStack> items;
    private final MoneyCollector wallet;
    @SuppressWarnings("FieldCanBeLocal")
    private final int INVENTORY_SIZE = 5;

    /**
     * Creates an inventory
     * @param items the starting items collection
     * @param wallet the wallet with money
     */
    public Inventory(Map<Item, ItemStack> items, MoneyCollector wallet) {
        if (items == null || wallet == null)
            throw new IllegalArgumentException("Invalid parameters for inventory");

        this.current = null;
        this.items = items;
        this.wallet = wallet;
    }

    /**
     * Equips the given equipment as current item
     * @param equipment the equipable item to set as current item
     */
    @Override
    public void equip(Equipment equipment) {
        if (equipment == null) throw new IllegalArgumentException("Equipment cannot be null");

        if (this.getItems().containsKey(equipment))
            throw new RuntimeException("Cannot equip an item that is not in the inventory");

        this.current = equipment;
    }

    /**
     * Collects the given item at the given amount in the inventory
     * @param stack the stack of item to collect
     */
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

    @Override
    public Equipment getCurrentEquipment() { return current; }


    // GETTERS

    @Override
    public MoneyCollector getMoneyCollector() { return wallet; }

    public Map<Item, ItemStack> getItems() { return items; }
}
