package it.unicam.cs.mpgc.rpg126164.persistance.repositories;

import it.unicam.cs.mpgc.rpg126164.domain.collectibles.equipment.Weapon;
import it.unicam.cs.mpgc.rpg126164.persistance.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * This class works as a repository for the "weapons" entity, and it contains methods to query the
 * SQLite database
 */
public class WeaponRepository {

    /**
     * Finds the weapon, given the name
     * @param name the name of the weapon
     * @return the complete weapon
     */
    public Weapon findByName(String name) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Weapon WHERE name = :name", Weapon.class)
                    .setParameter("name", name)
                    .uniqueResult();
        }
    }

    /**
     * Finds all the weapons in the database
     * @return the list of all weapons in the database
     */
    public List<Weapon> findAll() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Weapon", Weapon.class).list();
        }
    }
}
