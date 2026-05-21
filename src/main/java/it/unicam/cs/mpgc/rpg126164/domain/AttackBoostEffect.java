package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.Effect;
import it.unicam.cs.mpgc.rpg126164.abstractions.Fighter;

/**
 * This effect gives an attack boost to a playable character, and it benefits from this effect until the ending
 * of the fight.
 */
public class AttackBoostEffect implements Effect {

    private final int additiveATK;

    /**
     * Creates an attack boost effect
     * @param additiveATK the amount of ATK to add to the current ATK of the player
     */
    public AttackBoostEffect(int additiveATK) {
        if (additiveATK <= 0) throw new IllegalArgumentException("Invalid Attack increase");

        this.additiveATK = additiveATK;
    }

    @Override
    public void apply(Fighter target) {
        if (target == null) throw new IllegalArgumentException("Invalid target");

        target.getSheet().increaseAttack(additiveATK);;
    }


    // GETTER

    public int getAdditiveATK() { return additiveATK; }
}
