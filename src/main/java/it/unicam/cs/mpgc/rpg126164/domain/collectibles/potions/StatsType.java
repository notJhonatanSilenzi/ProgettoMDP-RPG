package it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions;

import it.unicam.cs.mpgc.rpg126164.domain.characters.Enemy;
import it.unicam.cs.mpgc.rpg126164.domain.characters.Fighter;
import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayableCharacter;

/**
 * This enum represents the type of stat that a potion can modify. It is used to determine which stat
 * of the target character will be affected by the potion's effect.
 */
public enum StatsType {
    HEALTH,
    ATTACK,
    DEFENSE;

    /**
     * Applies the modifier of a potion to the target, given the target type. The getPlayer receives
     * an increase of stats, the enemy receives a decrease of stats
     * @param target the fighter that receives the effect
     * @param modifier the stats modifier to apply
     * @param targetType the type of target that receives the effect
     */
    public void apply(Fighter target, int modifier, PotionTargetType targetType) {
        switch (targetType) {
            case SELF -> { switch (this) {
                    case HEALTH -> target.getSheet().heal(modifier);
                    case ATTACK -> target.getSheet().increaseAttack(modifier);
                    case DEFENSE -> target.getSheet().increaseDefense(modifier);
                }
            }
            case ENEMY  -> { switch (this) {
                    case HEALTH -> target.getSheet().damage(modifier);
                    case ATTACK -> target.getSheet().decreaseAttack(modifier);
                    case DEFENSE -> target.getSheet().decreaseDefense(modifier);
                }
            }
        }
    }

    /**
     * Checks if the given target can't receive the effects of this potion
     * @param target the fighter to check on
     * @return true if this target cannot receive the given effect, false otherwise
     */
    public boolean invalidTarget(Fighter target, PotionTargetType targetType) {
        return switch (target) {
            case null -> true;
            case PlayableCharacter _ when targetType == PotionTargetType.ENEMY -> true;
            case Enemy _ when targetType == PotionTargetType.SELF -> true;
            default -> false;
        };
    }
}
