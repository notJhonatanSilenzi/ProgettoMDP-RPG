package it.unicam.cs.mpgc.rpg126164.domain.characters;

import it.unicam.cs.mpgc.rpg126164.domain.characters.stats.Archetype;
import it.unicam.cs.mpgc.rpg126164.domain.characters.stats.CharacterSheet;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.Item;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.Consumable;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.equipment.Equipment;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.domain.inventory.EquipmentManager;
import it.unicam.cs.mpgc.rpg126164.domain.inventory.InventoryBehaviour;
import it.unicam.cs.mpgc.rpg126164.domain.inventory.InventoryBuilder;
import it.unicam.cs.mpgc.rpg126164.domain.inventory.MoneyCollector;

import java.io.Serializable;
import java.util.Map;

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
public class PlayableCharacter extends Character implements Fighter, Serializable {

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
    public PlayableCharacter(String name, String description, Archetype archetype) {
        super(name, description);
        this.archetype =  archetype;
        this.sheet = archetype.getSheet();
        this.inventory = archetype.getInventory();
        this.equipmentManager = new EquipmentManager();
        this.wallet = InventoryBuilder.getWallet();

        for (Map.Entry<Item, ItemStack> entry : inventory.getItems().entrySet())
            if (entry.getKey() instanceof Equipment)
                equipmentManager.equip((Equipment) entry.getKey());
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

    /**
     * This method represents the possibility to use a consumable item to give himself some extra advantages.
     * Consuming an item causes the getPlayer to lose one turn
     * @param consumable the item to consume
     */
    public void consumeItem(Consumable consumable, Fighter target) {
        if (consumable == null)
            throw new IllegalArgumentException("Null parameter");

        consumable.consume(target);
        inventory.drop(new ItemStack(consumable, 1));
    }

    /**
     * This method represents for this getPlayer the possibility to collect a certain amount of an item
     * @param stack the stack of items to collect
     */
    public void collectItem(ItemStack stack) {
        if (stack == null)
            throw new IllegalArgumentException("Null parameter");

        inventory.collect(stack);
    }

    /**
     * This method represents the possibility to drop a certain amount of an item
     * @param stack the stack of items to drop
     */
    public void dropItem(ItemStack stack) {
        if (stack == null)
            throw new IllegalArgumentException("Null parameter");

        inventory.drop(stack);
    }


    // GETTERS AND SETTERS

    @Override
    public CharacterSheet getSheet() { return this.sheet; }

    @Override
    public Archetype getArchetype() { return this.archetype; }

    @Override
    public int getWeaponAttack() { return this.getCurrentEquipment().useEquipment(); }

    public Equipment getCurrentEquipment() { return this.equipmentManager.getCurrentEquipment(); }

    public InventoryBehaviour getInventory() { return this.inventory; }

    public EquipmentManager getEquipmentManager() { return this.equipmentManager; }

    public MoneyCollector getMoneyCollector() { return this.wallet; }
}
