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

    /**
     * Returns the amount of money that a playable character will gain if this enemy gets defeated
     * @return the amount of money for the defeat
     */
    int getGoldForDefeat();
}
