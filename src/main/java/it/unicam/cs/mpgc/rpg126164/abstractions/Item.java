package it.unicam.cs.mpgc.rpg126164.abstractions;

/**
 * This abstract class represents a single item that can be collected in the inventory of a character. It cannot
 * be instantiated by itself, it requires specifications. It's defined by name, description, amount and maxAmount
 */
public abstract class Item {

    private String name;
    private String description;
    private int count;
    private int maxAmount;

    /**
     * This constructor creates a generic item
     * @param name the name of the item
     * @param description a more accurate description
     * @param count the current amount of this item in the inventory
     * @param maxAmount the maximum number of this item that can stay together in the inventory
     * @throws IllegalArgumentException if the parameters are not valid
     */
    public Item(String name, String description, int count, int maxAmount) {
        if (name.isEmpty() || description.isEmpty() || count < 0 || maxAmount <= 0)
            throw new IllegalArgumentException("Invalid item parameters");

        this.name = name;
        this.description = description;
        this.count = count;
        this.maxAmount = maxAmount;
    }

    // GETTER METHODS

    public String getName() { return name; }

    public String getDescription() { return description; }

    public int getCount() { return count; }

    public int getMaxAmount() { return maxAmount; }
}
