package it.unicam.cs.mpgc.rpg126164.domain.characters;

import it.unicam.cs.mpgc.rpg126164.domain.characters.stats.Archetype;
import it.unicam.cs.mpgc.rpg126164.domain.characters.stats.CharacterSheet;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.equipment.Equipment;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.domain.inventory.EquipmentManager;
import it.unicam.cs.mpgc.rpg126164.domain.inventory.InventoryBehaviour;
import it.unicam.cs.mpgc.rpg126164.domain.inventory.MoneyCollector;

import java.io.Serializable;

/**
 * This class represents a playable character in the game, and it's represented by:
 * - A name
 * - A description
 * - A character sheet with all the stats
 * - An inventory to store items in and out
 * - An object to manage equipments
 * - A money collector to spend and cash in money
 * The starting sheet and inventory are built according to the chosen archetype.
 */
public class PlayableCharacter extends Character implements PlayerFighter, Serializable {

    private final CharacterSheet sheet;
    private final Archetype archetype;
    private final InventoryBehaviour inventory;
    private final EquipmentManager equipmentManager;
    private final MoneyCollector wallet;

    /**
     * Creates a playable character. The sheet and the inventory are built according to the chosen archetype
     * @param name the name of the character
     * @param description its description
     * @param archetype the chosen archetype
     */
    public PlayableCharacter(String name, String description, Archetype archetype, InventoryBehaviour inv, EquipmentManager manager, MoneyCollector wallet) {
        super(name, description);
        this.archetype =  archetype;
        this.sheet = archetype.getSheet();
        this.inventory = inv;
        this.equipmentManager = manager;
        this.wallet = wallet;
    }

    /**
     * Allows to equip the given equipment as current equipment in fights
     * @param equipment the item to equip
     */
    public void equip(Equipment equipment) {
        if (equipment == null)
            throw new IllegalArgumentException("Null parameter");

        this.equipmentManager.equip(equipment);
    }


    // GETTERS AND SETTERS

    @Override
    public CharacterSheet getSheet() { return this.sheet; }

    @Override
    public Archetype getArchetype() { return this.archetype; }

    @Override
    public int getWeaponAttack() { return this.getCurrentEquipment().useEquipment(); }

    @Override
    public Equipment getCurrentEquipment() { return this.equipmentManager.getCurrentEquipment(); }

    @Override
    public InventoryBehaviour getInventory() { return this.inventory; }

    @Override
    public MoneyCollector getMoneyCollector() { return this.wallet; }
}
