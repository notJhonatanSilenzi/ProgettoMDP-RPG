package it.unicam.cs.mpgc.rpg126164.abstractions;

import it.unicam.cs.mpgc.rpg126164.domain.PlayableCharacter;

/**
 * This interface represents a generic game action that a playable character or an enemy can actuate during a fight.
 * It can be the use of a consumable, or the use of its weapon to attack an enemy.
 */
public interface GameAction {

    /**
     * This method actuates this game action, in order to process a turn in a fight. It refers to the given Fight,
     * in order to guarantee the correct fighters involved
     */
    void execute(Fight fight);

    /**
     * Returns the playable character that is being used to play this game
     * @return the playable character involved
     */
    PlayableCharacter getPlayer();
}
