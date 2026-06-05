package it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions;

/**
 * This enum represents the type of targets that an effect can be applied to. Effects can be applied to players or
 * to enemies
 */
public enum PotionTargetType {

    /**
     * the effect can be applied only to the user of the potion
     */
    SELF,

    /**
     * The effect can be applied only to the enemies
     */
    ENEMY
}
