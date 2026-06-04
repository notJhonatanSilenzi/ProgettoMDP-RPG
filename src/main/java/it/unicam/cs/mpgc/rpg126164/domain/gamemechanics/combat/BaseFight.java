package it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.combat;

import it.unicam.cs.mpgc.rpg126164.domain.characters.Enemy;
import it.unicam.cs.mpgc.rpg126164.domain.characters.Fighter;
import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayableCharacter;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.Consumable;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.equipment.Equipment;

import java.util.Objects;
import java.util.Set;

/**
 * This class represents a base fight system to use in the game to process turns and verify the ending of the fight.
 * It implements the Fight interface, and it allows to use even multiple enemies.
 */
public class BaseFight implements Fight {

    private final PlayableCharacter player;
    private final Set<Fighter> enemies;
    private FightResult result;

    /**
     * Creates a base fight system between fighters
     * @param player the getPlayer character
     * @param enemies the enemies to defeat
     */
    public BaseFight(PlayableCharacter player, Set<Fighter> enemies) {
        if (player == null || enemies == null || enemies.isEmpty())
            throw new IllegalArgumentException("Invalid parameters");

        this.player = player;
        this.enemies = enemies;
        this.result = null;
    }

    @Override
    public void startFight() {
        if (result != null) throw new IllegalStateException("Attempt to start an already started fight");

        this.result = FightResult.ON_GOING;
    }

    @Override
    public void processTurn(GameAction gameAction) {
        if (gameAction == null) throw new IllegalArgumentException("Invalid argument");
        if (result == null) throw new RuntimeException("Attempt to process a turn in a not started fight");
        if (result != FightResult.ON_GOING) throw new RuntimeException("Attempt to process a turn in an ended fight");

        gameAction.execute(this);
        updateFightStatus();
    }

    /**
     * Updates the status of this fight, checking if it's been completed by the getPlayer, or if the getPlayer has
     * been defeated by the enemies
     */
    private void updateFightStatus() {
        if (!player.getSheet().isAlive()) this.setResult(FightResult.LOSE);
        else if (enemies.stream().noneMatch(e -> e.getSheet().isAlive()))
            this.setResult(FightResult.WIN);
    }

    @Override
    public FightResult getFinalResult() { return this.result; }

    @Override
    public void attack(PlayableCharacter attacker, Fighter target) {
        if (attacker == null || target == null || attacker != this.player || !enemies.contains(target))
            throw new IllegalArgumentException("Invalid parameters");

        // If the defender evaded, break out. Else the target gets damage
        if (target.getSheet().hasEvaded()) return; // TODO - forse in futuro da adattare
        else applyDamage(attacker, target);

        // If the target is dead and is an enemy, I remove it from the enemies list and give money to the getPlayer
        if (!target.getSheet().isAlive() && target instanceof Enemy enemy) {
            enemies.remove(target);
            attacker.getMoneyCollector().cash(enemy.getEnemyType().getGoldForDefeat());
        } else this.applyDamage(target, attacker);
    }

    /**
     * This method applies the damage to the given character, based on the stats of the given
     * attacker and the given target
     * @param attacker the fighter that causes the damage
     * @param target the fighter that receives the damage
     */
    private void applyDamage(Fighter attacker, Fighter target) {
        if (attacker == null || target == null)
            throw new IllegalArgumentException("Invalid arguments");

        int damage = calculateDamage(attacker, target);
        target.getSheet().damage(damage);
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
    public void consumeItem(Fighter target, Consumable consumable) {
        if (target == null || consumable == null)
            throw new IllegalArgumentException("Invalid parameters");

        consumable.consume(target);
    }

    @Override
    public void equipItem(PlayableCharacter player, Equipment equipment) {
        if (player == null || equipment == null || player != this.player)
            throw new IllegalArgumentException("Invalid parameters");

        player.equip(equipment);
    }

    @Override
    public void reset() {
        this.result = null;
        for (Fighter enemy : this.enemies) enemy.getSheet().reset();
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
        return player.equals(((BaseFight) obj).getPlayer()) && enemies.equals(((BaseFight) obj).getEnemies());
    }

    /**
     * Returns the hash code of this fight
     * @return the hash code of this fight
     */
    @Override
    public int hashCode() { return Objects.hash(player, enemies); }


    // GETTERS

    public Fighter getPlayer() { return player; }

    public Set<Fighter> getEnemies() { return enemies; }

    public FightResult getResult() { return result; }

    public void setResult(FightResult result) {
        if (result == null) throw new IllegalArgumentException("Invalid argument");
        if (result != FightResult.ON_GOING) throw new IllegalArgumentException("Attempt to update an ended fight");

        this.result = result;
    }
}
