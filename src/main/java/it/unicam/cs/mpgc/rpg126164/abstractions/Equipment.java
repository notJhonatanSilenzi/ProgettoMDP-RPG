package it.unicam.cs.mpgc.rpg126164.abstractions;

/**
 * This interface allows to any object that implements this interface to be equipped by any character, and also
 * to be used once or multiple times, depending on the type of equipment
 */
public interface Equipment {

    /**
     * This method allows the attacker to use its own current weapon to deal damage to the defender
     * @param target the character that is attacking
     */
    void useEquipment(Character target);
}
