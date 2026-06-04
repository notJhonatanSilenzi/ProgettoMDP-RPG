package it.unicam.cs.mpgc.rpg126164.services;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayableCharacter;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.Item;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.domain.world.gameplay.Market;

import java.util.Map;

public class MarketService {

    /**
     * Returns the collection of items currently in the warehouse, to show them to the player and
     * let him choose which one to buy
     * @param market the market
     * @return the collection of items in sale
     */
    public Map<Item, ItemStack> showMarketItems(Market market) { return market.getWarehouse().getItems(); }

    /**
     * Returns the collection of items currently in the player's inventory, to show them to the
     * player and let him choose which one to sell
     * @param player the player
     * @return the collection of items currently in the player's inventory
     */
    public Map<Item, ItemStack> showPlayerItems(PlayableCharacter player) { return player.getInventory().getItems(); }

    /**
     * Buys the given item stack for the player, so it sells it for the market
     * @param market the market
     * @param itemStack the item stack bought by the player
     */
    public void buyItem(Market market, ItemStack itemStack) { market.buy(itemStack); }

    /**
     * Sells the given item stack for the player, so it buys it for the market
     * @param market the market
     * @param itemStack the item stack sold by the player
     */
    public void sellItem(Market market, ItemStack itemStack) { market.sell(itemStack); }

    /**
     * Makes the player exit from the market, and making it return to the world game hub
     * @param market the market
     */
    public void exitMarket(Market market) { market.exit(); }
}
