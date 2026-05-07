package it.unicam.cs.mpgc.rpg126164.abstractions;

/**
 * This interface allows to any character that implements this interface to fight with other characters able to
 * fight, allowing to cause or receive damages with other characters
 */
public interface Fighter {

    // TODO DA RAGIONARCI MEGLIO

    /**
     * This method allows to this character to cause damages to other characters able to fight
     */
    void attack();

    /**
     * This method allows to this character to receive damages from other characters able to fight
     */
    void getDamage();

    /**
     * This method checks if this character is still alive or not
     *
     * @return true if it's still alive, false otherwise
     */
    boolean isAlive();
}
