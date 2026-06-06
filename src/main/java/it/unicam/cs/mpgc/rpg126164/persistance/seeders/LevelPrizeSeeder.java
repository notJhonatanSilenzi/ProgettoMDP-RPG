package it.unicam.cs.mpgc.rpg126164.persistance.seeders;

import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.Potion;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.BaseLevel;
import it.unicam.cs.mpgc.rpg126164.persistance.LevelPrize;
import org.hibernate.Session;

/**
 * This class works as a seeder for the level-prize association, in order to associate a level to
 * its prize in the database
 */
public class LevelPrizeSeeder implements Seeder {

    @Override
    public void seed(Session session) {
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
        BaseLevel level1 = this.getLevel(session, "Level 1 - Waterfall");
        Potion potion1 = this.getPotion(session, "Attack Boost Potion I");

        session.persist(new LevelPrize(level1, potion1, 1));
        session.flush();
    }

    /**
     * Seeds the data for the second level, associating it with its prize
     * @param session the session to seed data with
     */
    private void seedLevel2(Session session) {
        BaseLevel level2 = this.getLevel(session, "Level 2 - Mineshaft");
        Potion potion2 = this.getPotion(session, "Defense Boost Potion I");

        System.out.println("LOOK HERE: " + level2 + " - " + potion2);

        session.persist(new LevelPrize(level2, potion2, 2));
        session.flush();
    }

    /**
     * Seeds the data for the third level, associating it with its prize
     * @param session the session to seed data with
     */
    private void seedLevel3(Session session) {
        BaseLevel level3 = this.getLevel(session, "Level 3 - Cave");
        Potion potion3 = this.getPotion(session, "Health Potion III");

        session.persist(new LevelPrize(level3, potion3, 1));
        session.flush();
    }

    /**
     * Seeds the data for the fourth level, associating it with its prize
     * @param session the session to seed data with
     */
    private void seedLevel4(Session session) {
        BaseLevel level4 = this.getLevel(session, "Level 4 - Forest");
        Potion potion4 = this.getPotion(session, "Defense Boost Potion II");

        session.persist(new LevelPrize(level4, potion4, 1));
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
     * Creates a potion, querying the session with a given name
     * @param session the session to query
     * @param name the potion name
     * @return the complete potion
     */
    private Potion getPotion(Session session, String name) {
        return session.createQuery("FROM Potion WHERE name = :name", Potion.class)
                .setParameter("name", name)
                .uniqueResult();
    }
}
