package it.unicam.cs.mpgc.rpg126164.services;

import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.SaveManager;
import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.SaveSlot;
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

    private final Session session;
    private final WeaponRepository weaponRepository;
    private final PotionRepository potionRepository;
    private final EnemyRepository enemyRepository;
    private final LevelRepository levelRepository;
    private final LevelEnemyRepository levelEnemyRepository;
    private final SaveManager saveManager;

    public GameBootstrap() {
        this.session = HibernateUtil.getSessionFactory().openSession(); // Create a session
        this.weaponRepository = new WeaponRepository(session);
        this.potionRepository = new PotionRepository(session);
        this.enemyRepository = new EnemyRepository(session);
        this.levelRepository = new LevelRepository(session);
        this.levelEnemyRepository = new LevelEnemyRepository(session);
        this.saveManager = new SaveSlot();
    }

    /**
     * Initializes the game service, especially the database
     * @return the game service
     */
    public GameService initGamePlay() {

        runSeeders(session); // Seeds the database

        return new GameService(saveManager);
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

    /**
     * Initializes a world service, to build the world game for the gameplay
     * @return a world service for this game
     */
    public WorldService initWorldGame() {
        return new WorldService(weaponRepository, potionRepository, levelRepository);
    }

    /**
     * Initializes a market service for the market of this game
     * @return a market service for the market
     */
    public MarketService initMarket() {
        return new MarketService();
    }
}
