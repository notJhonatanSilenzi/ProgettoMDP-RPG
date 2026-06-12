package it.unicam.cs.mpgc.rpg126164.domain.gamemechanics;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayerFighter;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.combat.Fight;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.combat.FightResult;
import jakarta.persistence.*;

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
        if  (name == null || enemyCount <= 0 || name.isEmpty())
            throw new IllegalArgumentException("Invalid parameters");

        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.enemyCount = enemyCount;
        this.fight = null;
        this.prize = null;
        this.completed = false;
    }

    @Override
    public void enterLevel(Fight fight) { this.fight = fight; }

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
    @Override
    public ItemStack givePrizeToPlayer(PlayerFighter player) {
        if (player == null) throw new IllegalArgumentException("Invalid parameters");

        if (prize == null) return null;

        if (playerHasWon()) {
            player.getInventory().collect(prize);
            completed = true;
        }
        return prize;
    }

    @Override
    public void setPrize(ItemStack prize) { this.prize = prize; }

    @Override
    public boolean isCompleted() { return completed; }


    // GETTERS

    public String getId() { return id; }

    @Override
    public String getName() { return name; }

    public Fight getFight() { return fight; }
}
