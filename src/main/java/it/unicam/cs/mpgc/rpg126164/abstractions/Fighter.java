package it.unicam.cs.mpgc.rpg126164.abstractions;

import it.unicam.cs.mpgc.rpg126164.domain.Archetype;

/**
 * This interface allows to any character that implements this interface to fight with other characters able to
 * fight, allowing to interact with the sheet, the archetype and the equipment of this character. This interface
 * is implemented by the playable characters and the enemies
 */
public interface Fighter {

    /**
     * Returns the sheet of this fighter
     * @return the sheet of this fighter
     */
    CharacterSheet getSheet();

    /**
     * Returns the archetype of this fighter
     * @return the archetype of this fighter
     */
    Archetype getArchetype();

    /**
     * Returns the current Equipment of this fighter
     * @return the current Equipment of this fighter
     */
    Equipment getEquipment();

    /**
     * Allows to change the current equipment in usage
     * @param equipment the equipment to equip
     */
    void setEquipment(Equipment equipment);
}
