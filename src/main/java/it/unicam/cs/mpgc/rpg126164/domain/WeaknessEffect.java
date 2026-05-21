package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.Effect;
import it.unicam.cs.mpgc.rpg126164.abstractions.Fighter;

/**
 * This effect represents a weakness effect, which causes a decrease of the defense of an enemy. It lasts til
 * the ending of the current fight.
 */
public class WeaknessEffect implements Effect {

    private final int decreaseDF;

    /**
     * Creates a weakness effect
     * @param decreaseDF the amount of defense that an enemy receives if it receives this effect
     */
    public WeaknessEffect(int decreaseDF) {
        if (decreaseDF <= 0) throw new IllegalArgumentException("The decrease of attack must be greater than 0");

        this.decreaseDF = decreaseDF;
    }

    @Override
    public void apply(Fighter target) {
        target.getSheet().decreaseAttack(getDecreaseDF());
    }


    // GETTERS

    public int getDecreaseDF() { return decreaseDF; }
}
