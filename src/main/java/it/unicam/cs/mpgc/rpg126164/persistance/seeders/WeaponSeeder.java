package it.unicam.cs.mpgc.rpg126164.persistance.seeders;

import it.unicam.cs.mpgc.rpg126164.domain.collectibles.equipment.Weapon;
import org.hibernate.Session;

/**
 * This class works as a data seeder for the database, adding data to the entity "weapons"
 */
public class WeaponSeeder implements Seeder {

    @Override
    public void seed(Session session) {
        // Normal weapons
        session.persist(new Weapon("Iron Spear", "A simple spear, used by warriors", 1, 120, 4));
        session.persist(new Weapon("Iron Sword", "A simple sword, used by berserkers", 1, 150, 5));
        session.persist(new Weapon("Simple Spell", "A simple spell, used by clerics", 1, 140, 3));
        session.persist(new Weapon("Fire Spell", "A dangerous spell, used by sorcerers", 1, 220, 6));
        // Medium weapons
        session.persist(new Weapon("Heavy Spear", "A heavy spear, used by elite warriors", 1, 250, 8));
        session.persist(new Weapon("Iron Axe", "a heavy axe, used by berserkers", 1, 300, 10));
        // Powerful weapons
        session.persist(new Weapon("Light Spell", "A powerful spell, used by clerics", 1, 280, 7));
        session.persist(new Weapon("Lightning Spell", "A powerful spell, used by sorcerers", 1, 350, 12));
    }
}
