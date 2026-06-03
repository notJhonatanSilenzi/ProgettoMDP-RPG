package it.unicam.cs.mpgc.rpg126164.persistance.seeders;

import it.unicam.cs.mpgc.rpg126164.domain.characters.Enemy;
import it.unicam.cs.mpgc.rpg126164.domain.characters.EnemyType;
import it.unicam.cs.mpgc.rpg126164.domain.characters.stats.Archetype;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.equipment.Weapon;
import it.unicam.cs.mpgc.rpg126164.persistance.repositories.WeaponRepository;
import org.hibernate.Session;

/**
 * This class works as a data seeder for the database, adding data for the entity "enemies"
 */
public class EnemySeeder implements Seeder {

    private WeaponRepository weaponRepository;

    @Override
    public void seed(Session session) {
        weaponRepository = new WeaponRepository(session);

        seedNormalEnemies(session);
        seedMediumEnemies(session);
        seedLargeAndBoss(session);
    }

    /**
     * Seeds normal enemies
     * @param session the session used to persist data
     */
    private void seedNormalEnemies(Session session) {
        Weapon ironSpear = weaponRepository.findByName("Iron Spear");
        Weapon ironSword = weaponRepository.findByName("Iron Sword");
        Weapon simpleSpell = weaponRepository.findByName("Simple Spell");
        Weapon fireSpell = weaponRepository.findByName("Fire Spell");

        session.persist(new Enemy("Bandit", "a thief lurking in the woods", ironSword, Archetype.BERSERKER, EnemyType.NORMAL));
        session.persist(new Enemy("Militia Guard", "a poorly trained warrior", ironSpear, Archetype.WARRIOR, EnemyType.NORMAL));
        session.persist(new Enemy("Cultist", "a fanatic cleric", simpleSpell, Archetype.CLERIC, EnemyType.NORMAL));
        session.persist(new Enemy("Apprentice Sorcerer", "a normal sorcerer", fireSpell, Archetype.SORCERER, EnemyType.NORMAL));
    }

    /**
     * Seeds all the medium enemies
     * @param session the session used to persist data
     */
    private void seedMediumEnemies(Session session) {
        Weapon heavySpear = weaponRepository.findByName("Heavy Spear");
        Weapon ironAxe = weaponRepository.findByName("Iron Axe");

        session.persist(new Enemy("Orc Veteran", "a strong orc", heavySpear, Archetype.WARRIOR, EnemyType.MEDIUM));
        session.persist(new Enemy("Assassin", "A dangerous thief", ironAxe, Archetype.BERSERKER, EnemyType.MEDIUM));
    }

    /**
     * Seeds the large enemy and the boss
     * @param session the session used to persist the data
     */
    private void seedLargeAndBoss(Session session) {
        Weapon lightSpell = weaponRepository.findByName("Light Spell");
        Weapon lightning = weaponRepository.findByName("Lightning Spell");

        session.persist(new Enemy("High Priest", "A powerful cleric", lightSpell, Archetype.CLERIC, EnemyType.LARGE));
        session.persist(new Enemy("Archmage", "A powerful sorcerer", lightning, Archetype.SORCERER, EnemyType.BOSS));
    }
}
