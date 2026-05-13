package it.unicam.cs.mpgc.rpg126164.abstractions;

/**
 * This interface allows any object that implements this interface to work as an inventory. The inventory allows
 * to collect, drop and equip items and equipment
 */
public interface InventoryBehaviour {

    /**
     * this method allows to equip the given equipable item
     * @param equipment the equipable item to set as current item
     */
    void equip(Equipment equipment);

    /**
     * this method allows to collect a certain amount of an item
     * @param item the item to collect
     * @param count the amount of item to collect
     */
    void collect(Item item, int count);

    /**
     * This method allows to drop a certain amount of an item
     * @param item the item to drop from the inventory
     * @param count the amount of item to drop
     */
    void drop(Item item, int count);
}
