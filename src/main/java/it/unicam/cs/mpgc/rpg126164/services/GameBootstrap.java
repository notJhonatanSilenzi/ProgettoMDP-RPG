package it.unicam.cs.mpgc.rpg126164.services;

import it.unicam.cs.mpgc.rpg126164.persistance.HibernateUtil;
import it.unicam.cs.mpgc.rpg126164.persistance.repositories.*;
import it.unicam.cs.mpgc.rpg126164.persistance.seeders.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * This class initializes the main game service for this game
 */
public class GameBootstrap {

    /**
     * Initializes the game service, especially the database
     * @return the game service
     */
    public GameService init() {
        Session session = HibernateUtil.getSessionFactory().openSession(); // Create a session

        runSeeders(session); // Seeds the database

        // Initialize the repositories and the service
        WeaponRepository weaponRepository = new WeaponRepository(session);
        PotionRepository potionRepository = new PotionRepository(session);
        EnemyRepository enemyRepository = new EnemyRepository(session);
        LevelRepository levelRepository = new LevelRepository(session);
        LevelEnemyRepository levelEnemyRepository = new LevelEnemyRepository(session);

        return new GameService(weaponRepository, potionRepository, enemyRepository, levelRepository, levelEnemyRepository);
    }

    /**
     * Seeds the database with all data, if needed
     * @param session the session to seed the database with
     */
    private void runSeeders(Session session) {
        try (session; session) {
            Transaction tx = session.beginTransaction(); // Create a transaction

            // Seeds the database through a seeder manager and commits the transaction
            SeederManager seederManager = new SeederManager(List.of(
                    new WeaponSeeder(),
                    new PotionSeeder(),
                    new EnemySeeder(),
                    new LevelSeeder(),
                    new LevelEnemySeeder()
            ));
            seederManager.seedIfNecessary(session);
            tx.commit();

        } catch (Exception ex) {
            throw new RuntimeException("Failed to run seeders", ex);
        }
    }
}
