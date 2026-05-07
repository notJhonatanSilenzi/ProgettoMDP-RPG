package it.unicam.cs.mpgc.rpg126164.abstractions;

/**
 * This interface allows to any object that implements this interface to be used as a map of the world game
 */
public interface WorldMap {

    /**
     * This method allows to open the map
     * <p></p>
     * NOTE: opening the map blocks the possibility of the player to move in the world game
     */
    void open();

    /**
     * This method allows to close the map
     */
    void close();

    /**
     * This method allows to update the map with the completed progresses and the current position of the player
     */
    void update();
}
