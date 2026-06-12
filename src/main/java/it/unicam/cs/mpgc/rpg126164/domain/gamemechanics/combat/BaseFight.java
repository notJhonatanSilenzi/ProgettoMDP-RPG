package it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.combat;

import it.unicam.cs.mpgc.rpg126164.domain.characters.*;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.Consumable;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.equipment.Equipment;

import java.util.List;
import java.util.Objects;

/**
 * This class represents a base fight system to use in the game to process turns and verify the ending of the fight.
 * It implements the Fight interface, and it allows to use even multiple enemies.
 */
public class BaseFight implements Fight {

    private final PlayerFighter player;
    private final List<EnemyFighter> defaultEnemies;
    private List<EnemyFighter> currentEnemies;
    private FightResult result;

    /**
     * Creates a base fight system between fighters
     * @param player the getPlayer character
     * @param enemies the enemies to defeat
     */
    public BaseFight(PlayerFighter player, List<EnemyFighter> enemies) {
        if (player == null || enemies == null || enemies.isEmpty())
            throw new IllegalArgumentException("Invalid parameters");

        this.player = player;
        this.defaultEnemies = enemies;
        this.currentEnemies = enemies;
        this.result = null;
    }

    @Override
    public void startFight() {
        if (result != null) throw new IllegalStateException("Attempt to start an already started fight");

        this.result = FightResult.ON_GOING;
        player.getSheet().reset();
    }

    @Override
    public FightResult getFinalResult() { return this.result; }

    @Override
    public int playerAttackEnemy(EnemyFighter target) {
        if (target == null) throw new IllegalArgumentException("Invalid enemy index");

        int damage = this.applyDamage(player, target);
        if (!target.getSheet().isAlive()) {
            player.getMoneyCollector().cash(target.getEnemyType().getGoldForDefeat());
            currentEnemies.remove(target);
        }
        updateFightStatus();
        return damage;
    }

    @Override
    public int enemyCounterAttack(EnemyFighter enemy) {
        if (currentEnemies.isEmpty()) return -1;

        if (enemy == null) throw new IllegalArgumentException("Invalid enemy index");

        if (!enemy.getSheet().isAlive()) return -1;

        int damage = (!player.getSheet().hasEvaded()) ? this.applyDamage(enemy, player) : 0;
        updateFightStatus();
        return damage;
    }

    /**
     * Updates the status of this fight, checking if it's been completed by the getPlayer, or if the getPlayer has
     * been defeated by the enemies
     */
    private void updateFightStatus() {
        if (!player.getSheet().isAlive()) this.setResult(FightResult.LOSE);
        else if (currentEnemies.isEmpty()) this.setResult(FightResult.WIN);
    }

    /**
     * This method applies the damage to the given character, based on the stats of the given
     * attacker and the given target
     * @param attacker the fighter that causes the damage
     * @param target the fighter that receives the damage
     */
    private int applyDamage(Fighter attacker, Fighter target) {
        if (attacker == null || target == null)
            throw new IllegalArgumentException("Invalid arguments");

        int damage = calculateDamage(attacker, target);
        target.getSheet().damage(damage);
        return damage;
    }

    /**
     * This method calculates the damage to apply to the given target, taking in count the stats of the attacker
     * and the stats of the target
     * @param attacker the fighter that causes the damage
     * @param target the fighter that receives the damage
     * @return the amount of damage to apply
     */
    private int calculateDamage(Fighter attacker, Fighter target) {
        return Math.max( // else calculate the damage, and apply it to the target fighter
                attacker.getSheet().getATK() + attacker.getWeaponAttack() - target.getSheet().getDF(),
                1);
    }

    @Override
    public int consumeItem(Fighter target, Consumable consumable) {
        if (target == null || consumable == null)
            throw new IllegalArgumentException("Invalid parameters");

        consumable.consume(target);
        player.getInventory().drop(new ItemStack(consumable, 1));
        if (!target.getSheet().isAlive() && target instanceof Enemy) {
            player.getMoneyCollector().cash(((Enemy) target).getEnemyType().getGoldForDefeat());
            this.currentEnemies.remove(target);
        }
        this.updateFightStatus();
        return consumable.getStatsModifier();
    }

    @Override
    public void equipItem(PlayerFighter player, Equipment equipment) {
        if (player == null || equipment == null || player != this.player)
            throw new IllegalArgumentException("Invalid parameters");

        player.equip(equipment);
    }

    @Override
    public void reset() {
        this.result = null;
        this.currentEnemies = defaultEnemies;
        for (Fighter enemy : currentEnemies) enemy.getSheet().reset();
        this.player.getSheet().reset();
    }

    /**
     * Checks if the given object is equal to this fight, basing on getPlayer and enemies
     * @param obj   the reference object with which to compare.
     * @return true if the two objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return player.equals(((BaseFight) obj).getPlayer()) && currentEnemies.equals(((BaseFight) obj).getCurrentEnemies());
    }

    /**
     * Returns the hash code of this fight
     * @return the hash code of this fight
     */
    @Override
    public int hashCode() { return Objects.hash(player, currentEnemies); }


    // GETTERS

    public PlayerFighter getPlayer() { return player; }

    @Override
    public List<EnemyFighter> getCurrentEnemies() { return currentEnemies; }

    public void setResult(FightResult result) { this.result = result; }
}
