package it.unicam.cs.mpgc.rpg126164.collectibles.potions;

import it.unicam.cs.mpgc.rpg126164.characters.Fighter;

import java.util.Objects;

/**
 * This class represents a harming potion, which causes damage directly on the hp of the enemy, without taking in
 * count the defense of the target
 */
public class HarmingPotion extends Potion {

    private final int damageHP;

    /**
     * Creates a harming potion
     * @param damageHP its damage value
     */
    public HarmingPotion(String name, String description, int maxAmount, int tradeValue, PotionTargetType targetType, int damageHP) {
        if (damageHP <= 0) throw new IllegalArgumentException("HP damage must be greater than 0");

        super(name, description, maxAmount, tradeValue, targetType);
        this.damageHP = damageHP;
    }

    @Override
    public void consume(Fighter target) {
        if (target == null || this.invalidTarget(target))
            throw new IllegalArgumentException("Invalid parameter");

        target.getSheet().damage(this.getDamageHP());
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

        return this.damageHP == ((HarmingPotion) obj).getDamageHP();
    }

    /**
     * Calculates the hash code of this item
     * @return the hash code of this item
     */
    @Override
    public int hashCode() { return Objects.hash(super.hashCode(), damageHP); }


    // GETTERS

    public int getDamageHP() { return damageHP; }
}
