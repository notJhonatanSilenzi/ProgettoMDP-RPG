package it.unicam.cs.mpgc.rpg126164.persistance.seeders;

import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.Potion;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.BaseLevel;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.LevelPrize;
import it.unicam.cs.mpgc.rpg126164.persistance.repositories.LevelRepository;
import it.unicam.cs.mpgc.rpg126164.persistance.repositories.PotionRepository;
import org.hibernate.Session;

/**
 * This class works as a seeder for the level-prize association, in order to associate a level to
 * its prize in the database
 */
public class LevelPrizeSeeder implements Seeder {

    private LevelRepository levelRepository;
    private PotionRepository potionRepository;

    @Override
    public void seed(Session session) {
        levelRepository = new LevelRepository(session);
        potionRepository = new PotionRepository(session);

        seedLevel1(session);
        seedLevel2(session);
        seedLevel3(session);
        seedLevel4(session);
    }

    /**
     * Seeds the data for the first level, associating it with its prize
     * @param session the session to seed data with
     */
    private void seedLevel1(Session session) {
        BaseLevel level1 = levelRepository.findByName("Level 1 - Waterfall");
        Potion potion1 = potionRepository.findByName("Attack Boost Potion I");

        session.persist(new LevelPrize(level1, potion1, 1));
    }

    /**
     * Seeds the data for the second level, associating it with its prize
     * @param session the session to seed data with
     */
    private void seedLevel2(Session session) {
        BaseLevel level2 = levelRepository.findByName("Level 2 - Mineshaft");
        Potion potion2 = potionRepository.findByName("Defense Boost Potion I");

        session.persist(new LevelPrize(level2, potion2, 2));
    }

    /**
     * Seeds the data for the third level, associating it with its prize
     * @param session the session to seed data with
     */
    private void seedLevel3(Session session) {
        BaseLevel level3 = levelRepository.findByName("Level 3 - Cave");
        Potion potion3 = potionRepository.findByName("Health Boost Potion III");

        session.persist(new LevelPrize(level3, potion3, 1));
    }

    /**
     * Seeds the data for the fourth level, associating it with its prize
     * @param session the session to seed data with
     */
    private void seedLevel4(Session session) {
        BaseLevel level4 = levelRepository.findByName("Level 4 - Forest");
        Potion potion4 = potionRepository.findByName("Defense Boost Potion II");

        session.persist(new LevelPrize(level4, potion4, 1));
    }
}
