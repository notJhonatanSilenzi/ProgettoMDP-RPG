package it.unicam.cs.mpgc.rpg126164.persistance.seeders;

import org.hibernate.Session;

import java.util.List;

/**
 * This class works as a manager for all the seeders, allowing to seed all the data with a single method call.
 */
public class SeederManager {

    private final List<Seeder> seeders;

    /**
     * Creates a seeder manager
     * @param seeders the seeders to handle
     */
    public SeederManager(List<Seeder> seeders) {
        this.seeders = seeders;
    }

    /**
     * Checks if the session needs to be seeded with data, and if so it seeds the data in the
     * database
     * @param session the session to be seeded
     */
    public void seedIfNecessary(Session session) {
        long count = session.createQuery("SELECT COUNT(e) FROM Enemy e", Long.class)
                .getSingleResult();

        if (count == 0) seedAll(session);
    }

    /**
     * Seeds all the data using the methods of the seeders
     * @param session the session that receives the data from the seeders
     */
    private void seedAll(Session session) {
        for (Seeder seeder : seeders) seeder.seed(session);
    }
}
