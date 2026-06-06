package it.unicam.cs.mpgc.rpg126164.persistance.repositories;

import it.unicam.cs.mpgc.rpg126164.domain.characters.Enemy;
import it.unicam.cs.mpgc.rpg126164.domain.characters.EnemyType;
import it.unicam.cs.mpgc.rpg126164.persistance.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * This class works as a repository for the "enemies" entity, and it contains methods to query the database
 * to rebuild objects, according to the data collected in the database.
 */
public class EnemyRepository {

    /**
     * Finds an enemy, given the name
     * @param name the name of the enemy
     * @return the complete enemy
     */
    public Enemy findByName(String name) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Enemy WHERE name = :name", Enemy.class)
                    .setParameter("name", name)
                    .uniqueResult();
        }
    }

    /**
     * Finds an enemy, given the id
     * @param id the id of the enemy
     * @return the complete enemy
     */
    public Enemy findById(String id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Enemy.class, id);
        }
    }

    /**
     * Returns all the enemies of a certain type
     * @param type the type of enemy
     * @return the list of all the enemies of that type
     */
    public List<Enemy> findByType(EnemyType type) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Enemy WHERE type = :type", Enemy.class)
                    .setParameter("type", type)
                    .list();
        }
    }
}
