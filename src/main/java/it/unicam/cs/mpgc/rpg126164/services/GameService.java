package it.unicam.cs.mpgc.rpg126164.services;

import it.unicam.cs.mpgc.rpg126164.persistance.repositories.*;

public class GameService {

    private final WeaponRepository weaponRepository;
    private final PotionRepository potionRepository;
    private final EnemyRepository enemyRepository;
    private final LevelRepository levelRepository;
    private final LevelEnemyRepository levelEnemyRepository1;

    public GameService(WeaponRepository weaponRepository, PotionRepository potionRepository,
                       EnemyRepository enemyRepository, LevelRepository levelRepository,
                       LevelEnemyRepository levelEnemyRepository) {

        if (weaponRepository == null || potionRepository == null || enemyRepository == null ||
                levelRepository == null || levelEnemyRepository == null)
            throw new IllegalArgumentException("Repositories cannot be null");

        this.weaponRepository = weaponRepository;
        this.potionRepository = potionRepository;
        this.enemyRepository = enemyRepository;
        this.levelRepository = levelRepository;
        this.levelEnemyRepository1 = levelEnemyRepository;
    }
}

