package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.Consumable;
import it.unicam.cs.mpgc.rpg126164.abstractions.Fight;
import it.unicam.cs.mpgc.rpg126164.abstractions.Fighter;
import it.unicam.cs.mpgc.rpg126164.abstractions.GameAction;

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
     * @param player the player character
     * @param enemies the enemies to defeat
     */
    public BaseFight(PlayableCharacter player, Set<Fighter> enemies) {
        if (player == null || enemies == null || enemies.isEmpty())
            throw new IllegalArgumentException("Invalid parameters");

        this.player = player;
        this.enemies = enemies;
        this.result = FightResult.ON_GOING;
    }

    @Override
    public void processTurn(GameAction gameAction) {
        if (gameAction == null) throw new IllegalArgumentException("Invalid argument");

        gameAction.execute(this);
        getFinalResult();
    }

    @Override
    public void getFinalResult() {
        if (result != FightResult.ON_GOING) return;

        if (!player.getSheet().isAlive()) this.setResult(FightResult.LOSE);
        else if (enemies.stream().noneMatch(e -> e.getSheet().isAlive()))
            this.setResult(FightResult.WIN);
    }

    @Override
    public void attack(Fighter attacker, Fighter target) {
        if (attacker == null || target == null)
            throw new IllegalArgumentException("Invalid parameters");

        // If the defender evaded, break out
        if (target.getSheet().hasEvaded()) return; // TODO - forse in futuro da adattare

        int damage = Math.max( // else calculate the damage, and apply it to the target fighter
                attacker.getSheet().getATK() + attacker.getEquipment().useEquipment() - target.getSheet().getDF(),
                1);
        target.getSheet().damage(damage);
        // If the target is dead and is an enemy, i remove it from the enemies list
        if (enemies.contains(target) && !target.getSheet().isAlive()) enemies.remove(target);
    }

    @Override
    public void consumeItem(Fighter target, Consumable consumable) {
        if (target == null || consumable == null)
            throw new IllegalArgumentException("Invalid parameters");

        consumable.consume(target);
    }


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
