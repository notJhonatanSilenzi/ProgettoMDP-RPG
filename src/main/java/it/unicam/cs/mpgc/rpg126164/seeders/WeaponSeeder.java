package it.unicam.cs.mpgc.rpg126164.seeders;

import it.unicam.cs.mpgc.rpg126164.collectibles.equipment.Weapon;
import org.hibernate.Session;

/**
 * This class works as a data seeder for the database, adding data to the entity "weapons"
 */
public class WeaponSeeder implements Seeder {

    @Override
    public void seed(Session session) {

        session.persist(new Weapon("Iron Spear", "A simple spear, used by warriors", 1, 120, 4));
        session.persist(new Weapon("Iron Sword", "A simple sword, used by berserkers", 1, 150, 5));
        session.persist(new Weapon("Simple Spell", "A simple spell, used by clerics", 1, 140, 3));
        session.persist(new Weapon("Fire Spell", "A dangerous spell, used by sorcerers", 1, 220, 6));
    }
}
