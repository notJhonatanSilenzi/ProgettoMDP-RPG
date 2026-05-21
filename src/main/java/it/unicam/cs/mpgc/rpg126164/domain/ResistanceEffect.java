package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.Effect;
import it.unicam.cs.mpgc.rpg126164.abstractions.Fighter;

/**
 * This effect represents a resistance effect for a potion, which causes an increase of the defense only for players.
 * It lasts til the ending of the current fight
 */
public class ResistanceEffect implements Effect {

    private final int increaseDF;

    /**
     * Creates a resistance effect
     * @param DF the amount of defense that the player receives if it consumes the potion with this effect
     */
    public ResistanceEffect(int DF) {
        if (DF <= 0) throw new IllegalArgumentException("Defense increase must be positive.");

        this.increaseDF = DF;
    }

    @Override
    public void apply(Fighter target) { target.getSheet().increaseDefense(getIncreaseDF()); }


    // GETTERS

    public int getIncreaseDF() { return increaseDF; }
}
