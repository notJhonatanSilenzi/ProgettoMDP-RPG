package it.unicam.cs.mpgc.rpg126164.persistance.repositories;

import it.unicam.cs.mpgc.rpg126164.domain.characters.Enemy;
import it.unicam.cs.mpgc.rpg126164.domain.characters.EnemyFighter;
import it.unicam.cs.mpgc.rpg126164.persistance.HibernateUtil;
import org.hibernate.Session;

/**
 * This class works as a repository for the "enemies" entity, and it contains methods to query the database
 * to rebuild objects, according to the data collected in the database.
 */
public class EnemyRepository {

    /**
     * Finds an enemy, given the id
     * @param id the id of the enemy
     * @return the complete enemy
     */
    public EnemyFighter findById(String id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Enemy.class, id);
        }
    }
}
