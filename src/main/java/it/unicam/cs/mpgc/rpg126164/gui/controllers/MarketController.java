package it.unicam.cs.mpgc.rpg126164.gui.controllers;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayableCharacter;
import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayerFighter;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.Item;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.WorldGame;
import it.unicam.cs.mpgc.rpg126164.gui.GameSession;
import it.unicam.cs.mpgc.rpg126164.services.MarketService;

import java.util.Map;

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
     * Shows the item stacks in the market's inventory
     * @return the item stacks in stock in the market
     */
    public Map<Item, ItemStack> showMarketItems() { return service.showMarketItems(this.getWorldGame().getMarket()); }

    /**
     * Shows the item stacks in the player's inventory
     * @param player the player of this session
     * @return the item stacks of the player
     */
    public Map<Item, ItemStack> showPlayerItems(PlayerFighter player) { return service.showPlayerItems(player); }

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
