package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.Equipment;
import it.unicam.cs.mpgc.rpg126164.abstractions.Item;

import java.util.Objects;

/**
 * This class represents a damage source, especially weapons or spells. They're reusable multiple times in fights,
 * and it contains an additive of ATK to the fighter's base ATK. They're not cumulable, so they can't be stacked
 * in the inventory and their count is always 1.
 */
public class DamageSource implements Item, Equipment {

    private final String name;
    private final String description;
    private final int maxAmount;
    private final int tradeValue;
    private final int ATK;

    /**
     * Creates a damage source, equippable by the players
     * @param name its name
     * @param description its description
     * @param maxAmount the maximum amount of this item that can be stacked in the inventory, must be 1 for this type of item
     * @param tradeValue its value in the market/shop
     * @param ATK the additive of ATK to the fighter's base ATK when equipped
     */
    public DamageSource(String name, String description, int maxAmount, int tradeValue, int ATK) {
        if (name == null || description == null || tradeValue <= 0 || name.isEmpty() ||
                description.isEmpty() ||  name.length() < 3 ||  description.length() < 3 ||
                    maxAmount != 1 || ATK <= 0)
            throw new IllegalArgumentException("Invalid parameters");

        this.name = name;
        this.description = description;
        this.maxAmount = maxAmount;
        this.tradeValue = tradeValue;
        this.ATK = ATK;
    }

    @Override
    public int useEquipment() { return this.ATK; }

    /**
     * Checks if the given object is equal to this item
     * @param obj the reference object with which to compare.
     * @return true if the two objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Equipment)) return false;

        return (this.name.equals(((Equipment) obj).getName()));
    }

    /**
     * Calculates the hash code of this item
     * @return the hash code of this item
     */
    @Override
    public int hashCode() { return Objects.hash(name); }


    // GETTERS AND SETTERS

    @Override
    public String getName() { return name; }

    @Override
    public String getDescription() { return description; }

    @Override
    public int getMaxAmount() { return maxAmount; }

    @Override
    public int getTradeValue() { return tradeValue; }
}
