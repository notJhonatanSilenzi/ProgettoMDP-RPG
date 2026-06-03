package it.unicam.cs.mpgc.rpg126164.collectibles.equipment;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * This class represents a damage source, especially weapons or spells. They're reusable multiple times in fights,
 * and it contains an additive of ATK to the fighter's base ATK. They're not cumulable, so they can't be stacked
 * in the inventory and their count is always 1.
 */
@Entity
@Table(name = "weapons")
public class Weapon implements Equipment, Serializable {

    @Id
    @Column(nullable = false, unique = true)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Lob
    private String description;

    @Column(nullable = false, name = "max_amount")
    private int maxAmount;

    @Column(nullable = false, name = "trade_value")
    private int tradeValue;

    @Column(nullable = false, name = "atk")
    private int ATK;

    public Weapon() {}

    /**
     * Creates a damage source, equippable by the players
     * @param name its name
     * @param description its description
     * @param maxAmount the maximum amount of this item that can be stacked in the inventory, must be 1 for this type of item
     * @param tradeValue its value in the market/shop
     * @param ATK the additive of ATK to the fighter's base ATK when equipped
     */
    public Weapon(String name, String description, int maxAmount, int tradeValue, int ATK) {
        if (name == null || description == null || tradeValue <= 0 || name.isEmpty() ||
                description.isEmpty() ||  name.length() < 3 ||  description.length() < 3 ||
                    maxAmount != 1 || ATK <= 0)
            throw new IllegalArgumentException("Invalid parameters");

        this.id = UUID.randomUUID().toString();
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
        if (!(obj instanceof Weapon)) return false;

        return this.getId().equals(((Weapon) obj).getId());
    }

    /**
     * Calculates the hash code of this item
     * @return the hash code of this item
     */
    @Override
    public int hashCode() { return Objects.hash(id); }


    // GETTERS AND SETTERS

    public String getId() { return id; }

    @Override
    public String getName() { return name; }

    @Override
    public String getDescription() { return description; }

    @Override
    public int getMaxAmount() { return maxAmount; }

    @Override
    public int getTradeValue() { return tradeValue; }
}
