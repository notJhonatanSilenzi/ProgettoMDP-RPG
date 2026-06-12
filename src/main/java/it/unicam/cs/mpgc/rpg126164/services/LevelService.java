package it.unicam.cs.mpgc.rpg126164.services;

import it.unicam.cs.mpgc.rpg126164.domain.characters.*;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.Level;
import it.unicam.cs.mpgc.rpg126164.persistance.LevelEnemy;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.combat.BaseFight;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.combat.Fight;
import it.unicam.cs.mpgc.rpg126164.domain.world.gameplay.LevelManager;
import it.unicam.cs.mpgc.rpg126164.persistance.repositories.EnemyRepository;
import it.unicam.cs.mpgc.rpg126164.persistance.repositories.LevelEnemyRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * This class works as a service for the adventure mode of this game, managing movements of the player
 * between levels and the building of the fight
 */
public class LevelService {

    private final EnemyRepository enemyRepository;
    private final LevelEnemyRepository levelEnemyRepository;

    /**
     * Creates a level service
     * @param enemyRepository the enemies repository
     * @param levelEnemyRepository the level-enemy association repository
     */
    public LevelService(EnemyRepository enemyRepository, LevelEnemyRepository levelEnemyRepository) {
        if (enemyRepository == null || levelEnemyRepository == null)
            throw new IllegalArgumentException("Repositories cannot be null");

        this.enemyRepository = enemyRepository;
        this.levelEnemyRepository = levelEnemyRepository;
    }

    /**
     * Enters a level, so it builds the fight for the level according to the database
     * @param player the player's character
     * @param adventure the adventure game mode
     * @return the fight for the current level
     */
    public Fight enterLevel(PlayerFighter player, LevelManager adventure) {
        Level currentLevel = adventure.getCurrentLevel();
        List<LevelEnemy> entries = levelEnemyRepository.findByLevel(currentLevel);
        List<EnemyFighter> enemies = new ArrayList<>();
        for (LevelEnemy entry : entries)
            enemies.add(enemyRepository.findById(entry.getEnemy().getId()));
        Fight fight = new BaseFight(player, enemies);
        currentLevel.enterLevel(fight);
        return fight;
    }

    /**
     * Shifts the current level to the next one, if the previous one has been completed
     * @param adventure the adventure mode of the game
     */
    public void moveToNextLevel(LevelManager adventure) {
        adventure.nextLevel();
    }

    /**
     * Gives the prize of this level to the player
     *
     * @param player    the player's character
     * @param adventure the adventure mode of this game
     */
    public ItemStack playerReceivesPrice(PlayerFighter player, LevelManager adventure) {
        return adventure.getCurrentLevel().givePrizeToPlayer(player);
    }
}
