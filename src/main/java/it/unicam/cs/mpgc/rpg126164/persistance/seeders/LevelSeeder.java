package it.unicam.cs.mpgc.rpg126164.persistance.seeders;

import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.BaseLevel;
import org.hibernate.Session;

/**
 * This class works as a data seeder for the database, adding data for the "levels" entity
 */
public class LevelSeeder implements Seeder {

    @Override
    public void seed(Session session) {
        session.persist(new BaseLevel("Level 1 - Old Ruins"));
        session.persist(new BaseLevel("Level 2 - Dry Desert"));
        session.persist(new BaseLevel("Level 3 - Lava Cave"));
        session.persist(new BaseLevel("Level 4 - Mystic Forest"));
        session.persist(new BaseLevel("Level 5 - Snow Mountain"));
        session.flush();
    }
}
