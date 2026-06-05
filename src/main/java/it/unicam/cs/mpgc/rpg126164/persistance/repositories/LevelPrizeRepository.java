package it.unicam.cs.mpgc.rpg126164.persistance.repositories;

import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.Consumable;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.Level;
import it.unicam.cs.mpgc.rpg126164.persistance.LevelPrize;
import org.hibernate.Session;

import java.util.List;

/**
 * This class works as a level-prize repository, in order to recreate the prizes for each level from
 * the database, and also to query the database
 */
public class LevelPrizeRepository {

    private final Session session;

    /**
     * Creates a level-prize associations repository
     * @param session the session to query the database with
     */
    public LevelPrizeRepository(Session session) { this.session = session; }

    /**
     * Finds a level-prize association, given the id
     * @param id its id
     * @return the level prize association
     */
    public LevelPrize findById(String id) { return session.find(LevelPrize.class, id); }

    /**
     * Finds a level-prize association, given the level
     * @param level the level
     * @return the level prize association
     */
    public LevelPrize findByLevel(Level level) {
        return session.createQuery("FROM LevelPrize WHERE level.id = :level", LevelPrize.class)
                .setParameter("level", level)
                .getSingleResult();
    }

    /**
     * Finds a level-prize association, given the prize
     * @param prize the prize
     * @return the level prize association
     */
    public LevelPrize findByPrize(Consumable prize) {
        return session.createQuery("FROM LevelPrize WHERE prize.id = :prize", LevelPrize.class)
                .setParameter("prize", prize)
                .getSingleResult();
    }

    /**
     * Finds all the entries in the database
     * @return the list of level-prize associations in the database
     */
    public List<LevelPrize> findAll() {
        return session.createQuery("FROM LevelPrize", LevelPrize.class).getResultList();
    }
}
