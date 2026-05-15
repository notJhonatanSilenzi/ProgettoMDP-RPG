package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.Item;

public class ItemStack {

    private final Item item;
    private int count;
    private final int maxAmount;

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
