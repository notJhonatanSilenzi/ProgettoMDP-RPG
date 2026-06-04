package it.unicam.cs.mpgc.rpg126164.domain.gamemechanics;

import it.unicam.cs.mpgc.rpg126164.domain.characters.Fighter;
import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayableCharacter;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.combat.BaseFight;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.combat.Fight;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.combat.FightResult;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.combat.GameAction;
import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

/**
 * This class represents a generic level in the world game. It contains a reference to the fight to complete in order
 * to move onto the next level, and also a price and a flag to assert the level is completed.
 */
@Entity
@Table(name = "levels")
public class BaseLevel implements Level {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "enemy_count", nullable = false)
    private int enemyCount;

    @Transient
    private Fight fight;

    @Transient
    private ItemStack prize;

    @Transient
    private boolean completed;

    public BaseLevel() {}

    /**
     * Creates a basic level in the world game
     * @param name the name of the level
     * @param enemyCount the number of enemies that the getPlayer must defeat to complete the level
     */
    public BaseLevel(String name, int enemyCount) {
        if  (name == null || enemyCount <= 0 || name.isEmpty()) throw new IllegalArgumentException("Invalid parameters");

        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.enemyCount = enemyCount;
        this.fight = null;
        this.prize = null;
        this.completed = false;
    }

    @Override
    public void startLevel(PlayableCharacter player, Set<Fighter> enemies, ItemStack prize) {
        if (player == null || enemies == null || enemies.isEmpty())
            throw new IllegalArgumentException("Invalid parameters");

        if (enemies.size() != enemyCount) throw new IllegalArgumentException("Invalid number of enemies");

        if (fight == null) {
            this.fight = new BaseFight(player, enemies);
            this.prize = prize;
        } else fight.reset();

        fight.startFight();
    }

    @Override
    public void processTurn(GameAction gameAction) {
        if (gameAction == null) throw new IllegalArgumentException("Invalid arguments");

        fight.processTurn(gameAction);
        checkLevelStatus(gameAction);
    }

    /**
     * Checks if the fight has ended. In particular:
     * - it gives the price to the getPlayer, if they won the fight, and signs the level as completed
     * - it resets the level if the getPlayer lost the fight
     * - otherwise it doesn't do anything
     * @param gameAction the game action that the getPlayer has done in this turn
     */
    private void checkLevelStatus(GameAction gameAction) {
        if (playerHasWon()) {
            givePrizeToPlayer(gameAction.getPlayer());
            completed = true;
        }
        else if (playerHasLost()) this.reset();
    }

    @Override
    public boolean playerHasWon() { return fight.getFinalResult() == FightResult.WIN; }

    @Override
    public boolean playerHasLost() { return fight.getFinalResult() == FightResult.LOSE; }

    @Override
    public void reset() {
        fight.reset();
        completed = false;
    }

    /**
     * Gives a price to the getPlayer, if they completed the level by winning the fight
     * @param player the getPlayer that receives the price
     */
    private void givePrizeToPlayer(PlayableCharacter player) {
        if (player == null)
            throw new IllegalArgumentException("Invalid parameters");

        if (playerHasWon()) player.collectItem(prize);
    }


    // GETTERS

    public String getId() { return id; }

    public String getName() { return name; }

    public int getEnemyCount() { return enemyCount; }

    public Fight getFight() { return fight; }

    public ItemStack getPrize() { return prize; }

    public boolean isCompleted() { return completed; }
}
