package it.unicam.cs.mpgc.rpg126164.persistance.repositories;

import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.Consumable;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.Potion;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.BaseLevel;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.Level;
import it.unicam.cs.mpgc.rpg126164.persistance.HibernateUtil;
import it.unicam.cs.mpgc.rpg126164.persistance.LevelPrize;
import org.hibernate.Session;

import java.util.List;

/**
 * This class works as a level-prize repository, in order to recreate the prizes for each level from
 * the database, and also to query the database
 */
public class LevelPrizeRepository {

    /**
     * Finds a level-prize association, given the id
     * @param id its id
     * @return the level prize association
     */
    public LevelPrize findById(String id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(LevelPrize.class, id);
        }
    }

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

    /**
     * Finds a level-prize association, given the prize
     * @param prize the prize
     * @return the level prize association
     */
    public LevelPrize findByPrize(Consumable prize) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM LevelPrize WHERE prize = :prize_id", LevelPrize.class)
                    .setParameter("prize_id", prize)
                    .getSingleResult();
        }
    }

    /**
     * Finds all the entries in the database
     * @return the list of level-prize associations in the database
     */
    public List<LevelPrize> findAll() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM LevelPrize", LevelPrize.class).getResultList();
        }
    }
}
