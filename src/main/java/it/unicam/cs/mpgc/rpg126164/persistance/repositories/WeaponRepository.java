package it.unicam.cs.mpgc.rpg126164.persistance.repositories;

import it.unicam.cs.mpgc.rpg126164.domain.collectibles.equipment.Weapon;
import org.hibernate.Session;

import java.util.List;

/**
 * This class works as a repository for the "weapons" entity, and it contains methods to query the
 * SQLite database
 */
public class WeaponRepository {

    private final Session session;

    /**
     * Creates a weapon repository
     * @param session the session that is being used for the game
     */
    public WeaponRepository(Session session) { this.session = session; }

    /**
     * Finds the weapon, given the name
     * @param name the name of the weapon
     * @return the complete weapon
     */
    public Weapon findByName(String name) {
        return session.createQuery("FROM Weapon WHERE name = :name", Weapon.class)
                .setParameter("name", name)
                .uniqueResult();
    }

    /**
     * Finds the weapon, given the id
     * @param id the id of the weapon
     * @return the complete weapon
     */
    public Weapon findById(String id) { return session.find(Weapon.class, id); }

    /**
     * Finds all the weapons in the database
     * @return the list of all weapons in the database
     */
    public List<Weapon> findAll() { return session.createQuery("FROM Weapon", Weapon.class).list(); }
}
