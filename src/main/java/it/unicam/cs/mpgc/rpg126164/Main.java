package it.unicam.cs.mpgc.rpg126164;

import it.unicam.cs.mpgc.rpg126164.characters.Fighter;
import it.unicam.cs.mpgc.rpg126164.characters.PlayableCharacter;
import it.unicam.cs.mpgc.rpg126164.characters.stats.Archetype;
import it.unicam.cs.mpgc.rpg126164.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.gamemechanics.BaseLevel;
import it.unicam.cs.mpgc.rpg126164.gamemechanics.LevelEnemy;
import it.unicam.cs.mpgc.rpg126164.repositories.EnemyRepository;
import it.unicam.cs.mpgc.rpg126164.repositories.LevelEnemyRepository;
import it.unicam.cs.mpgc.rpg126164.repositories.LevelRepository;
import it.unicam.cs.mpgc.rpg126164.repositories.PotionRepository;
import it.unicam.cs.mpgc.rpg126164.seeders.*;
import it.unicam.cs.mpgc.rpg126164.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        init(session);

        LevelRepository levelRepository = new LevelRepository(session);
        LevelEnemyRepository levelEnemyRepository = new LevelEnemyRepository(session);
        EnemyRepository enemyRepository = new EnemyRepository(session);
        PotionRepository potionRepository = new PotionRepository(session);

        BaseLevel level2 = levelRepository.findByName("Level 2 - Mineshaft");
        System.out.println("Level: " + level2.getName());

        List<LevelEnemy> entries = levelEnemyRepository.findByLevel(level2);
        System.out.println("Entries: " + entries.size());

        for (LevelEnemy config : entries) {
            System.out.println(
                    config.getEnemy().getName()
                            + " x"
                            + config.getQuantity()
            );
        }

        List<Fighter> enemies = new ArrayList<>();

        for (LevelEnemy config : entries) {
            for (int i = 0; i < config.getQuantity(); i++) {
                enemies.add(
                        config.getEnemy()
                );
            }
        }
        System.out.println("Enemies: " + enemies.size());

        PlayableCharacter player = new PlayableCharacter("Player", "A true hero", Archetype.WARRIOR);
        level2.startLevel(player, new HashSet<>(enemies), new ItemStack(potionRepository.findByName("healing potion I"), 5));

        tx.commit();
        session.close();
    }

    private static void init(Session session) {

        SeederManager seederManager = new SeederManager(List.of(
                new PotionSeeder(),
                new WeaponSeeder(),
                new EnemySeeder(),
                new LevelSeeder(),
                new LevelEnemySeeder()
        ));
        seederManager.seedIfNecessary(session);
    }
}
