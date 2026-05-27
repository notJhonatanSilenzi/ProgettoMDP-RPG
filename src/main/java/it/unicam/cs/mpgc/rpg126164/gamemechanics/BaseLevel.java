package it.unicam.cs.mpgc.rpg126164.gamemechanics;

import it.unicam.cs.mpgc.rpg126164.characters.PlayableCharacter;
import it.unicam.cs.mpgc.rpg126164.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.gamemechanics.combat.Fight;
import it.unicam.cs.mpgc.rpg126164.gamemechanics.combat.FightResult;
import it.unicam.cs.mpgc.rpg126164.gamemechanics.combat.GameAction;

/**
 * This class represents a generic level in the world game. It contains a reference to the fight to complete in order
 * to move onto the next level, and also a price and a flag to assert the level is completed.
 */
public class BaseLevel implements Level {

    private final Fight fight;
    private final ItemStack price;
    private boolean completed;

    /**
     * Creates a basic level in the world game
     * @param fight the fight that the player must complete to continue in the game
     * @param price the items that the player collects if he wins the fight
     */
    public BaseLevel(Fight fight, ItemStack price) {
        if  (fight == null || price == null) throw new IllegalArgumentException("Invalid parameters");

        this.fight = fight;
        this.price = price;
        this.completed = false;
    }

    @Override
    public void startLevel() {
        fight.reset();
        fight.startFight();
    }

    @Override
    public void processTurn(GameAction gameAction) {
        if (gameAction == null) throw new IllegalArgumentException("Invalid arguments");

        fight.processTurn(gameAction);
        checkLevelStatus(gameAction);
    }

    /**
     * Checks if the fight has ended. In particular:
     * - it gives the price to the player, if they won the fight, and signs the level as completed
     * - it resets the level if the player lost the fight
     * - otherwise it doesn't do anything
     * @param gameAction the game action that the player has done in this turn
     */
    private void checkLevelStatus(GameAction gameAction) {
        if (playerHasWon()) {
            givePriceToPlayer(gameAction.getPlayer());
            completed = true;
        }
        else if (playerHasLost()) this.reset();
    }

    @Override
    public boolean playerHasWon() { return fight.getFinalResult() == FightResult.WIN; }

    @Override
    public boolean playerHasLost() { return fight.getFinalResult() == FightResult.LOSE; }

    @Override
    public void reset() {
        fight.reset();
        completed = false;
    }

    /**
     * Gives a price to the player, if they completed the level by winning the fight
     * @param player the player that receives the price
     */
    private void givePriceToPlayer(PlayableCharacter player) {
        if (player == null)
            throw new IllegalArgumentException("Invalid parameters");

        if (playerHasWon()) player.getInventory().collect(price);
    }


    // GETTERS

    public Fight getFight() { return fight; }

    public ItemStack getPrice() { return price; }

    public boolean isCompleted() { return completed; }
}
