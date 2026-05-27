package it.unicam.cs.mpgc.rpg126164.collectibles;

/**
 * This interface represents a single item that can be collected in the inventory of a character. It shows the
 * methods to access to specific fields and the method equals() and hashCode()
 */
public interface Item {

    /**
     * Checks if the given obj is equal to this item, referencing only to the item name
     * @param obj the reference object with which to compare.
     * @return true if the two items are equal, false otherwise
     */
    boolean equals(Object obj);

    /**
     * returns the hash code of this item
     * @return the hash code of this item
     */
    int hashCode();

    /**
     * Returns the name of this item
     * @return the name of this item
     */
    String getName();

    /**
     * Returns the description of this item
     * @return the description of this item
     */
    String getDescription();

    /**
     * Returns the max amount reachable for this item. It's 1 if the item is non-stackable
     * @return the max amount reachable for this item
     */
    int getMaxAmount();

    /**
     * Returns the trade value of this item
     * @return the trade value of this item
     */
    int getTradeValue();
}
