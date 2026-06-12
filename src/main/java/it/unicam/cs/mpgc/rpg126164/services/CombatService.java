package it.unicam.cs.mpgc.rpg126164.services;

import it.unicam.cs.mpgc.rpg126164.domain.characters.*;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.Item;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.equipment.Equipment;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.Consumable;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.PotionTargetType;
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
     * @return the combat turn result
     */
    public CombatTurnResult playerAttackEnemy(Fight fight, EnemyFighter enemy) {
        if (fight == null || enemy == null) throw new IllegalArgumentException("Invalid parameters");

        String enemyTurn = (enemy.getSheet().isAlive())
                ? enemy.getName() + " dealt " + fight.enemyCounterAttack(enemy) + " damage to " + fight.getPlayer().getName()
                : enemy.getName() + " has been defeated!";
        return new CombatTurnResult(
                fight.getPlayer().getName() + " dealt " + fight.playerAttackEnemy(enemy) + " damage to " + enemy.getName(),
                enemyTurn,
                enemy.getSheet().isAlive()
        );
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
    public CombatTurnResult playerConsumesPotion(Fight fight, Consumable potion, Fighter target) {
        if (fight == null || potion == null) throw new IllegalArgumentException("Invalid parameters");

        String playerTurn = (potion.getTargetType() == PotionTargetType.SELF)
                ? fight.getPlayer().getName() + " consumed " + potion.getName() + " and gained " +
                fight.consumeItem(target, potion) + " " + potion.getStatsType()
                : fight.getPlayer().getName() + " consumed " + potion.getName() + " and reduced " +
                target.getName() + " " + potion.getStatsType() + " by " + fight.consumeItem(target, potion) + "!";

        EnemyFighter enemy = (potion.getTargetType() == PotionTargetType.SELF) ? fight.getCurrentEnemies().getFirst() : (EnemyFighter) target;
        String enemyTurn = (!enemy.getSheet().isAlive())
                ? enemy.getName() + " has been defeated!"
                : enemy + " dealt " + fight.enemyCounterAttack(enemy) + " damage to " + fight.getPlayer().getName();
        return new CombatTurnResult(
                playerTurn,
                enemyTurn,
                target.getSheet().isAlive()
        );
    }

    /**
     * Shows the inventory, in order to change weapon or consume a potion
     * @param player the player that is fighting
     * @return the collection of items currently in the player's inventory
     */
    public Map<Item, ItemStack> showInventory(PlayerFighter player) {
        if (player == null) throw new IllegalArgumentException("Player cannot be null");

        return player.getInventory().getItems();
    }

    /**
     * Allows the player to change weapon during the fight
     * @param player the player that changes weapon
     * @param fight the fight to process
     * @param weapon the weapon chosen by the player
     */
    public void changeEquipment(PlayerFighter player, Fight fight, Equipment weapon) {
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
