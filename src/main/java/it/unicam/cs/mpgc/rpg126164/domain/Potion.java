package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.AbstractItem;
import it.unicam.cs.mpgc.rpg126164.abstractions.Consumable;
import it.unicam.cs.mpgc.rpg126164.abstractions.Effect;
import it.unicam.cs.mpgc.rpg126164.abstractions.Fighter;

/**
 * This class represents a generic potions, and it can be consumed to benefit of the specific effect. It implements
 * the Consumable interface, and extends the AbstractItem abstract class
 */
public class Potion extends AbstractItem implements Consumable {

    private final Effect effect;
    private final PotionTargetType targetType;

    /**
     * Creates a potion
     * @param name its name
     * @param description its description
     * @param maxAmount the maximum amount reachable in an item stack
     * @param tradeValue its value in the market
     * @param effect the effect that returns if consumed
     * @param targetType the type of target that this effect can be applied to
     */
    public Potion(String name, String description, int maxAmount, int tradeValue, Effect effect,
                  PotionTargetType targetType) {
        if (effect == null || targetType == null)
            throw new IllegalArgumentException("Effect and target type cannot be null.");

        super(name, description, maxAmount, tradeValue);
        this.effect = effect;
        this.targetType = targetType;
    }

    @Override
    public void consume(Fighter target) {
        if (target == null) throw new IllegalArgumentException("Target cannot be null.");

        this.effect.apply(target);
    }

    /**
     * Checks if the given target can't receive the effects of this potion
     * @param target the fighter to check on
     * @return true if this target cannot receive the given effect, false otherwise
     */
    private boolean invalidTarget(Fighter target) {
        return switch (target) {
            case null -> true;
            case PlayableCharacter _ when this.targetType == PotionTargetType.ENEMY -> true;
            case Enemy _ when this.targetType == PotionTargetType.SELF -> true;
            default -> false;
        };
    }


    // GETTERS

    public Effect getEffect() { return effect; }

    @Override
    public PotionTargetType getTargetType() { return targetType; }
}
