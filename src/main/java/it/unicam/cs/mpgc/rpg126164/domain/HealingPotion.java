package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.AbstractItem;
import it.unicam.cs.mpgc.rpg126164.abstractions.Consumable;
import it.unicam.cs.mpgc.rpg126164.abstractions.Fighter;

/**
 * This class represents a healing potion, an item that can increase the amount of HP of a character
 */
public class HealingPotion extends AbstractItem implements Consumable {

    private final int HPincrease;

    /**
     * Creates a harming potion
     * @param name its name
     * @param description its description
     * @param maxAmount its maximum amount that can be stored in the inventory
     * @param tradeValue its trade value
     * @param HPincrease the amount of HP that this potion can increase
     */
    public HealingPotion(String name, String  description, int maxAmount, int tradeValue, int HPincrease) {
        if (HPincrease <= 0)
            throw new IllegalArgumentException("Invalid HP increase value");

        super(name, description, maxAmount, tradeValue);
        this.HPincrease = HPincrease;
    }

    @Override
    public void consume(Fighter target) {
        if (target == null) throw new IllegalArgumentException("Target cannot be null");

        target.getSheet().heal(HPincrease);
    }

    // GETTERS

    public int getHPincrease() { return HPincrease; }
}
