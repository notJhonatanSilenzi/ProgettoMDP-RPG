package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.Effect;
import it.unicam.cs.mpgc.rpg126164.abstractions.Fighter;

/**
 * This class represents a healing effect, an item that can increase the amount of HP of a character
 */
public class HealingEffect implements Effect {

    private final int increaseHP;

    /**
     * Creates a healing potion
     * @param increaseHP the amount of increaseHP that this potion can increase
     */
    public HealingEffect(int increaseHP) {
        if (increaseHP <= 0)
            throw new IllegalArgumentException("Invalid increaseHP increase value");

        this.increaseHP = increaseHP;
    }

    @Override
    public void apply(Fighter target) {
        if (target == null) throw new IllegalArgumentException("Target cannot be null");

        target.getSheet().heal(this.getIncreaseHP());
    }

    // GETTERS

    public int getIncreaseHP() { return increaseHP; }
}
