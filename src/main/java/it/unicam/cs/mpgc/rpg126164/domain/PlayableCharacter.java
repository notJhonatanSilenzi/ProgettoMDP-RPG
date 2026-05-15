package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.*;
import it.unicam.cs.mpgc.rpg126164.abstractions.Character;

public class PlayableCharacter extends Character implements Fighter {

    private final CharacterSheet sheet;
    private final Archetype archetype;
    private final InventoryBehaviour inventory;

    public PlayableCharacter(String name, Archetype archetype) {
        super(name);
        this.archetype =  archetype;
        this.sheet = archetype.getSheet();
        this.inventory = archetype.getInventory();
    }

    @Override
    public CharacterSheet getSheet() { return this.sheet; }

    @Override
    public Archetype getArchetype() { return this.archetype; }

    @Override
    public Equipment getEquipment() { return this.inventory.getCurrentEquipment(); }

    @Override
    public void setEquipment(Equipment equipment) { this.inventory.equip(equipment); }

    public void attack(Fighter enemy) {
        // TODO - ragionarci meglio
        this.getEquipment().useEquipment((Character) enemy);
    }

    public void usePotion(Item item) {
        if (item instanceof Equipment)
            throw new IllegalArgumentException("Can't use an equipment as a potion");

        if (item instanceof Consumable) ((Consumable) item).consume(this);
    }

    public void buyItem(ItemStack itemStack) {
        // TODO - ragionarci meglio
    }

    public void sellItem(ItemStack itemStack) {
        // TODO - ragionarci meglio
    }
}
