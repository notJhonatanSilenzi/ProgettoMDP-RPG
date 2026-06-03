package it.unicam.cs.mpgc.rpg126164;

import it.unicam.cs.mpgc.rpg126164.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory();
    }
}
