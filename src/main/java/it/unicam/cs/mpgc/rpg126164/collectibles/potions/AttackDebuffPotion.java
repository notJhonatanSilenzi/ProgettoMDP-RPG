package it.unicam.cs.mpgc.rpg126164.collectibles.potions;

import it.unicam.cs.mpgc.rpg126164.characters.Fighter;

import java.util.Objects;

/**
 * This potion represents an attack debuff to apply to an enemy, and it lasts til the end of the fight
 */
public class AttackDebuffPotion extends Potion{

    private final int decreaseATK;

    /**
     * Creates an attack debuff potion
     * @param decreaseATK the amount of ATK to cut off from the stats of the given enemy
     */
    public AttackDebuffPotion(String name, String description, int maxAmount, int tradeValue, PotionTargetType targetType, int decreaseATK) {
        if (decreaseATK <= 0) throw new IllegalArgumentException("DecreaseDF must be greater than 0");

        super(name, description, maxAmount, tradeValue, targetType);
        this.decreaseATK = decreaseATK;
    }

    @Override
    public void consume(Fighter target) {
        if (target == null || this.invalidTarget(target))
            throw new IllegalArgumentException("Invalid parameter");

        target.getSheet().decreaseDefense(this.getDecreaseATK());
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

        return this.decreaseATK == ((AttackDebuffPotion) obj).getDecreaseATK();
    }

    /**
     * Calculates the hash code of this item
     * @return the hash code of this item
     */
    @Override
    public int hashCode() { return Objects.hash(super.hashCode(), decreaseATK); }


    // GETTER

    public int getDecreaseATK() {
        return decreaseATK;
    }
}
