package it.unicam.cs.mpgc.rpg126164.persistance.repositories;

import it.unicam.cs.mpgc.rpg126164.domain.characters.Enemy;
import it.unicam.cs.mpgc.rpg126164.domain.characters.EnemyType;
import org.hibernate.Session;

import java.util.List;

/**
 * This class works as a repository for the "enemies" entity, and it contains methods to query the database
 * to rebuild objects, according to the data collected in the database.
 */
public class EnemyRepository {

    private final Session session;

    /**
     * Creates an enemies repository
     * @param session the session that is being used to query the database
     */
    public EnemyRepository(Session session) { this.session = session; }

    /**
     * Finds an enemy, given the name
     * @param name the name of the enemy
     * @return the complete enemy
     */
    public Enemy findByName(String name) {
        return session.createQuery("FROM Enemy WHERE name = :name", Enemy.class)
                .setParameter("name", name)
                .uniqueResult();
    }

    /**
     * Finds an enemy, given the id
     * @param id the id of the enemy
     * @return the complete enemy
     */
    public Enemy findById(String id) { return session.find(Enemy.class, id); }

    /**
     * Returns all the enemies of a certain type
     * @param type the type of enemy
     * @return the list of all the enemies of that type
     */
    public List<Enemy> findByType(EnemyType type) {
        return session.createQuery("FROM Enemy WHERE type = :type", Enemy.class)
                .setParameter("type", type)
                .list();
    }
}
