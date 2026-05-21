package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.Effect;
import it.unicam.cs.mpgc.rpg126164.abstractions.Fighter;

/**
 * This effect represents an attack debuff to apply to an enemy, and it lasts til the end of the fight
 */
public class AttackDebuffEffect implements Effect {

    private final int decreaseATK;

    /**
     * Creates an attack debuff effect
     * @param decreaseATK the amount of ATK to cut off from the stats of the given enemy
     */
    public AttackDebuffEffect(int decreaseATK) {
        if (decreaseATK <= 0) throw new IllegalArgumentException("DecreaseDF must be greater than 0");

        this.decreaseATK = decreaseATK;
    }

    @Override
    public void apply(Fighter target) { target.getSheet().decreaseDefense(this.getDecreaseATK()); }


    // GETTER

    public int getDecreaseATK() {
        return decreaseATK;
    }
}
