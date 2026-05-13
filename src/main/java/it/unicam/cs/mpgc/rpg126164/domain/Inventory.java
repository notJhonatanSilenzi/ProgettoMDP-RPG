package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.Equipment;
import it.unicam.cs.mpgc.rpg126164.abstractions.InventoryBehaviour;
import it.unicam.cs.mpgc.rpg126164.abstractions.Item;

import java.util.Map;

/**
 * This class represents a concrete inventory for a playable character. It contains the current equipment, the
 * items collection with their quantity and the wallet. It implements InventoryBehaviour
 */
public class Inventory implements InventoryBehaviour {

    private Equipment current;
    private Map<Item, Integer> items;
    private Wallet wallet;

    /**
     * Creates an inventory
     * @param current the current item, initially null
     * @param items the starting items collection
     * @param wallet the wallet with money
     */
    public Inventory(Equipment current, Map<Item, Integer> items, Wallet wallet) {
        if (current == null || items == null || wallet == null)
            throw new IllegalArgumentException("Invalid parameters for inventory");

        this.current = current;
        this.items = items;
        this.wallet = wallet;
    }

    /**
     * Equips the given equipment as current item
     * @param equipment the equipable item to set as current item
     */
    public void equip(Equipment equipment) {
        if (equipment == null) throw new IllegalArgumentException("Equipment cannot be null");

        this.current = equipment;
    }

    /**
     * Collects the given item at the given amount in the inventory
     * @param item the item to collect
     * @param count the amount of item to collect
     */
    public void collect(Item item, int count) {
        this.checkInventory(item, count);

        if (this.items.containsKey(item)) this.items.put(item, this.items.get(item) + count);
        else this.items.put(item, count);
    }

    /**
     * Removes the given item at the given amount from the inventory
     * @param item the item to drop from the inventory
     * @param count the amount of item to drop
     */
    public void drop(Item item, int count) {
        if (item == null || count <= 0) throw new IllegalArgumentException("Parameters cannot be null");

        if (!(this.items.containsKey(item)))
            throw new RuntimeException("Cannot drop an item that is not in the inventory");

        if (count == item.getMaxAmount()) items.remove(item);
        else this.items.put(item, this.items.get(item) - count);
    }

    /**
     * Makes all the necessary checks for collecting items in the inventory
     * @param item the item to check and collect
     * @param count the given amount
     * @throws IllegalArgumentException if the parameters are invalid
     * @throws RuntimeException if the amount to collect exceeds the maximum amount of the item
     */
    private void checkInventory(Item item, int count) {
        if (item == null || count <= 0) throw new IllegalArgumentException("Parameters cannot be null");

        if (this.items.containsKey(item))
            if (this.items.get(item) + count >= item.getMaxAmount())
                throw new RuntimeException("Cannot collect more than the maximum amount of this item");
            else if (item instanceof Equipment)
                 throw new RuntimeException("Cannot collect more than one of this item");

        if (item instanceof Equipment && count > 1) throw new RuntimeException("Cannot collect more than one of this item");
        if (count > item.getMaxAmount()) throw new RuntimeException("Cannot collect more than the maximum amount of this item");
    }

    // TODO - manca gestione del wallet

    // GETTERS

    public Equipment getCurrent() { return current; }

    public Map<Item, Integer> getItems() { return items; }

    public Wallet getWallet() { return wallet; }
}
