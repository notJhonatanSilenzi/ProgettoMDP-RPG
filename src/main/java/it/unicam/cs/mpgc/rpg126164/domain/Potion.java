package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.*;

import java.util.Objects;

/**
 * This class represents a generic potions, and it can be consumed to benefit of the specific effect. It implements
 * the Consumable interface, and extends the AbstractItem abstract class
 */
public abstract class Potion implements Item, Consumable {

    private final String name;
    private final String description;
    private final int maxAmount;
    private final int tradeValue;
    private final PotionTargetType targetType;

    /**
     * Creates a potion
     * @param name its name
     * @param description its description
     * @param maxAmount the maximum amount reachable in an item stack
     * @param tradeValue its value in the market
     * @param targetType the type of target that this effect can be applied to
     */
    public Potion(String name, String description, int maxAmount, int tradeValue, PotionTargetType targetType) {

        if (name == null || description == null || maxAmount <= 0 || tradeValue <= 0 ||
                targetType == null || name.isEmpty() || description.isEmpty() ||
                    name.length() < 3 ||  description.length() < 3)
            throw new IllegalArgumentException("Effect and target type cannot be null.");

        this.name = name;
        this.description = description;
        this.maxAmount = maxAmount;
        this.tradeValue = tradeValue;
        this.targetType = targetType;
    }

    @Override
    public abstract void consume(Fighter target);

    /**
     * Checks if the given target can't receive the effects of this potion
     * @param target the fighter to check on
     * @return true if this target cannot receive the given effect, false otherwise
     */
    public boolean invalidTarget(Fighter target) {
        return switch (target) {
            case null -> true;
            case PlayableCharacter _ when this.targetType == PotionTargetType.ENEMY -> true;
            case Enemy _ when this.targetType == PotionTargetType.SELF -> true;
            default -> false;
        };
    }

    /**
     * Checks if the given object is equal to this item
     * @param obj the reference object with which to compare.
     * @return true if the two objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Potion)) return false;

        return (this.name.equals(((Potion) obj).getName())
                && this.targetType == ((Potion) obj).getTargetType());
    }

    /**
     * Calculates the hash code of this item
     * @return the hash code of this item
     */
    @Override
    public int hashCode() { return Objects.hash(name, targetType); }


    // GETTERS

    @Override
    public PotionTargetType getTargetType() { return targetType; }

    @Override
    public String getName() { return name; }

    @Override
    public String getDescription() { return description; }

    @Override
    public int getMaxAmount() { return maxAmount; }

    @Override
    public int getTradeValue() { return tradeValue; }
}
