package it.unicam.cs.mpgc.rpg126164.persistance.repositories;

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
     * Finds all the levels in the database
     * @return the list of levels in the database
     */
    public List<Level> findAll() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM BaseLevel", Level.class).list();
        }
    }
}
