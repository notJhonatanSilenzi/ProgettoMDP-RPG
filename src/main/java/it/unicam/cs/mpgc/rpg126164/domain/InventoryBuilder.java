package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.*;

import java.util.HashMap;
import java.util.Map;

/**
 * This static class helps in building the starting inventory for a playable character
 */
public final class InventoryBuilder {
    // TODO - adattare quando verrà messa la persistenza
    private InventoryBuilder() {}

    /**
     * Builds the inventory based on the archetype
     * @param archetype the chosen archetype
     * @return the correct inventory for this archetype
     */
    public static InventoryBehaviour getInitialInventory(Archetype archetype) {
        InventoryBehaviour inv = new Inventory(new HashMap<>(getPotions()));
        switch(archetype) {
            case WARRIOR -> getWarriorWeapon(inv);
            case BERSERKER -> getBerserkerWeapon(inv);
            case CLERIC -> getClericSpell(inv);
            case SORCERER -> getSorcererSpell(inv);
        }
        return inv;
    }

    /**
     * Returns the initial weapon for a warrior player
     * @param inv the inventory to which the weapon will be added and equipped
     */
    private static void getWarriorWeapon(InventoryBehaviour inv) {
        Equipment spear = new DamageSource("Iron Spear", "A simple spear, used by warriors", 1, 120, 4);
        inv.collect(new ItemStack(spear, 1));
    }

    /**
     * Returns the initial weapon for a berserker player
     * @param inv the inventory to which the weapon will be added and equipped
     */
    private static void getBerserkerWeapon(InventoryBehaviour inv) {
        Equipment sword = new DamageSource("Iron Sword", "A simple sword, used by berserkers", 1, 150, 5);
        inv.collect(new ItemStack(sword, 1));
    }

    /**
     * Returns the initial spell for a cleric player
     * @param inv the inventory to which the spell will be added and equipped
     */
    private static void getClericSpell(InventoryBehaviour inv) {
        Equipment simpleSpell = new DamageSource("Simple Spell", "A simple spell, used by clerics", 1, 140, 3);
        inv.collect(new ItemStack(simpleSpell, 1));
    }

    /**
     * Returns the initial spell for a sorcerer player
     * @param inv the inventory to which the spell will be added and equipped
     */
    private static void getSorcererSpell(InventoryBehaviour inv) {
        Equipment fireSpell = new DamageSource("Fire Spell", "A dangerous spell, used by sorcerers", 1, 220, 6);
        inv.collect(new ItemStack(fireSpell, 1));
    }

    /**
     * Returns the initial potions for every archetype, which are the same for all of them
     * @return the initial amount of potions to be added in the inventory
     */
    private static Map<Item, ItemStack> getPotions() {
        Map<Item, ItemStack> potions = new HashMap<>();
        Potion healingPotion = new HealingPotion(
                "healing potion I", "This potion heals you, giving extra hp", 5,
                60, PotionTargetType.SELF, 20);
        Potion harmingPotion = new HarmingPotion(
                "harming potion lvl 1", "This potion harms the enemy, taking away hp", 5,
                120, PotionTargetType.ENEMY, 20);
        potions.put(healingPotion, new ItemStack(healingPotion, 2));
        potions.put(harmingPotion, new ItemStack(harmingPotion, 1));
        return potions;
    }

    /**
     * Returns the initial money collector for every player, which will be the same for all of them
     * @return the initial money collector
     */
    public static MoneyCollector getWallet() { return new Wallet("gold", 200); }
}
