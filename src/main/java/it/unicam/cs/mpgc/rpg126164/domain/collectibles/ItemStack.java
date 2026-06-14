package it.unicam.cs.mpgc.rpg126164.domain.collectibles;

import java.io.Serializable;

/**
 * This class represents an item stack that can be added or deleted in the inventory. It refers to an item, and it
 * contains the current amount for this item. For equipments, it has to be 1 and maximum 1
 */
public class ItemStack implements Serializable {

    private final Item item;
    private int count;

    /**
     * Creates an item stack
     * @param item the referenced item
     * @param count its current amount
     * @throws IllegalArgumentException if the item is null or if the count is below zero or above the
     * max amount allowed for the given item
     */
    public ItemStack(Item item, int count) {
        if (item == null || count <= 0 || count > item.getMaxAmount())
            throw new IllegalArgumentException("Invalid parameters for item stack");

        this.item = item;
        this.count = count;
    }


    // GETTERS AND SETTERS

    public Item getItem() { return item; }

    public int getCount() { return count; }

    public void setCount(int count) { this.count = count; }
}
