package it.unicam.cs.mpgc.rpg126164.domain.characters;

import it.unicam.cs.mpgc.rpg126164.domain.collectibles.equipment.Equipment;
import it.unicam.cs.mpgc.rpg126164.domain.inventory.InventoryBehaviour;
import it.unicam.cs.mpgc.rpg126164.domain.inventory.MoneyCollector;

/**
 * This interface is a specific Fighter interface for playable characters
 */
public interface PlayerFighter extends Fighter {

    /**
     * Returns the inventory of this playable character
     * @return the inventory
     */
    InventoryBehaviour getInventory();

    /**
     * Returns the money collector of this playable character
     * @return the money collector (wallet)
     */
    MoneyCollector getMoneyCollector();

    /**
     * Allows to equip the given equipment as current equipment in fights
     * @param equipment the item to equip
     * @throws IllegalArgumentException if the given equipment is null
     */
    void equip(Equipment equipment);
}
