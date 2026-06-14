package it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions;

import it.unicam.cs.mpgc.rpg126164.domain.characters.Fighter;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * This class represents a generic potions, and it can be consumed to benefit of the specific effect. It implements
 * the Consumable interface, and extends the AbstractItem abstract class
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "potions")
public class Potion implements Consumable, Serializable {

    @Id
    @Column(nullable = false, unique = true)
    private String id;

    @Column(nullable = false, name = "name", unique = true)
    private String name;

    @Lob
    @Column(nullable = false, name = "description")
    private String description;

    @Column(nullable = false, name = "max_amount")
    private int maxAmount;

    @Column(nullable = false, name = "trade_value")
    private int tradeValue;

    @Column(nullable = false, name = "target_type")
    @Enumerated(EnumType.STRING)
    private PotionTargetType targetType;

    @Column(nullable = false, name = "stat_type")
    @Enumerated(EnumType.STRING)
    private StatsType statsType;

    @Column(nullable = false, name = "modifier")
    private int statsModifier;

    public Potion() {}

    /**
     * Creates a potion
     * @param name its name
     * @param description its description
     * @param maxAmount the maximum amount reachable in an item stack
     * @param tradeValue its value in the market
     * @param targetType the type of target that this effect can be applied to
     * @param statsType the type of stat that the potion modifies
     * @param statsModifier the amount of stat that this potion takes away or adds to the sheet
     * @throws IllegalArgumentException if the given parameters are null, empty, too short on character
     * count or numeric values below zero
     */
    public Potion(String name, String description, int maxAmount, int tradeValue,
                  PotionTargetType targetType, StatsType statsType, int statsModifier) {

        if (name == null || description == null || maxAmount <= 0 || tradeValue <= 0 ||
                targetType == null || statsType == null || statsModifier <= 0 || name.isEmpty()
                || description.isEmpty() || name.length() < 3 ||  description.length() < 3)
            throw new IllegalArgumentException("Invalid parameters");

        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.maxAmount = maxAmount;
        this.tradeValue = tradeValue;
        this.targetType = targetType;
        this.statsType = statsType;
        this.statsModifier = statsModifier;
    }

    @Override
    public void consume(Fighter target) {
        if (target == null || statsType.invalidTarget(target, targetType))
            throw new IllegalArgumentException("Invalid parameter");

        this.statsType.apply(target, statsModifier, targetType);
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

        return (this.id.equals(((Potion) obj).id));
    }

    /**
     * Calculates the hash code of this item
     * @return the hash code of this item
     */
    @Override
    public int hashCode() { return Objects.hash(id); }


    // GETTERS

    public String getId() { return id; }

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

    @Override
    public StatsType getStatsType() { return statsType; }

    @Override
    public int getStatsModifier() { return statsModifier; }

    @Override
    public String getStatsDesc() {
        return this.getStatsModifier() + ((this.statsType == StatsType.HEALTH) ? " HP" : ((this.statsType == StatsType.ATTACK) ? " ATK" : " DF"));
    }
}
