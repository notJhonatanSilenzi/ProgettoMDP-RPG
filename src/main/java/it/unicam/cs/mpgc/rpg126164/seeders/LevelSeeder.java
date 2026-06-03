package it.unicam.cs.mpgc.rpg126164.seeders;

import it.unicam.cs.mpgc.rpg126164.gamemechanics.BaseLevel;
import org.hibernate.Session;

/**
 * This class works as a data seeder for the database, adding data for the "levels" entity
 */
public class LevelSeeder implements Seeder {

    @Override
    public void seed(Session session) {
        session.persist(new BaseLevel("Level 1", 1));
        session.persist(new BaseLevel("Level 2", 2));
        session.persist(new BaseLevel("Level 3", 1));
        session.persist(new BaseLevel("Level 4", 2));
        session.persist(new BaseLevel("Level 5", 1));
    }
}
