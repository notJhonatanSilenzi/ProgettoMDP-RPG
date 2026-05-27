package it.unicam.cs.mpgc.rpg126164.collectibles.potions;

import it.unicam.cs.mpgc.rpg126164.characters.Fighter;

import java.util.Objects;

/**
 * This class represents a resistance potion, which causes an increase of the defense only for players.
 * It lasts til the ending of the current fight
 */
public class ResistancePotion extends Potion {

    private final int increaseDF;

    /**
     * Creates a resistance potion
     * @param DF the amount of defense that the player receives if it consumes the potion with this effect
     */
    public ResistancePotion(String name, String description, int maxAmount, int tradeValue, PotionTargetType targetType, int DF) {
        if (DF <= 0) throw new IllegalArgumentException("Defense increase must be positive.");

        super(name, description, maxAmount, tradeValue, targetType);
        this.increaseDF = DF;
    }

    @Override
    public void consume(Fighter target) {
        if (target == null || this.invalidTarget(target))
            throw new IllegalArgumentException("Invalid parameter");

        target.getSheet().increaseDefense(getIncreaseDF());
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

        return this.increaseDF == ((ResistancePotion) obj).getIncreaseDF();
    }

    /**
     * Calculates the hash code of this item
     * @return the hash code of this item
     */
    @Override
    public int hashCode() { return Objects.hash(super.hashCode(), increaseDF); }


    // GETTERS

    public int getIncreaseDF() { return increaseDF; }
}
