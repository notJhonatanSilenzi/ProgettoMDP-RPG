package it.unicam.cs.mpgc.rpg126164.persistance.repositories;

import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.BaseLevel;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.Level;
import it.unicam.cs.mpgc.rpg126164.persistance.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * This class works as a repository for the "levels" entity, and it contains methods to query the database,
 * given some information
 */
public class LevelRepository {

    /**
     * Finds a level, given its name
     * @param name the name of the level
     * @return the complete level
     */
    public BaseLevel findByName(String name) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM BaseLevel WHERE name = :name", BaseLevel.class)
                    .setParameter("name", name)
                    .uniqueResult();
        }
    }

    /**
     * Finds a level, given the id
     * @param id the id of the level
     * @return the complete level
     */
    public BaseLevel findById(String id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(BaseLevel.class, id);
        }
    }

    /**
     * Finds all the levels in the database
     * @return the list of levels in the database
     */
    public List<Level> findAll() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM BaseLevel", Level.class).list();
        }
    }
}
