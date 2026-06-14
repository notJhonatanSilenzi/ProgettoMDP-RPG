package it.unicam.cs.mpgc.rpg126164.gui.controllers;

import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.WorldGame;
import it.unicam.cs.mpgc.rpg126164.gui.GameSession;
import it.unicam.cs.mpgc.rpg126164.services.MarketService;

/**
 * This class works as a market controller for the market service
 */
public class MarketController {

    private final MarketService service;
    private final GameSession gameSession;

    /**
     * Creates a market controller
     * @param service the market service
     * @param gameSession the current game session
     */
    public MarketController(MarketService service, GameSession gameSession) {
        this.service = service;
        this.gameSession = gameSession;
    }

    /**
     * Buys the given item stack for the player
     * @param stack the item stack to buy
     */
    public void buyItem(ItemStack stack) { service.buyItem(this.getWorldGame().getMarket(), stack); }

    /**
     * Sells the given item stack for the player, to cash in money
     * @param stack the item stack to sell
     */
    public void sellItem(ItemStack stack) { service.sellItem(this.getWorldGame().getMarket(), stack); }

    /**
     * Makes the player go out of the market of this session
     */
    public void exit() { service.exitMarket(this.getWorldGame().getMarket()); }

    /**
     * Returns the world game of this session
     * @return the world game
     */
    public WorldGame getWorldGame() { return gameSession.getWorldGame(); }
}
