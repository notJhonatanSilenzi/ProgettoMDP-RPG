package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.*;
import it.unicam.cs.mpgc.rpg126164.abstractions.Character;

/**
 * This class represents a playable character in the game, and it's represented by a name, a description, an
 * archetype and also an inventory and a sheet for fights. The starting sheet and inventory are built according
 * to the chosen archetype, and the player is allowed to buy or sell items in the shop, to interact with the
 * inventory, to consume items and to attack the enemy. Attacking takes in count the current weapon
 */
public class PlayableCharacter extends Character implements Fighter {

    private final CharacterSheet sheet;
    private final Archetype archetype;
    private final InventoryBehaviour inventory;

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
    }


    /**
     * This method represents the possibility to use a consumable item to give himself some extra advantages.
     * Consuming an item causes the player to lose one turn
     * @param consumable the item to consume
     */
    public void usePotion(Consumable consumable) {
        if (consumable == null)
            throw new IllegalArgumentException("Null parameter");

        consumable.consume(this);
        inventory.drop(new ItemStack(consumable, 1));
    }

    /**
     * This method is activable in the shop and allows the player to buy a certain amount of an item, causing
     * a loss of money in the wallet
     * @param itemStack the stack of item to purchase
     */
    public void buyItem(ItemStack itemStack) {
        if (itemStack == null) throw new IllegalArgumentException("Null parameter");

        inventory.collect(itemStack);
        inventory.getMoneyCollector().spend(itemStack.getItem().getTradeValue() * itemStack.getCount());
    }

    /**
     * This method is activable in the shop and allows the player to sell a certain amount of an item,
     * causing an increase of money in the wallet
     * @param itemStack the stack of item to sell
     */
    public void sellItem(ItemStack itemStack) {
        if (itemStack == null) throw new IllegalArgumentException("Null parameter");

        inventory.drop(itemStack);
        inventory.getMoneyCollector().cash((itemStack.getItem().getTradeValue() * itemStack.getCount()) / 2);
    }


    // GETTERS AND SETTERS

    @Override
    public CharacterSheet getSheet() { return this.sheet; }

    @Override
    public Archetype getArchetype() { return this.archetype; }

    @Override
    public Equipment getEquipment() { return this.inventory.getCurrentEquipment(); }

    @Override
    public void setEquipment(Equipment equipment) { this.inventory.equip(equipment); }

    public InventoryBehaviour getInventory() { return this.inventory; }
}
