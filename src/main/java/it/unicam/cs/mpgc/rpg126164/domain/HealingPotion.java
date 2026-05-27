package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.Fighter;

import java.util.Objects;

/**
 * This class represents a healing potion, an item that can increase the amount of HP of a character
 */
public class HealingPotion extends Potion {

    private final int increaseHP;

    /**
     * Creates a healing potion
     * @param increaseHP the amount of increaseHP that this potion can increase
     */
    public HealingPotion(String name, String description, int maxAmount, int tradeValue, PotionTargetType targetType, int increaseHP) {
        if (increaseHP <= 0)
            throw new IllegalArgumentException("Invalid increaseHP increase value");

        super(name, description, maxAmount, tradeValue, targetType);
        this.increaseHP = increaseHP;
    }

    @Override
    public void consume(Fighter target) {
        if (target == null || this.invalidTarget(target))
            throw new IllegalArgumentException("Invalid parameter");

        target.getSheet().heal(this.getIncreaseHP());
    }

    /**
     * Checks if the given object is equal to this item
     * @param obj the reference object with which to compare.
     * @return true if the two objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;

        return this.increaseHP == ((HealingPotion) obj).getIncreaseHP();
    }

    /**
     * Calculates the hash code of this item
     * @return the hash code of this item
     */
    @Override
    public int hashCode() { return Objects.hash(super.hashCode(), increaseHP); }


    // GETTERS

    public int getIncreaseHP() { return increaseHP; }
}
