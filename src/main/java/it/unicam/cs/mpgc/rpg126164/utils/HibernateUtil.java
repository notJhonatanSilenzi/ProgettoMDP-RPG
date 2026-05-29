package it.unicam.cs.mpgc.rpg126164.utils;

import it.unicam.cs.mpgc.rpg126164.collectibles.equipment.DamageSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory =
            new Configuration()
                    .addAnnotatedClass(DamageSource.class)
                    .buildSessionFactory();

    public static Session getSessionFactory() {
        return sessionFactory.openSession();
    }
}
