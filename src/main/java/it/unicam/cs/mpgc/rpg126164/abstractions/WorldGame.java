package it.unicam.cs.mpgc.rpg126164.abstractions;

import it.unicam.cs.mpgc.rpg126164.domain.PlayableCharacter;

public interface WorldGame {

    /**
     * This method allows the player to enter the game world, and start playing.
     * @param character the player that enters the game world
     */
    void enter(PlayableCharacter character);

    /**
     * This method allows the player to enter the adventure mode, where they can complete levels
     * and fight with the enemies, in order to gain prizes
     * @param character the player that enters the adventure mode
     */
    void enterAdventure(PlayableCharacter character);

    /**
     * This method allows the player to enter the market, where they can buy and sell items,
     * in order to obtain unique items and cash in money
     * @param character the player that enters the market
     */
    void enterMarket(PlayableCharacter character);

    /**
     * This method allows to exit the game and return to the home page
     */
    void exit();
}
