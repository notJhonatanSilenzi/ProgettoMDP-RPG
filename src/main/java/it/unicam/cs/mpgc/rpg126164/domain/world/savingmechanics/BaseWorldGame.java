package it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayableCharacter;
import it.unicam.cs.mpgc.rpg126164.domain.world.gameplay.LevelManager;
import it.unicam.cs.mpgc.rpg126164.domain.world.gameplay.Market;

/**
 * This class represents a basic implementation of a world game, and it manages the getPlayer's character,
 * the level manager, and the market. It provides methods to enter the game, enter an adventure,
 * enter the market, and exit the game. The class ensures that the getPlayer can only enter the game
 * if they are not already in it, and that they can only enter an adventure or the market if they
 * are currently in the game.
 */
public class BaseWorldGame implements WorldGame {

    private PlayableCharacter player;
    private final LevelManager levelManager;
    private final Market market;
    private final SaveManager saveManager;

    /**
     * Creates a basic world game for this game
     * @param levelManager the level manager to be used in this game
     * @param market the market to be used in this game
     * @param saveManager the save manager to be used in this game
     */
    public BaseWorldGame(LevelManager levelManager, Market market, SaveManager saveManager) {
        this.player = null;
        this.levelManager = levelManager;
        this.market = market;
        this.saveManager = saveManager;
    }

    @Override
    public void enter(PlayableCharacter character) {
        if (character == null) throw new IllegalArgumentException("Game already started");
        if (this.player != null) throw new IllegalStateException("Game already started");

        this.player = character;
    }

    @Override
    public void enterAdventure() {
        if (this.player == null) throw new IllegalArgumentException("Game not started");

        this.levelManager.enter(player);
    }

    @Override
    public void enterMarket() {
        if (this.player == null) throw new IllegalArgumentException("Game not started");;

        this.market.enter(player);
    }

    @Override
    public void save() {
        if (this.player == null) throw new IllegalArgumentException("Game not started");

        this.saveManager.save(
                new GameState(
                        this.player,
                        this.levelManager.getCurrentLevel().getId(),
                        this.levelManager.getProgressionPercentage(),
                        this.market.getWarehouse()
                )
        );
    }

    @Override
    public void exit() {
        if (this.player == null) throw new IllegalArgumentException("Game not started");

        this.player = null;
    }


    // GETTERS

    public PlayableCharacter getPlayer() { return player; }

    public LevelManager getLevelManager() { return levelManager; }

    public Market getMarket() { return market; }

    public SaveManager getSaveManager() { return saveManager; }
}
