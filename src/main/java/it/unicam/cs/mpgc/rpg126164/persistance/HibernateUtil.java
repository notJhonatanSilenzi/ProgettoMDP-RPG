package it.unicam.cs.mpgc.rpg126164.persistance;

import it.unicam.cs.mpgc.rpg126164.domain.characters.Enemy;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.equipment.Weapon;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.Potion;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.BaseLevel;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.LevelEnemy;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * This class works as a utility for managing Hibernate sessions. It initializes a SessionFactory
 * with the annotated classes. The given classes will be persisted in the database
 */
public class HibernateUtil {

    /**
     * Creates a SessionFactory with the annotated class. It is a static final variable, so it will
     * be initialized only once and shared across the application. It annotates weapons, potions, enemies
     * and levels
     */
    private static final SessionFactory sessionFactory =
            new Configuration()
                    .addAnnotatedClass(Weapon.class)
                    .addAnnotatedClass(Potion.class)
                    .addAnnotatedClass(Enemy.class)
                    .addAnnotatedClass(BaseLevel.class)
                    .addAnnotatedClass(LevelEnemy.class)
                    .buildSessionFactory();

    /**
     * returns a SessionFactory
     * @return a SessionFactory
     */
    public static SessionFactory getSessionFactory() { return sessionFactory; }
}
