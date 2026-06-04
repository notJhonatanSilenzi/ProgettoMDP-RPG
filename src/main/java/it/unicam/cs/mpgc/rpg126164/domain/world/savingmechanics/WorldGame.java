package it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayableCharacter;

public interface WorldGame {

    /**
     * This method allows the getPlayer to enter the game world, and start playing.
     * @param character the getPlayer that enters the game world
     */
    void enter(PlayableCharacter character);

    /**
     * This method allows the getPlayer to enter the adventure mode, where they can complete levels
     * and fight with the enemies, in order to gain prizes
     */
    void enterAdventure();

    /**
     * This method allows the getPlayer to enter the market, where they can buy and sell items,
     * in order to obtain unique items and cash in money
     */
    void enterMarket();

    /**
     * Saves the current state of the game, creating a GameState DTO
     */
    void save();

    /**
     * This method allows to exit the game and return to the home page
     */
    void exit();
}
