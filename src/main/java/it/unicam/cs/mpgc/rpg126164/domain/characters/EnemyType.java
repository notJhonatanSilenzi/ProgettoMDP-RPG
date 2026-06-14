package it.unicam.cs.mpgc.rpg126164.domain.characters;

/**
 * This enum represents the three types of enemies the player can face off, and returns a multiplier to adapt the
 * stats for the gameplay
 */
public enum EnemyType {
    /**
     * The standard type of enemies, a bit weaker than the player. The most common in the game
     */
    NORMAL(0.5, 40),

    /**
     * A type of enemy stronger than the normal one, with a bit higher stats than the player. They're rare in
     * the game, more rare than normal enemies
     */
    MEDIUM(0.75, 80),

    /**
     * A type of enemy stronger than the medium one, and even more rare than the medium ones
     */
    LARGE(1.0, 120),
    /**
     * The strongest type of enemy, it has very high stats, and it's the rarest type in the game. It only appears
     * in the last level of the game
     */
    BOSS(1.15, 200);

    private final double multiplier;
    private final int goldForDefeat;

    /**
     * Gets the multiplier, given the type of enemy
     * @param multiplier the multiplier to adapt game stats
     * @param goldForDefeat the amount of gold that the player receives, if defeats the enemy
     */
    EnemyType(double multiplier, int goldForDefeat) {
        this.multiplier = multiplier;
        this.goldForDefeat = goldForDefeat;
    }


    // GETTERS

    public double getMultiplier() { return multiplier; }

    public int getGoldForDefeat() { return goldForDefeat; }
}
