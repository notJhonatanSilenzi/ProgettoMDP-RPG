package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.Fighter;

import java.util.Objects;

/**
 * This potion gives an attack boost to a playable character, and it benefits from this effect
 * until the ending of the fight.
 */
public class AttackBoostPotion extends Potion {

    private final int additiveATK;

    /**
     * Creates an attack boost potion
     * @param additiveATK the amount of ATK to add to the current ATK of the player
     */
    public AttackBoostPotion(String name, String description, int maxAmount, int tradeValue, PotionTargetType targetType, int additiveATK) {
        if (additiveATK <= 0) throw new IllegalArgumentException("Invalid Attack increase");

        super(name, description, maxAmount, tradeValue, targetType);
        this.additiveATK = additiveATK;
    }

    @Override
    public void consume(Fighter target) {
        if (target == null || this.invalidTarget(target))
            throw new IllegalArgumentException("Invalid parameter");

        target.getSheet().increaseAttack(additiveATK);;
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

        return this.additiveATK == ((AttackBoostPotion) obj).getAdditiveATK();
    }

    /**
     * Calculates the hash code of this item
     * @return the hash code of this item
     */
    @Override
    public int hashCode() { return Objects.hash(super.hashCode(), additiveATK); }


    // GETTER

    public int getAdditiveATK() { return additiveATK; }
}
