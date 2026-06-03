package it.unicam.cs.mpgc.rpg126164.seeders;

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
     * Seeds all the data using the methods of the seeders
     * @param session the session that receives the data from the seeders
     */
    public void seedAll(Session session) {
        for (Seeder seeder : seeders) seeder.seed(session);
    }
}
