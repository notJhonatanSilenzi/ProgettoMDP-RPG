package it.unicam.cs.mpgc.rpg126164.abstractions;

/**
 * This interface represents a generic effect caused by to consume a consumable item. It only shows the method to
 * apply the effects to the given target
 */
public interface Effect {

    /**
     * Applies this effect to the given target, which has to be a character able to fight with other characters
     * @param target the fighter that receives the effects
     */
    void apply(Fighter target);
}
