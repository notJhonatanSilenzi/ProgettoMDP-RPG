package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.Effect;
import it.unicam.cs.mpgc.rpg126164.abstractions.Fighter;

/**
 * This class represents a harming effect, which causes damage directly on the hp of the enemy, without taking in
 * count the defense of the target
 */
public class HarmingEffect implements Effect {

    private final int damageHP;

    /**
     * Creates a harming potion
     * @param damageHP its damage value
     */
    public HarmingEffect(int damageHP) {
        if (damageHP <= 0) throw new IllegalArgumentException("HP damage must be greater than 0");

        this.damageHP = damageHP;
    }

    @Override
    public void apply(Fighter target) {
        if (target == null) throw new IllegalArgumentException("Target cannot be null");

        target.getSheet().damage(this.getDamageHP());
    }

    // GETTERS

    public int getDamageHP() { return damageHP; }
}
