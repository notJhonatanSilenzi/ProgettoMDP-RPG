package it.unicam.cs.mpgc.rpg126164.services;

import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.domain.world.gameplay.Market;

/**
 * This class works as a service for the economy in this game, managing buying and selling from the
 * market, and showing the inventories of both the market and the player
 */
public class MarketService {

    /**
     * Buys the given item stack for the player, so it sells it for the market
     * @param market the market
     * @param itemStack the item stack bought by the player
     * @throws IllegalArgumentException if any of the parameters is null
     */
    public void buyItem(Market market, ItemStack itemStack) {
        if (market == null || itemStack == null) throw new IllegalArgumentException("Null parameters");

        market.buy(itemStack);
    }

    /**
     * Sells the given item stack for the player, so it buys it for the market
     * @param market the market
     * @param itemStack the item stack sold by the player
     * @throws IllegalArgumentException if any of the parameters is null
     */
    public void sellItem(Market market, ItemStack itemStack) {
        if (market == null || itemStack == null) throw new IllegalArgumentException("Null parameters");

        market.sell(itemStack);
    }

    /**
     * Makes the player exit from the market, and making it return to the world game hub
     * @param market the market
     * @throws IllegalArgumentException if the market is null
     */
    public void exitMarket(Market market) {
        if (market == null) throw new IllegalArgumentException("Null parameters");

        market.exit();
    }
}
