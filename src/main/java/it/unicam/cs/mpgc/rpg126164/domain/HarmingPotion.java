package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.AbstractItem;
import it.unicam.cs.mpgc.rpg126164.abstractions.Consumable;
import it.unicam.cs.mpgc.rpg126164.abstractions.Fighter;

/**
 * This class represents a harming potion, which causes damage directly on the hp of the enemy, without taking in
 * count the defense of the target
 */
public class HarmingPotion extends AbstractItem implements Consumable {

    private final int HPdamage;

    /**
     * Creates a harming potion
     * @param name its name
     * @param description its description
     * @param maxAmount the maximum amount of this item that can be stacked in the inventory
     * @param tradeValue its trade value
     * @param HPdamage its damage value
     */
    public HarmingPotion(String name, String description, int maxAmount, int tradeValue, int HPdamage) {
        if (HPdamage <= 0) throw new IllegalArgumentException("HP damage must be greater than 0");

        super(name, description, maxAmount, tradeValue);
        this.HPdamage = HPdamage;
    }

    @Override
    public void consume(Fighter target) {
        if (target == null) throw new IllegalArgumentException("Target cannot be null");

        target.getSheet().damage(HPdamage);
    }

    // GETTERS

    public int getHPdamage() { return HPdamage; }
}
