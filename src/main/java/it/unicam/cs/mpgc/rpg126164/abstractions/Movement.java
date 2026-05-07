package it.unicam.cs.mpgc.rpg126164.abstractions;

import it.unicam.cs.mpgc.rpg126164.domain.Direction;
import it.unicam.cs.mpgc.rpg126164.domain.Position;

/**
 * This method allows to any character that implements this interface to move and change position in the world
 * game, into any allowed position
 */
public interface Movement {

    /**
     * This method returns the new position reached by this character
     *
     * @param direction the direction in which the character is moving
     * @return the new current position of the character
     */
    Position move(Direction direction);
}
