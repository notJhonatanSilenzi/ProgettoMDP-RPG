package it.unicam.cs.mpgc.rpg126164.abstractions;

import it.unicam.cs.mpgc.rpg126164.domain.ItemStack;

/**
 * This interface allows any object that implements this interface to work as an inventory. The inventory allows
 * to collect, drop and equip items and equipment
 */
public interface InventoryBehaviour {

    /**
     * Returns the current equipment in use by this character
     * @return the current equipment
     */
    Equipment getCurrentEquipment();

    /**
     * this method allows to equip the given equipable item
     * @param equipment the equipable item to set as current item
     */
    void equip(Equipment equipment);

    /**
     * this method allows to collect a certain amount of an item
     * @param stack the stack of item to collect
     */
    void collect(ItemStack stack);

    /**
     * This method allows to drop a certain amount of an item
     * @param stack the stack of item to drop from the inventory
     */
    void drop(ItemStack stack);
}
