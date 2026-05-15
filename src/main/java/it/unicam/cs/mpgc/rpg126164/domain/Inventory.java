package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.Equipment;
import it.unicam.cs.mpgc.rpg126164.abstractions.InventoryBehaviour;
import it.unicam.cs.mpgc.rpg126164.abstractions.Item;
import it.unicam.cs.mpgc.rpg126164.abstractions.MoneyCollector;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a concrete inventory for a playable character. It contains the current equipment, the
 * items collection with their quantity and the wallet. It implements InventoryBehaviour
 */
public class Inventory implements InventoryBehaviour {

    private Equipment current;
    private List<ItemStack> items;
    private final MoneyCollector wallet;
    private final int INVENTORY_SIZE = 5;

    /**
     * Creates an inventory
     * @param items the starting items collection
     * @param wallet the wallet with money
     */
    public Inventory(List<ItemStack> items, MoneyCollector wallet) {
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
    @SuppressWarnings("SuspiciousMethodCalls")
    public void equip(Equipment equipment) {
        if (equipment == null) throw new IllegalArgumentException("Equipment cannot be null");

        if (this.getItems().contains(equipment))
            throw new RuntimeException("Cannot equip an item that is not in the inventory");

        this.current = equipment;
    }

    /**
     * Collects the given item at the given amount in the inventory
     * @param stack the stack of item to collect
     */
    public void collect(ItemStack stack) {
        if (stack == null) throw new IllegalArgumentException("Parameters cannot be null");

        // If there's space and the player hasn't got this item in this inventory
        if (!(this.items.contains(stack)) && items.size() <= INVENTORY_SIZE)
            this.items.add(stack);

        // If there's already this item in this inventory
        for (ItemStack is : this.items)
            if (is.getItem().equals(stack.getItem()))
                if (is.getCount() + stack.getCount() <= is.getMaxAmount())
                    is.setCount(is.getCount() + stack.getCount());
                else {
                    is.setCount(is.getMaxAmount());
                    throw new RuntimeException("Cannot collect enough units of this item");
                }
    }

    public void drop(ItemStack stack) {
        if (stack == null || !(this.getItems().contains(stack.getItem())))
            throw new IllegalArgumentException("Invalid parameters");

        // Find the item
        int i = -1;
        for (ItemStack is : this.items)
            if (is.getItem().equals(stack.getItem())) i = items.indexOf(is);

        if (items.get(i).getCount() - stack.getCount() > 0) // Item remains in inventory
            items.get(i).setCount(items.get(i).getCount() - stack.getCount());
        else this.items.remove(i); // Item doesn't remain in inventory
    }

    @Override
    public Equipment getCurrentEquipment() { return current; }


    // GETTERS

    public List<ItemStack> getStacks() { return items; }

    public MoneyCollector getWallet() { return wallet; }

    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        for (ItemStack stack : this.items)
            items.add(stack.getItem());
        return items;
    }
}
