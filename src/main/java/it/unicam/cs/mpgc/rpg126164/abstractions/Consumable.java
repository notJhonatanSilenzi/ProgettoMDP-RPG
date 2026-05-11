package it.unicam.cs.mpgc.rpg126164.abstractions;

/**
 * This interface allows any object that implements this interface to be consumed, in order to apply specific
 * effects and advantages to the character that uses the consumable or to other characters. It can only be used
 * once
 */
public interface Consumable {

    /**
     * This method allows to consume this consumable object, applying the specific effects and advantages
     * to the character that uses it or to other characters
     * @param character the character that consumes this consumable object
     */
    void consume(Character character);
}
