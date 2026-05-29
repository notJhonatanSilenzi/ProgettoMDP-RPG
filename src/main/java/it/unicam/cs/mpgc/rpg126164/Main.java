package it.unicam.cs.mpgc.rpg126164;

import it.unicam.cs.mpgc.rpg126164.utils.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {
    static void main() {

        System.out.println("START GAME DB TEST");

        var sessionFactory = HibernateUtil.getSessionFactory();
        System.out.println(sessionFactory.getClass());
    }
}
