package it.unicam.cs.mpgc.rpg126164.repositories;

import it.unicam.cs.mpgc.rpg126164.collectibles.potions.Potion;
import it.unicam.cs.mpgc.rpg126164.collectibles.potions.StatsType;
import org.hibernate.Session;

import java.util.List;

/**
 * This class works as a repository for the "potions" entity, and it contains methods to query the db,
 * according to the given information
 */
public class PotionRepository {

    private final Session session;

    /**
     * Creates a potion repository
     * @param session the session that is being used to query the db
     */
    public PotionRepository(Session session) { this.session = session; }

    /**
     * Finds a potion, given the name
     * @param name the name of the potion
     * @return the complete potion
     */
    public Potion findByName(String name) {
        return session.createQuery("FROM Potion WHERE name = :name", Potion.class)
                .setParameter("name", name)
                .uniqueResult();
    }

    /**
     * Finds a potion, given the id
     * @param id the id of the potion
     * @return the complete potion
     */
    public Potion findById(String id) { return session.find(Potion.class, id); }

    /**
     * Finds all the potion, given the stats type that they modify
     * @param type the type of stats that the potion modifies
     * @return the list of potions that modify the given stats type
     */
    public List<Potion> findByStatsType(StatsType type) {
        return session.createQuery("FROM Potion WHERE statsType = :type", Potion.class)
                .setParameter("type", type)
                .getResultList();
    }

    /**
     * Returns all the potions seeded in the database
     * @return the list of all potions
     */
    public List<Potion> findAll() {
        return session.createQuery("FROM Potion", Potion.class).getResultList();
    }
}
