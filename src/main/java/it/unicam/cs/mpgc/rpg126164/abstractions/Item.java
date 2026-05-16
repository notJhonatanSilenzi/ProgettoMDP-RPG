package it.unicam.cs.mpgc.rpg126164.abstractions;

import java.util.Objects;

/**
 * This abstract class represents a single item that can be collected in the inventory of a character. It cannot
 * be instantiated by itself, it requires specifications. It's defined by name, description, maxAmount and the value
 * on the market for a single item
 */
public abstract class Item {

    private String name;
    private String description;
    private int maxAmount;
    private final int tradeValue;

    /**
     * This constructor creates a generic item
     * @param name the name of the item
     * @param description a more accurate description
     * @param maxAmount the maximum number of this item that can stay together in the inventory
     * @throws IllegalArgumentException if the parameters are not valid
     */
    public Item(String name, String description, int maxAmount, int tradeValue) {
        if (name.isEmpty() || description.isEmpty() || maxAmount <= 0 || tradeValue <= 0)
            throw new IllegalArgumentException("Invalid item parameters");

        this.name = name;
        this.description = description;
        this.maxAmount = maxAmount;
        this.tradeValue = tradeValue;
    }

    /**
     * equals() method for items
     * @param obj   the reference object with which to compare.
     * @return true if obj is equal to this item, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;

        return ((Item) obj).getName().equals(this.getName());
    }

    /**
     * Returns the hash code of this item, based on the name
     * @return the hash code of this item
     */
    @Override
    public int hashCode() { return Objects.hash(name); }


    // GETTER AND SETTERS METHODS

    public String getName() { return name; }

    public String getDescription() { return description; }

    public int getMaxAmount() { return maxAmount; }

    public int getTradeValue() { return tradeValue; }
}
