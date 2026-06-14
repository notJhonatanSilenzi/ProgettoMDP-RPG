package it.unicam.cs.mpgc.rpg126164.services.utils;

import it.unicam.cs.mpgc.rpg126164.domain.characters.stats.Archetype;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.Item;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.equipment.Equipment;
import it.unicam.cs.mpgc.rpg126164.domain.inventory.*;
import it.unicam.cs.mpgc.rpg126164.persistance.repositories.PotionRepository;
import it.unicam.cs.mpgc.rpg126164.persistance.repositories.WeaponRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * This class works as a builder for the player's inventory, equipment manager and wallet at the building
 * of a new playable character and a new world game
 */
public class PlayerBuilder {

    private final PotionRepository potionRepository;
    private final WeaponRepository weaponRepository;

    /**
     * Creates a player builder
     * @param potionRepository the potion repository
     * @param weaponRepository the weapon repository
     */
    public PlayerBuilder(PotionRepository potionRepository, WeaponRepository weaponRepository) {
        this.potionRepository = potionRepository;
        this.weaponRepository = weaponRepository;
    }

    /**
     * Builds the inventory, according to the chosen archetype
     * @param archetype the chosen archetype
     * @return the complete starting inventory
     */
    public InventoryBehaviour buildInventory(Archetype archetype) {
        Map<Item, ItemStack> items = new HashMap<>();
        Item health1 = potionRepository.findByName("Health Potion I");
        Item harm1 = potionRepository.findByName("Harming Potion I");
        Item customItem = this.getCustomItem(archetype);

        items.put(health1, new ItemStack(health1, 2));
        items.put(harm1, new ItemStack(harm1, 1));
        items.put(customItem, new ItemStack(customItem, 1));

        return new Inventory(items);
    }

    /**
     * Returns a custom item, decided according to the chosen archetype
     * @param archetype the chosen archetype
     * @return the custom item
     */
    private Item getCustomItem(Archetype archetype) {
        return switch (archetype) {
            case WARRIOR -> potionRepository.findByName("Attack Boost Potion I");
            case BERSERKER -> potionRepository.findByName("Defense Boost Potion I");
            case CLERIC, SORCERER -> potionRepository.findByName("Weakness Potion I");
        };
    }

    /**
     * Returns an equipment manager for the player, which builds the equipped weapon at the start of
     * the game, according to the chosen archetype
     * @param archetype the chosen archetype
     * @return the equipment manager
     */
    public EquipmentManager buildEquipmentManager(Archetype archetype) {
        Equipment weapon = switch (archetype) {
            case WARRIOR -> weaponRepository.findByName("Iron Spear");
            case BERSERKER -> weaponRepository.findByName("Iron Sword");
            case CLERIC -> weaponRepository.findByName("Simple Spell");
            case SORCERER -> weaponRepository.findByName("Fire Spell");
        };
        EquipmentManager equipmentManager = new EquipmentManager();
        equipmentManager.equip(weapon);
        return equipmentManager;
    }

    /**
     * Builds a wallet for the player at the start of the game
     * @return the starting wallet
     */
    public MoneyCollector buildWallet() { return new Wallet("Gold", 300); }
}
