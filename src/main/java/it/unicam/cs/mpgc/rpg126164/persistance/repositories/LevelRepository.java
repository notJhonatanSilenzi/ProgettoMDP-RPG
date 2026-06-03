package it.unicam.cs.mpgc.rpg126164.persistance.repositories;

import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.BaseLevel;
import org.hibernate.Session;

/**
 * This class works as a repository for the "levels" entity, and it contains methods to query the database,
 * given some information
 */
public class LevelRepository {

    private final Session session;

    /**
     * Creates a level repository
     * @param session the session used to query the database
     */
    public LevelRepository(Session session) { this.session = session; }

    /**
     * Finds a level, given its name
     * @param name the name of the level
     * @return the complete level
     */
    public BaseLevel findByName(String name) {
        return session.createQuery("FROM BaseLevel WHERE name = :name", BaseLevel.class)
                .setParameter("name", name)
                .uniqueResult();
    }

    /**
     * Finds a level, given the id
     * @param id the id of the level
     * @return the complete level
     */
    public BaseLevel findById(String id) { return session.find(BaseLevel.class, id); }
}
