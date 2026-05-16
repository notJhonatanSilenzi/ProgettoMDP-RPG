package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.Item;

/**
 * This class represents an item stack that can be added or deleted in the inventory. It refers to an item, and it
 * contains the amount and the max amount for this item. For equipments, it has to be 1 and maximum 1
 */
public class ItemStack {

    private final Item item;
    private int count;
    private final int maxAmount;

    /**
     * Creates an item stack
     * @param item the referenced item
     * @param count its current amount
     * @param maxAmount the maximum amount reachable for this item
     */
    public ItemStack(Item item, int count, int maxAmount) {
        if (item == null || count <= 0 || maxAmount <= 0 || count > maxAmount)
            throw new IllegalArgumentException("Invalid parameters for item stack");

        this.item = item;
        this.count = count;
        this.maxAmount = maxAmount;
    }

    // GETTERS AND SETTERS

    public Item getItem() { return item; }

    public int getCount() { return count; }

    public int getMaxAmount() { return maxAmount; }

    public void setCount(int count) { this.count = count; }
}
