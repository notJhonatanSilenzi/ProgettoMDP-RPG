package it.unicam.cs.mpgc.rpg126164.domain.characters;

/**
 * This interface is a specific extension of the Fighter interface for the enemies
 */
public interface EnemyFighter extends Fighter {

    /**
     * Returns the type of enemy for this enemy
     * @return the type of enemy
     */
    EnemyType getEnemyType();
}
