package it.unicam.cs.mpgc.rpg126164.persistance.seeders;

import it.unicam.cs.mpgc.rpg126164.domain.characters.Enemy;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.BaseLevel;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.LevelEnemy;
import it.unicam.cs.mpgc.rpg126164.persistance.repositories.EnemyRepository;
import it.unicam.cs.mpgc.rpg126164.persistance.repositories.LevelRepository;
import org.hibernate.Session;

/**
 * This class works as a seeder for the "level_enemy" entity, and it persists all the associations
 * for the levels, in order to recreate the correct set of enemies in the levels
 */
public class LevelEnemySeeder implements Seeder {

    private EnemyRepository enemyRepository;
    private LevelRepository levelRepository;

    @Override
    public void seed(org.hibernate.Session session) {
        enemyRepository = new EnemyRepository(session);
        levelRepository = new LevelRepository(session);

        seedLevel1(session);
        seedLevel2(session);
        seedLevel3(session);
        seedLevel4(session);
        seedLevel5(session);
    }

    /**
     * Seeds all the info about level 1
     * @param session the session used to persist data
     */
    private void seedLevel1(Session session) {
        BaseLevel level1 = levelRepository.findByName("Level 1 - Waterfall");
        Enemy enemy1 = enemyRepository.findByName("Bandit");

        session.persist(new LevelEnemy(level1, enemy1, 1));
    }

    /**
     * Seeds all the info about level 2
     * @param session the session used to persist data
     */
    private void seedLevel2(Session session) {
        BaseLevel level2 = levelRepository.findByName("Level 2 - Mineshaft");
        Enemy enemy1 = enemyRepository.findByName("Orc Veteran");
        Enemy enemy2 = enemyRepository.findByName("Cultist");

        session.persist(new LevelEnemy(level2, enemy1, 1));
        session.persist(new LevelEnemy(level2, enemy2, 1));
    }

    /**
     * Seeds all the info about level 3
     * @param session the session used to persist data
     */
    private void seedLevel3(Session session) {
        BaseLevel level3 = levelRepository.findByName("Level 3 - Cave");
        Enemy enemy1 = enemyRepository.findByName("High Priest");

        session.persist(new LevelEnemy(level3, enemy1, 1));
    }

    /**
     * Seeds all the info about level 4
     * @param session the session used to persist data
     */
    private void seedLevel4(Session session) {
        BaseLevel level4 = levelRepository.findByName("Level 4 - Forest");
        Enemy enemy1 = enemyRepository.findByName("Militia Guard");
        Enemy enemy2 = enemyRepository.findByName("Assassin");
        Enemy enemy3 = enemyRepository.findByName("Apprentice Sorcerer");

        session.persist(new LevelEnemy(level4, enemy1, 1));
        session.persist(new LevelEnemy(level4, enemy2, 1));
        session.persist(new LevelEnemy(level4, enemy3, 1));
    }

    /**
     * Seeds all the info about level 5
     * @param session the session used to persist data
     */
    private void seedLevel5(Session session) {
        BaseLevel level5 = levelRepository.findByName("Level 5 - Mountain");
        Enemy boss = enemyRepository.findByName("Archmage");

        session.persist(new LevelEnemy(level5, boss, 1));
    }
}
