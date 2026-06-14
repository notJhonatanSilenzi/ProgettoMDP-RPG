package it.unicam.cs.mpgc.rpg126164.domain.inventory;

import it.unicam.cs.mpgc.rpg126164.domain.collectibles.equipment.Equipment;

import java.io.Serializable;

/**
 * This class represents a utility to manage the equipment of a getPlayer. It allows to equip an item and to
 * get the currently equipped item.
 */
public class EquipmentManager implements Serializable {

    private Equipment current;

    /**
     * Creates an Equipment Manager for a playable character
     */
    public EquipmentManager() { this.current = null; }

    /**
     * Sets the given equipable item as current item
     * @param equipment the item to equip
     * @throws IllegalArgumentException if the given equipment is null
     */
    public void equip(Equipment equipment) {
        if (equipment == null) throw new IllegalArgumentException("Null parameter");

        this.current = equipment;
    }

    /**
     * Returns the current equipment
     * @return the current equipment
     * @throws IllegalArgumentException if the current equipment is null
     */
    public Equipment getCurrentEquipment() {
        if (current == null) throw new IllegalArgumentException("Null parameter");

        return this.current;
    }
}
