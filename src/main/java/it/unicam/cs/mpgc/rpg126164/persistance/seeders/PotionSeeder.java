package it.unicam.cs.mpgc.rpg126164.persistance.seeders;

import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.Potion;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.PotionTargetType;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.StatsType;
import org.hibernate.Session;

/**
 * This class works as a seeder for the database, adding data for the entity "potions"
 */
public class PotionSeeder implements Seeder {

    @Override
    public void seed(Session session) {
        seedHealingPotions(session);
        seedHarmingPotions(session);
        seedAttackBoostPotions(session);
        seedWeaknessPotions(session);
        seedDefenseBoostPotions(session);
        seedDefenseDebuffPotions(session);
    }

    private void seedHealingPotions(Session session) {
        session.persist(new Potion("Health Potion I", "This potion heals you, giving extra hp", 5, 60, PotionTargetType.SELF, StatsType.HEALTH, 20));
        session.persist(new Potion("Health Potion II", "This potion heals you, giving extra hp", 4, 120, PotionTargetType.SELF, StatsType.HEALTH, 35));
        session.persist(new Potion("Health potion III", "This potion heals you, giving extra hp", 3, 180, PotionTargetType.SELF, StatsType.HEALTH, 50));
    }

    private void seedHarmingPotions(Session session) {
        session.persist(new Potion("Harming Potion I", "This potion harms the enemy, taking away hp", 5, 120, PotionTargetType.ENEMY, StatsType.HEALTH, 20));
        session.persist(new Potion("Harming Potion II", "This potion harms the enemy, taking away hp", 4, 240, PotionTargetType.ENEMY, StatsType.HEALTH, 30));
        session.persist(new Potion("Harming Potion III", "This potion harms the enemy, taking away hp", 3, 360, PotionTargetType.ENEMY, StatsType.HEALTH, 40));
    }

    private void seedAttackBoostPotions(Session session) {
        session.persist(new Potion("Attack Boost Potion I", "This potion boosts your attack, giving extra attack points", 3, 90, PotionTargetType.SELF, StatsType.ATTACK, 10));
        session.persist(new Potion("Attack Boost Potion II", "This potion boosts your attack, giving extra attack points", 2, 180, PotionTargetType.SELF, StatsType.ATTACK, 25));
    }

    private void seedWeaknessPotions(Session session) {
        session.persist(new Potion("Weakness Potion I", "This potion weakens the enemy, taking away attack points", 3, 90, PotionTargetType.ENEMY, StatsType.ATTACK, 5));
        session.persist(new Potion("Weakness Potion II", "This potion weakens the enemy, taking away attack points", 2, 180, PotionTargetType.ENEMY, StatsType.ATTACK, 15));
    }

    private void seedDefenseBoostPotions(Session session) {
        session.persist(new Potion("Defense Boost Potion I", "This potion boosts your defense, giving extra defense points", 3, 90, PotionTargetType.SELF, StatsType.DEFENSE, 5));
        session.persist(new Potion("Defense Boost Potion II", "This potion boosts your defense, giving extra defense points", 2, 180, PotionTargetType.SELF, StatsType.DEFENSE, 15));
    }

    private void seedDefenseDebuffPotions(Session session) {
        session.persist(new Potion("Defense Debuff Potion I", "This potion weakens the enemy, taking away defense points", 3, 90, PotionTargetType.ENEMY, StatsType.DEFENSE, 5));
        session.persist(new Potion("Defense Debuff Potion II", "This potion weakens the enemy, taking away defense points", 2, 180, PotionTargetType.ENEMY, StatsType.DEFENSE, 15));
    }
}
