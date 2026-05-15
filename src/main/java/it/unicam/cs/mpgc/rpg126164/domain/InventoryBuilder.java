package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.Equipment;
import it.unicam.cs.mpgc.rpg126164.abstractions.InventoryBehaviour;
import it.unicam.cs.mpgc.rpg126164.abstractions.Item;
import it.unicam.cs.mpgc.rpg126164.abstractions.MoneyCollector;

import java.util.ArrayList;
import java.util.List;

/**
 * This static class helps in building the starting inventory for a playable character
 */
public final class InventoryBuilder {

    private InventoryBuilder() {}

    public static InventoryBehaviour getInitialInventory(Archetype archetype) {
        return buildInventory(archetype);
    }

    private static InventoryBehaviour buildInventory(Archetype archetype) {
        InventoryBehaviour inv = new Inventory(new ArrayList<>(getPotions()), getWallet());
        switch(archetype) {
            case WARRIOR -> getWarriorWeapon(inv);
            case BERSERKER -> getBerserkerWeapon(inv);
            case CLERIC -> getClericSpell(inv);
            case SORCERER -> getSorcererSpell(inv);
        };
        return inv;
    }

    private static void getWarriorWeapon(InventoryBehaviour inv) {
        Equipment spear = new DamageSource("Iron Spear", "A simple spear, used by warriors", 1, 4);
        inv.collect(new ItemStack((Item) spear, 1, 1));
        inv.equip(spear);
    }

    private static void getBerserkerWeapon(InventoryBehaviour inv) {
        Equipment sword = new DamageSource("Iron Sword", "A simple sword, used by berserkers", 1, 5);
        inv.collect(new ItemStack((Item) sword, 1, 1));
        inv.equip(sword);
    }

    private static void getClericSpell(InventoryBehaviour inv) {
        Equipment simpleSpell = new DamageSource("Simple Spell", "A simple spell, used by clerics", 1, 3);
        inv.collect(new ItemStack((Item) simpleSpell, 1, 1));
        inv.equip(simpleSpell);
    }

    private static void getSorcererSpell(InventoryBehaviour inv) {
        Equipment fireSpell = new DamageSource("Fire Spell", "A dangerous spell, used by sorcerers", 1, 6);
        inv.collect(new ItemStack((Item) fireSpell, 1, 1));
        inv.equip(fireSpell);
    }

    private static List<ItemStack> getPotions() {
        List<ItemStack> potions = new ArrayList<>();
        potions.add(new ItemStack(
                new HealingPotion("healing potion", "This potion heals you, giving extra hp", 5, 12),
                2, 5)
        );
        potions.add(new ItemStack(
                new HarmingPotion("harming potion", "This potion harms the enemy, taking away hp", 5, 15),
                1, 3)
        );
        return potions;
    }

    private static MoneyCollector getWallet() { return new Wallet("gold", 200); }
}
