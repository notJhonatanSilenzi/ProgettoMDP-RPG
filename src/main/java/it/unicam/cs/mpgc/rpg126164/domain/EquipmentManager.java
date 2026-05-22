package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.Equipment;

/**
 * This class represents a utility to manage the equipment of a player. It allows to equip an item and to
 * get the currently equipped item.
 */
public class EquipmentManager {

    private Equipment current;

    /**
     * Creates an Equipment Manager for a playable character
     */
    public EquipmentManager() { this.current = null; }

    /**
     * Sets the given equipable item as current item
     * @param equipment the item to equip
     */
    public void equip(Equipment equipment) {
        if (equipment == null) throw new IllegalArgumentException("Null parameter");

        this.current = equipment;
    }

    /**
     * Returns the current equipment
     * @return the current equipment
     */
    public Equipment getCurrentEquipment() {
        if (current == null) throw new IllegalArgumentException("Null parameter");

        return this.current;
    }
}
