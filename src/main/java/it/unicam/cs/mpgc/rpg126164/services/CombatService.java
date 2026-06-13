package it.unicam.cs.mpgc.rpg126164.services;

import it.unicam.cs.mpgc.rpg126164.domain.characters.*;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.equipment.Equipment;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.Consumable;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.PotionTargetType;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.combat.Fight;

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

        int playerDamage = fight.playerAttackEnemy(enemy);
        int damage = fight.enemyCounterAttack(enemy);
        String enemyTurn = resolveEnemyTurnCounterattack(damage, fight, enemy);
        return new CombatTurnResult(
                fight.getPlayer().getName() + " dealt " + playerDamage + " damage to " + enemy.getName(),
                enemyTurn,
                !enemy.getSheet().isAlive()
        );
    }

    /**
     * Allows the player to consume a potion during the fight
     * @param fight  the fight to process
     * @param potion the potion to consume
     * @param target  the index of the fighter that receives the effects of the potion. If it's below
     *               zero, the player receives the effects
     * @return the output string for the UI
     */
    public CombatTurnResult playerConsumesPotion(Fight fight, Consumable potion, Fighter target) {
        if (fight == null || potion == null) throw new IllegalArgumentException("Invalid parameters");

        String playerTurn = resolvePlayerTurnOutputAfterPotion(fight, potion, target);

        EnemyFighter enemy = (potion.getTargetType() == PotionTargetType.SELF) ? fight.getCurrentEnemies().getFirst() : (EnemyFighter) target;
        int damage = fight.enemyCounterAttack(enemy);
        String enemyTurn = resolveEnemyTurnCounterattack(damage, fight, enemy);

        return new CombatTurnResult(
                playerTurn,
                enemyTurn,
                !target.getSheet().isAlive()
        );
    }

    private String resolveEnemyTurnCounterattack(int damage, Fight fight, EnemyFighter enemy) {
        String output;
        if (damage < 0) output = enemy.getName() + " has been defeated!";
        else if (damage == 0) output = fight.getPlayer().getName() + " has evaded the attack!";
        else output = enemy.getName() + " dealt " + damage + " to " + fight.getPlayer().getName();
        return output;
    }

    private String resolvePlayerTurnOutputAfterPotion(Fight fight, Consumable potion, Fighter target) {
        return (potion.getTargetType() == PotionTargetType.SELF)
                ? fight.getPlayer().getName() + " consumed " + potion.getName() + " and gained " +
                fight.consumeItem(target, potion) + " " + potion.getStatsType().toString().toLowerCase()
                : fight.getPlayer().getName() + " consumed " + potion.getName() + " and reduced " +
                target.getName() + " " + potion.getStatsType().toString().toLowerCase() + " by " + fight.consumeItem(target, potion) + "!";
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
