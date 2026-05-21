package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.Fighter;

/**
 * This enum represents the type of targets that an effect can be applied to. Effects can be applied to players or
 * to enemies
 */
public enum PotionTargetType {

    /**
     * the effect can be applied only to the user of the potion
     */
    SELF {
        @Override
        public boolean isValid(Fighter user, Fighter target) { return user.equals(target); }
    },

    /**
     * The effect can be applied only to the enemies
     */
    ENEMY {
        @Override
        public boolean isValid(Fighter user, Fighter target) { return !user.equals(target); }
    };

    /**
     * This abstract method checks if the target is valid for the user of the potion, depending on the type of target.
     * @param user the target that consumes a consumable item
     * @param target the target that receives the effects
     * @return true if the effect can be applied to the given target, false otherwise
     */
    public abstract boolean isValid(Fighter user, Fighter target);
}
