package it.unicam.cs.mpgc.rpg126164.persistance.seeders;

import org.hibernate.Session;

/**
 * This interface represents a generic seeder for this project, in order to add data to the database
 */
public interface Seeder {

    /**
     * Adds persistence data to the given session
     * @param session the session that receives the data to persist
     */
    void seed(Session session);
}
