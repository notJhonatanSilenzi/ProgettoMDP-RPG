package it.unicam.cs.mpgc.rpg126164.persistance.repositories;

import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.Potion;
import it.unicam.cs.mpgc.rpg126164.persistance.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * This class works as a repository for the "potions" entity, and it contains methods to query the db,
 * according to the given information
 */
public class PotionRepository {

    /**
     * Finds a potion, given the name
     * @param name the name of the potion
     * @return the complete potion
     */
    public Potion findByName(String name) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Potion WHERE name = :name", Potion.class)
                    .setParameter("name", name)
                    .uniqueResult();
        }
    }

    /**
     * Returns all the potions seeded in the database
     * @return the list of all potions
     */
    public List<Potion> findAll() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Potion", Potion.class).getResultList();
        }
    }
}
