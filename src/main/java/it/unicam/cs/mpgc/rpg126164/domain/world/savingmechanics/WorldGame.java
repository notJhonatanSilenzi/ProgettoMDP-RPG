package it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayerFighter;
import it.unicam.cs.mpgc.rpg126164.domain.world.gameplay.LevelManager;
import it.unicam.cs.mpgc.rpg126164.domain.world.gameplay.Market;

public interface WorldGame {

    /**
     * This method allows the player to enter the game world, and start playing.
     * @param character the player that enters the game world
     * @throws IllegalArgumentException if the player is null
     * @throws IllegalStateException if the game already started
     */
    void enter(PlayerFighter character);

    /**
     * This method allows the player to enter the market, where they can buy and sell items,
     * in order to obtain unique items and cash in money
     * @throws IllegalStateException if the game hasn't started yet
     */
    void enterMarket();

    /**
     * Saves the current state of the game, creating a GameState DTO
     * @throws IllegalStateException if the game hasn't started yet
     */
    void save();

    /**
     * Returns the player's character in this world
     * @return the player's character
     */
    PlayerFighter getPlayer();

    /**
     * Returns the level manager of this world
     * @return the level manager
     */
    LevelManager getLevelManager();

    /**
     * Returns the market of this world
     * @return the market
     */
    Market getMarket();
}
