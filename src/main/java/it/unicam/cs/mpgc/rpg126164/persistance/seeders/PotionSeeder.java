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
        session.persist(new Potion("healing potion I", "This potion heals you, giving extra hp", 5, 60, PotionTargetType.SELF, StatsType.HEALTH, 20));
        session.persist(new Potion("healing potion II", "This potion heals you, giving extra hp", 4, 120, PotionTargetType.SELF, StatsType.HEALTH, 35));
        session.persist(new Potion("healing potion III", "This potion heals you, giving extra hp", 3, 180, PotionTargetType.SELF, StatsType.HEALTH, 50));
    }

    private void seedHarmingPotions(Session session) {
        session.persist(new Potion("harming potion I", "This potion harms the enemy, taking away hp", 5, 120, PotionTargetType.ENEMY, StatsType.HEALTH, 20));
        session.persist(new Potion("harming potion II", "This potion harms the enemy, taking away hp", 4, 240, PotionTargetType.ENEMY, StatsType.HEALTH, 30));
        session.persist(new Potion("harming potion III", "This potion harms the enemy, taking away hp", 3, 360, PotionTargetType.ENEMY, StatsType.HEALTH, 40));
    }

    private void seedAttackBoostPotions(Session session) {
        session.persist(new Potion("attack boost potion I", "This potion boosts your attack, giving extra attack points", 3, 90, PotionTargetType.SELF, StatsType.ATTACK, 10));
        session.persist(new Potion("attack boost potion II", "This potion boosts your attack, giving extra attack points", 2, 180, PotionTargetType.SELF, StatsType.ATTACK, 25));
    }

    private void seedWeaknessPotions(Session session) {
        session.persist(new Potion("weakness potion I", "This potion weakens the enemy, taking away attack points", 3, 90, PotionTargetType.ENEMY, StatsType.ATTACK, 5));
        session.persist(new Potion("weakness potion II", "This potion weakens the enemy, taking away attack points", 2, 180, PotionTargetType.ENEMY, StatsType.ATTACK, 15));
    }

    private void seedDefenseBoostPotions(Session session) {
        session.persist(new Potion("defense boost potion I", "This potion boosts your defense, giving extra defense points", 3, 90, PotionTargetType.SELF, StatsType.DEFENSE, 5));
        session.persist(new Potion("defense boost potion II", "This potion boosts your defense, giving extra defense points", 2, 180, PotionTargetType.SELF, StatsType.DEFENSE, 15));
    }

    private void seedDefenseDebuffPotions(Session session) {
        session.persist(new Potion("defense debuff potion I", "This potion weakens the enemy, taking away defense points", 3, 90, PotionTargetType.ENEMY, StatsType.DEFENSE, 5));
        session.persist(new Potion("defense debuff potion II", "This potion weakens the enemy, taking away defense points", 2, 180, PotionTargetType.ENEMY, StatsType.DEFENSE, 15));
    }
}
