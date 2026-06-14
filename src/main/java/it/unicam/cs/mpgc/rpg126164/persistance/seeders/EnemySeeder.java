package it.unicam.cs.mpgc.rpg126164.persistance.seeders;

import it.unicam.cs.mpgc.rpg126164.domain.characters.Enemy;
import it.unicam.cs.mpgc.rpg126164.domain.characters.EnemyType;
import it.unicam.cs.mpgc.rpg126164.domain.characters.stats.Archetype;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.equipment.Weapon;
import org.hibernate.Session;

/**
 * This class works as a data seeder for the database, adding data for the entity "enemies"
 */
public class EnemySeeder implements Seeder {

    @Override
    public void seed(Session session) {
        seedNormalEnemies(session);
        seedMediumEnemies(session);
        seedLargeAndBoss(session);
        session.flush();
    }

    /**
     * Seeds normal enemies
     * @param session the current hibernate session
     */
    private void seedNormalEnemies(Session session) {
        Weapon ironSpear = getWeapon(session, "Iron Spear");
        Weapon ironSword = getWeapon(session, "Iron Sword");
        Weapon simpleSpell = getWeapon(session, "Simple Spell");
        Weapon fireSpell = getWeapon(session, "Fire Spell");

        session.persist(new Enemy("Bandit", "a thief lurking in the woods", ironSword, Archetype.BERSERKER, EnemyType.NORMAL));
        session.persist(new Enemy("Militia Guard", "a poorly trained warrior", ironSpear, Archetype.WARRIOR, EnemyType.NORMAL));
        session.persist(new Enemy("Cultist", "a fanatic cleric", simpleSpell, Archetype.CLERIC, EnemyType.NORMAL));
        session.persist(new Enemy("Apprentice Sorcerer", "a normal sorcerer", fireSpell, Archetype.SORCERER, EnemyType.NORMAL));
    }

    /**
     * Seeds all the medium enemies
     * @param session the current hibernate session
     */
    private void seedMediumEnemies(Session session) {
        Weapon heavySpear = getWeapon(session, "Heavy Spear");
        Weapon ironAxe = getWeapon(session, "Iron Axe");

        session.persist(new Enemy("Orc Veteran", "a strong orc", heavySpear, Archetype.WARRIOR, EnemyType.MEDIUM));
        session.persist(new Enemy("Assassin", "A dangerous thief", ironAxe, Archetype.BERSERKER, EnemyType.MEDIUM));
    }

    /**
     * Seeds the large enemy and the boss
     * @param session the current hibernate session
     */
    private void seedLargeAndBoss(Session session) {
        Weapon lightSpell = getWeapon(session, "Light Spell");
        Weapon lightning = getWeapon(session, "Lightning Spell");

        session.persist(new Enemy("High Priest", "A powerful cleric", lightSpell, Archetype.CLERIC, EnemyType.LARGE));
        session.persist(new Enemy("Archmage", "A powerful sorcerer", lightning, Archetype.SORCERER, EnemyType.BOSS));
    }

    /**
     * Queries the weapon, given the name
     * @param session the current hibernate session
     * @param name the name
     * @return the complete weapon
     */
    private Weapon getWeapon(Session session, String name) {
        return session.createQuery("FROM Weapon WHERE name = :name", Weapon.class)
                .setParameter("name", name)
                .uniqueResult();
    }
}
