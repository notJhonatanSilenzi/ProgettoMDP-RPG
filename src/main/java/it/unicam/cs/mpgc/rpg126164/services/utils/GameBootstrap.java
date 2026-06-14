package it.unicam.cs.mpgc.rpg126164.services.utils;

import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.SaveManager;
import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.SaveSlot;
import it.unicam.cs.mpgc.rpg126164.persistance.HibernateUtil;
import it.unicam.cs.mpgc.rpg126164.persistance.repositories.*;
import it.unicam.cs.mpgc.rpg126164.persistance.seeders.*;
import it.unicam.cs.mpgc.rpg126164.services.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * This class boots the game, and it initializes:
 * - the repositories with the first hibernate session
 * - the services
 * - the save manager
 * - the seed manager to seed the database with data from the seeders
 */
public class GameBootstrap {

    private final Session session;
    private final WeaponRepository weaponRepository;
    private final PotionRepository potionRepository;
    private final EnemyRepository enemyRepository;
    private final LevelRepository levelRepository;
    private final LevelEnemyRepository levelEnemyRepository;
    private final LevelPrizeRepository levelPrizeRepository;
    private final SaveManager saveManager;

    /**
     * Creates a game bootstrap
     */
    public GameBootstrap() {
        this.session = HibernateUtil.getSessionFactory().openSession(); // Create a session
        this.weaponRepository = new WeaponRepository();
        this.potionRepository = new PotionRepository();
        this.enemyRepository = new EnemyRepository();
        this.levelRepository = new LevelRepository();
        this.levelEnemyRepository = new LevelEnemyRepository();
        this.levelPrizeRepository = new LevelPrizeRepository();
        this.saveManager = new SaveSlot();
    }

    /**
     * Initializes the game service and seeds the database
     * @return the game service
     */
    public GameService initGamePlay() {

        runSeeders(session); // Seeds the database

        return new GameService(saveManager, potionRepository, weaponRepository);
    }

    /**
     * Seeds the database with all data, if needed
     * @param session the session to seed the database with
     */
    private void runSeeders(Session session) {
        try {
            Transaction tx = session.beginTransaction(); // Create a transaction

            // Seeds the database through a seeder manager and commits the transaction
            SeederManager seederManager = new SeederManager(List.of(
                    new WeaponSeeder(),
                    new PotionSeeder(),
                    new EnemySeeder(),
                    new LevelSeeder(),
                    new LevelEnemySeeder(),
                    new LevelPrizeSeeder()
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
        return new WorldService(weaponRepository, potionRepository, levelRepository, levelPrizeRepository);
    }

    /**
     * Initializes a market service for the market of this game
     * @return a market service for the market
     */
    public MarketService initMarket() {
        return new MarketService();
    }

    /**
     * Initializes a level service for the adventure mode of this game
     * @return a level service for the adventure mode
     */
    public LevelService initLevelManager() {
        return new LevelService(enemyRepository, levelEnemyRepository);
    }

    /**
     * Initializes a combat service for the fights during the levels of this game
     * @return a combat service for the level game play
     */
    public CombatService initCombat() { return new CombatService();}
}
