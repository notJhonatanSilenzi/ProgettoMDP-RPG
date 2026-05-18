package it.unicam.cs.mpgc.rpg126164.abstractions;

import java.util.Objects;

/**
 * This abstract class represents a generic Item that can be collected in the inventory. It implements the Item
 * interface, and it contains a name, a description, a maxAmount and a tradeValue. It can't be instantiated
 */
public abstract class AbstractItem implements Item {

    private final String name;
    private final String description;
    private final int maxAmount;
    private final int tradeValue;

    /**
     * Creates a generic item
     * @param name its name
     * @param description its description
     * @param maxAmount the maximum amount of this item that can be collected in the inventory
     * @param tradeValue its value in the market
     */
    public AbstractItem(String name, String description, int maxAmount, int tradeValue) {
        if (name == null || description == null || name.isEmpty() || description.isEmpty() || maxAmount <= 0 || tradeValue <= 0)
            throw new IllegalArgumentException("Invalid parameters");

        this.name = name;
        this.description = description;
        this.maxAmount = maxAmount;
        this.tradeValue = tradeValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AbstractItem)) return false;

        return (this.name.equals(((AbstractItem) obj).name));
    }

    @Override
    public int hashCode() { return Objects.hash(name); }

    @Override
    public String getName() { return name; }

    @Override
    public String getDescription() { return description; }

    @Override
    public int getMaxAmount() { return maxAmount; }

    @Override
    public int getTradeValue() { return tradeValue; }
}
