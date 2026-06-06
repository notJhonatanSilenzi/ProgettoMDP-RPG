package it.unicam.cs.mpgc.rpg126164.persistance.repositories;

import it.unicam.cs.mpgc.rpg126164.domain.characters.Enemy;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.Level;
import it.unicam.cs.mpgc.rpg126164.persistance.HibernateUtil;
import it.unicam.cs.mpgc.rpg126164.persistance.LevelEnemy;
import org.hibernate.Session;

import java.util.List;

/**
 * This class works as a repository for the "level_enemy" entity, and it contains methods to query
 * the database, given the information
 */
public class LevelEnemyRepository {

    /**
     * Returns a level-enemy association, given the id
     * @param id the id of the association
     * @return the complete LevelEnemy association
     */
    public LevelEnemy findById(String id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(LevelEnemy.class, id);
        }
    }

    /**
     * Finds all the level-enemy association, given the level
     * @param level the level involved in the associations
     * @return the list of level-enemy associations
     */
    public List<LevelEnemy> findByLevel(Level level) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM LevelEnemy WHERE level = :level", LevelEnemy.class)
                    .setParameter("level", level)
                    .getResultList();
        }
    }

    /**
     * Finds all the level-enemy associations, given the enemy
     * @param enemy the enemy involved in the associations
     * @return the list of level-enemy associations
     */
    public List<LevelEnemy> findByEnemy(Enemy enemy) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM LevelEnemy WHERE enemy = :enemy", LevelEnemy.class)
                    .setParameter("enemy", enemy)
                    .getResultList();
        }
    }
}
