package it.unicam.cs.mpgc.rpg126164.collectibles.potions;

import it.unicam.cs.mpgc.rpg126164.characters.Fighter;

import java.util.Objects;

/**
 * This class represents a weakness potion, which causes a decrease of the defense of an enemy. It lasts til
 * the ending of the current fight.
 */
public class WeaknessPotion extends Potion {

    private final int decreaseDF;

    /**
     * Creates a weakness potion
     * @param decreaseDF the amount of defense that an enemy receives if it receives this effect
     */
    public WeaknessPotion(String name, String description, int maxAmount, int tradeValue, PotionTargetType targetType, int decreaseDF) {
        if (decreaseDF <= 0) throw new IllegalArgumentException("The decrease of attack must be greater than 0");

        super(name, description, maxAmount, tradeValue, targetType);
        this.decreaseDF = decreaseDF;
    }

    @Override
    public void consume(Fighter target) {
        if (target == null || this.invalidTarget(target))
            throw new IllegalArgumentException("Invalid parameter");

        target.getSheet().decreaseDefense(getDecreaseDF());
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

        return this.decreaseDF == ((WeaknessPotion) obj).getDecreaseDF();
    }

    /**
     * Calculates the hash code of this item
     * @return the hash code of this item
     */
    @Override
    public int hashCode() { return Objects.hash(super.hashCode(), decreaseDF); }


    // GETTERS

    public int getDecreaseDF() { return decreaseDF; }
}
