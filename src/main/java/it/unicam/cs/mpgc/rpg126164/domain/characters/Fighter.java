package it.unicam.cs.mpgc.rpg126164.domain.characters;

import it.unicam.cs.mpgc.rpg126164.domain.characters.stats.Archetype;
import it.unicam.cs.mpgc.rpg126164.domain.characters.stats.CharacterSheet;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.equipment.Equipment;

/**
 * This interface allows to any character that implements this interface to fight with other characters able to
 * fight, allowing to interact with the sheet and the archetype. This interface is implemented by the playable
 * characters and the enemies, and it allows access to the bonus attacks given by the current equipment
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
     * Returns the additive attack, based on the current weapon
     * @return the bonus attack given by the current equipable item
     */
    int getWeaponAttack();

    /**
     * Returns the current equipment of this fighter
     * @return the current equipment
     */
    Equipment getCurrentEquipment();

    /**
     * Returns the name of this fighter
     * @return the name of this fighter
     */
    String getName();

    /**
     * Returns the description of this fighter
     * @return the description of this fighter
     */
    String getDescription();
}
