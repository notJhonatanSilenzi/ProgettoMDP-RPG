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
        session.flush();
    }

    /**
     * Seeds all the health potions
     * @param session the current hibernate session
     */
    private void seedHealingPotions(Session session) {
        session.persist(new Potion("Health Potion I", "This potion heals you, giving extra hp", 4, 60, PotionTargetType.SELF, StatsType.HEALTH, 15));
        session.persist(new Potion("Health Potion II", "This potion heals you, giving extra hp", 3, 120, PotionTargetType.SELF, StatsType.HEALTH, 25));
        session.persist(new Potion("Health Potion III", "This potion heals you, giving extra hp", 2, 180, PotionTargetType.SELF, StatsType.HEALTH, 40));
    }

    /**
     * Seeds all the harming potions
     * @param session the current hibernate session
     */
    private void seedHarmingPotions(Session session) {
        session.persist(new Potion("Harming Potion I", "This potion harms the enemy, taking away hp", 4, 120, PotionTargetType.ENEMY, StatsType.HEALTH, 10));
        session.persist(new Potion("Harming Potion II", "This potion harms the enemy, taking away hp", 3, 240, PotionTargetType.ENEMY, StatsType.HEALTH, 18));
        session.persist(new Potion("Harming Potion III", "This potion harms the enemy, taking away hp", 2, 360, PotionTargetType.ENEMY, StatsType.HEALTH, 25));
    }

    /**
     * Seeds all the attack boost potions
     * @param session the current hibernate session
     */
    private void seedAttackBoostPotions(Session session) {
        session.persist(new Potion("Attack Boost Potion I", "This potion boosts your attack, giving extra attack points", 3, 90, PotionTargetType.SELF, StatsType.ATTACK, 10));
        session.persist(new Potion("Attack Boost Potion II", "This potion boosts your attack, giving extra attack points", 2, 180, PotionTargetType.SELF, StatsType.ATTACK, 15));
    }

    /**
     * Seeds all the weakness potions
     * @param session the current hibernate session
     */
    private void seedWeaknessPotions(Session session) {
        session.persist(new Potion("Weakness Potion I", "This potion weakens the enemy, taking away attack points", 3, 90, PotionTargetType.ENEMY, StatsType.ATTACK, 7));
        session.persist(new Potion("Weakness Potion II", "This potion weakens the enemy, taking away attack points", 2, 180, PotionTargetType.ENEMY, StatsType.ATTACK, 10));
    }

    /**
     * Seeds all the defense boost potions
     * @param session the current hibernate session
     */
    private void seedDefenseBoostPotions(Session session) {
        session.persist(new Potion("Defense Boost Potion I", "This potion boosts your defense, giving extra defense points", 3, 90, PotionTargetType.SELF, StatsType.DEFENSE, 7));
        session.persist(new Potion("Defense Boost Potion II", "This potion boosts your defense, giving extra defense points", 2, 180, PotionTargetType.SELF, StatsType.DEFENSE, 10));
    }

    /**
     * Seeds all the defense debuff potions
     * @param session the current hibernate session
     */
    private void seedDefenseDebuffPotions(Session session) {
        session.persist(new Potion("Defense Debuff Potion I", "This potion weakens the enemy, taking away defense points", 3, 90, PotionTargetType.ENEMY, StatsType.DEFENSE, 8));
        session.persist(new Potion("Defense Debuff Potion II", "This potion weakens the enemy, taking away defense points", 2, 180, PotionTargetType.ENEMY, StatsType.DEFENSE, 12));
    }
}
