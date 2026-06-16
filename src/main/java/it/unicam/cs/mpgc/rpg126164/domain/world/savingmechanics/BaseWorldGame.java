package it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayerFighter;
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

    private PlayerFighter player;
    private final LevelManager levelManager;
    private final Market market;
    private final SaveManager saveManager;

    /**
     * Creates a basic world game for this game
     * @param levelManager the level manager to be used in this game
     * @param market the market to be used in this game
     * @param saveManager the save manager to be used in this game
     * @throws IllegalArgumentException if any of the parameters is null
     */
    public BaseWorldGame(LevelManager levelManager, Market market, SaveManager saveManager) {
        if (levelManager == null ||  market == null || saveManager == null)
            throw new IllegalArgumentException("Arguments cannot be null");

        this.player = null;
        this.levelManager = levelManager;
        this.market = market;
        this.saveManager = saveManager;
    }

    @Override
    public void enter(PlayerFighter character) {
        if (character == null) throw new IllegalArgumentException("Game already started");
        if (this.player != null) throw new IllegalStateException("Game already started");

        this.player = character;
    }

    @Override
    public void enterMarket() {
        if (this.player == null) throw new IllegalArgumentException("Game not started");

        this.market.enter(player);
    }

    @Override
    public void save() {
        if (this.player == null) throw new IllegalArgumentException("Game not started");

        int levelIndexToSave = this.levelManager.getCurrentLevelIndex();
        if (this.levelManager.getCurrentLevel().isCompleted() && !this.levelManager.isLastLevel())
            levelIndexToSave++;

        this.saveManager.save(
                new GameState(
                        this.player,
                        this.levelManager.getCurrentLevel().getId(),
                        levelIndexToSave,
                        this.market.getWarehouse()
                )
        );
    }


    // GETTERS

    @Override
    public PlayerFighter getPlayer() { return player; }

    @Override
    public LevelManager getLevelManager() { return levelManager; }

    @Override
    public Market getMarket() { return market; }
}
