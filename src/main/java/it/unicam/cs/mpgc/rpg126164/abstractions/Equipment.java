package it.unicam.cs.mpgc.rpg126164.abstractions;

/**
 * This interface allows to any object that implements this interface to be equipped by any character able to fight,
 * and also to be used once or multiple times, depending on the type of equipment
 */
public interface Equipment extends Item {

    /**
     * This method allows the attacker to use its own current weapon to deal damage to the defender. The target
     * must be a character able to fight, playable or not, and it only returns the additive ATK for the character
     * @return the additive ATK for the character that is using this equipment
     */
    int useEquipment();
}
