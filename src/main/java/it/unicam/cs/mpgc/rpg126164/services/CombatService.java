package it.unicam.cs.mpgc.rpg126164.services;

import it.unicam.cs.mpgc.rpg126164.domain.characters.Fighter;
import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayableCharacter;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.Item;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.equipment.Equipment;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.Consumable;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.combat.Fight;

import java.util.Map;

/**
 * This class works as a service for the fight system during levels
 */
public class CombatService {

    /**
     * Starts the fight, initializing the fight system and allowing to process turns
     * @param fight the fight to start
     */
    public void startFight(Fight fight) {
        if (fight == null) throw new IllegalArgumentException("Fight cannot be null");

        fight.startFight();
    }

    /**
     * Allows the player to attack the given enemy
     * @param fight the fight to process
     * @param enemy the enemy to attack
     * @return the output string for the UI
     */
    public String playerAttackEnemy(Fight fight, Fighter enemy) {
        if (fight == null || enemy == null) throw new IllegalArgumentException("Invalid parameters");

        return fight.playerAttackEnemy(enemy) + "\n" + fight.enemyCounterAttack(enemy);
    }

    /**
     * Allows the player to consume a potion during the fight
     *
     * @param fight  the fight to process
     * @param potion the potion to consume
     * @param target  the index of the fighter that receives the effects of the potion. If it's below
     *               zero, the player receives the effects
     * @return the output string for the UI
     */
    public String playerConsumesPotion(Fight fight, Consumable potion, Fighter target) {
        if (fight == null || potion == null) throw new IllegalArgumentException("Invalid parameters");

        return (target instanceof PlayableCharacter)
                ? fight.consumeItem(target, potion) + "\n" + fight.enemyCounterAttack(fight.getCurrentEnemies().getFirst())
                : fight.consumeItem(target, potion) + "\n" + fight.enemyCounterAttack(target);
    }

    /**
     * Shows the inventory, in order to change weapon or consume a potion
     * @param player the player that is fighting
     * @return the collection of items currently in the player's inventory
     */
    public Map<Item, ItemStack> showInventory(PlayableCharacter player) {
        if (player == null) throw new IllegalArgumentException("Player cannot be null");

        return player.getInventory().getItems();
    }

    /**
     * Allows the player to change weapon during the fight
     * @param player the player that changes weapon
     * @param fight the fight to process
     * @param weapon the weapon chosen by the player
     */
    public void changeEquipment(PlayableCharacter player, Fight fight, Equipment weapon) {
        if (player == null || fight == null || weapon == null)
            throw new IllegalArgumentException("Invalid parameters");

        fight.equipItem(player, weapon);
    }

    /**
     * Restarts the given fight, in case of loss by the player
     * @param fight the fight to restart
     */
    public void restartLevel(Fight fight) {
        if (fight == null) throw new IllegalArgumentException("Fight cannot be null");

        fight.reset();
        fight.startFight();
    }
}
