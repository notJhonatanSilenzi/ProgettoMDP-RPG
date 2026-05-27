package it.unicam.cs.mpgc.rpg126164.characters;

/**
 * This enum represents the three types of enemies the player can face off, and returns a multiplier to adapt the
 * stats for the gameplay
 */
public enum EnemyType {
    /**
     * The standard type of enemies, a bit weaker than the player. The most common in the game
     */
    NORMAL(0.8),

    /**
     * A type of enemy stronger than the normal one, with a bit higher stats than the player. They're rare in
     * the game, more rare than normal enemies
     */
    MEDIUM(1.2),

    /**
     * The strongest type of enemy, it has very high stats, and it's the rarest type in the game. It only appears
     * in the last level of the game
     */
    BOSS(1.7);

    private final double multiplier;

    /**
     * Gets the multiplier, given the type of enemy
     * @param multiplier the multiplier to adapt game stats
     */
    EnemyType(double multiplier) { this.multiplier = multiplier; }

    public double getMultiplier() { return multiplier; }
}
