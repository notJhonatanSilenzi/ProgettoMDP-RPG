package it.unicam.cs.mpgc.rpg126164.persistance.seeders;

import it.unicam.cs.mpgc.rpg126164.domain.characters.Enemy;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.BaseLevel;
import it.unicam.cs.mpgc.rpg126164.persistance.LevelEnemy;
import it.unicam.cs.mpgc.rpg126164.persistance.repositories.EnemyRepository;
import it.unicam.cs.mpgc.rpg126164.persistance.repositories.LevelRepository;
import org.hibernate.Session;

/**
 * This class works as a seeder for the "level_enemy" entity, and it persists all the associations
 * for the levels, in order to recreate the correct set of enemies in the levels
 */
public class LevelEnemySeeder implements Seeder {

    @Override
    public void seed(Session session) {
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
        BaseLevel level1 = getLevel(session, "Level 1 - Waterfall");
        Enemy enemy1 = getEnemy(session, "Bandit");

        session.persist(new LevelEnemy(level1, enemy1, 1));
        session.flush();
    }

    /**
     * Seeds all the info about level 2
     * @param session the session used to persist data
     */
    private void seedLevel2(Session session) {
        BaseLevel level2 = getLevel(session, "Level 2 - Mineshaft");
        Enemy enemy1 = getEnemy(session, "Orc Veteran");
        Enemy enemy2 = getEnemy(session, "Cultist");

        session.persist(new LevelEnemy(level2, enemy1, 1));
        session.persist(new LevelEnemy(level2, enemy2, 1));
        session.flush();
    }

    /**
     * Seeds all the info about level 3
     * @param session the session used to persist data
     */
    private void seedLevel3(Session session) {
        BaseLevel level3 = getLevel(session, "Level 3 - Cave");
        Enemy enemy1 = getEnemy(session, "High Priest");

        session.persist(new LevelEnemy(level3, enemy1, 1));
        session.flush();
    }

    /**
     * Seeds all the info about level 4
     * @param session the session used to persist data
     */
    private void seedLevel4(Session session) {
        BaseLevel level4 = getLevel(session, "Level 4 - Forest");
        Enemy enemy1 = getEnemy(session, "Militia Guard");
        Enemy enemy2 = getEnemy(session, "Assassin");
        Enemy enemy3 = getEnemy(session, "Apprentice Sorcerer");

        session.persist(new LevelEnemy(level4, enemy1, 1));
        session.persist(new LevelEnemy(level4, enemy2, 1));
        session.persist(new LevelEnemy(level4, enemy3, 1));
        session.flush();
    }

    /**
     * Seeds all the info about level 5
     * @param session the session used to persist data
     */
    private void seedLevel5(Session session) {
        BaseLevel level5 = getLevel(session, "Level 5 - Mountain");
        Enemy boss = getEnemy(session, "Archmage");

        session.persist(new LevelEnemy(level5, boss, 1));
        session.flush();
    }

    /**
     * Queries the level, given the name
     * @param session the session to query
     * @param name the name
     * @return the complete level
     */
    private BaseLevel getLevel(Session session, String name) {
        return session.createQuery("FROM BaseLevel WHERE name = :name", BaseLevel.class)
                .setParameter("name", name)
                .uniqueResult();
    }

    /**
     * Queries the enemy, given the name
     * @param session the session to query
     * @param name the name
     * @return the complete enemy
     */
    private Enemy getEnemy(Session session, String name) {
        return session.createQuery("FROM Enemy WHERE name = :name", Enemy.class)
                .setParameter("name", name)
                .uniqueResult();
    }
}
