package it.unicam.cs.mpgc.rpg126164.persistance.repositories;

import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.Level;
import it.unicam.cs.mpgc.rpg126164.persistance.HibernateUtil;
import it.unicam.cs.mpgc.rpg126164.persistance.LevelPrize;
import org.hibernate.Session;

/**
 * This class works as a level-prize repository, in order to recreate the prizes for each level from
 * the database, and also to query the database
 */
public class LevelPrizeRepository {

    /**
     * Finds a level-prize association, given the level
     * @param level the level
     * @return the level prize association
     */
    public LevelPrize findByLevel(Level level) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM LevelPrize lp WHERE lp.level.id = :id", LevelPrize.class)
                    .setParameter("id", level.getId())
                    .uniqueResult();
        }
    }
}
