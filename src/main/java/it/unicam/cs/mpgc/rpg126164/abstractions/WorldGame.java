package it.unicam.cs.mpgc.rpg126164.abstractions;

import it.unicam.cs.mpgc.rpg126164.domain.Position;

public interface WorldGame {

    /**
     * This method allows the player to enter the game world, and start playing.
     *
     * @param index the index of the save slot to load, if any. if there are no occupied save slots, the system
     * directly starts a new game
     */
    void enter(int index);

    /**
     * this method allows the player to save the game given the save slot index. every saving process overwrites
     * the previous save in the same slot, if any.
     */
    void save(int index);

    /**
     * This method allows to exit the game and return to the home page
     */
    void exit();

    /**
     * This method checks if the given position is reachable by the player character
     *
     * @param position the position to check
     * @return true id the character can reach the given position, false otherwise
     */
    boolean isReachable(Position position);

    // TODO MANCA PER LA PERCENTUALE DI PROGRESSO DEL GIOCO, SERVONO LE MECCANICHE PRIMA
}
