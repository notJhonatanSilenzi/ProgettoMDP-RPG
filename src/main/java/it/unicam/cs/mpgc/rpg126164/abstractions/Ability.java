package it.unicam.cs.mpgc.rpg126164.abstractions;

/**
 * This interface allows any object that implements this interface to apply particular effects during fights
 * between characters that are able to fight
 */
public interface Ability {

    // TODO - OCCHIO ALLE ABILITA' SU SE STESSI O SU TUTTI, O SU UNO E UNO
    /**
     * This method allows to a character to apply special effects to itself, to the enemy or to both
     * @param attacker the character that is applying the ability
     * @param defender the character that is getting disadvantage from the ability
     */
    void applyEffect(Character attacker, Character defender);
}
