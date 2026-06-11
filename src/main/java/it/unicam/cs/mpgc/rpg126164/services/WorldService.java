package it.unicam.cs.mpgc.rpg126164.services;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayableCharacter;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.Item;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.Level;
import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.*;
import it.unicam.cs.mpgc.rpg126164.persistance.LevelPrize;
import it.unicam.cs.mpgc.rpg126164.domain.inventory.Inventory;
import it.unicam.cs.mpgc.rpg126164.domain.inventory.InventoryBehaviour;
import it.unicam.cs.mpgc.rpg126164.domain.world.gameplay.BaseAdventure;
import it.unicam.cs.mpgc.rpg126164.domain.world.gameplay.Emporium;
import it.unicam.cs.mpgc.rpg126164.domain.world.gameplay.LevelManager;
import it.unicam.cs.mpgc.rpg126164.domain.world.gameplay.Market;
import it.unicam.cs.mpgc.rpg126164.persistance.repositories.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class works as a service for the world game, and it includes operations like building the
 * world game, and also moving into the various game modes
 */
public class WorldService {

    private final WeaponRepository weaponRepository;
    private final PotionRepository potionRepository;
    private final LevelRepository levelRepository;
    private final LevelPrizeRepository levelPrizeRepository;

    public WorldService(WeaponRepository weaponRepository, PotionRepository potionRepository,LevelRepository levelRepository, LevelPrizeRepository levelPrizeRepository) {
        this.weaponRepository = weaponRepository;
        this.potionRepository = potionRepository;
        this.levelRepository = levelRepository;
        this.levelPrizeRepository = levelPrizeRepository;
    }

    /**
     * Builds a world game for a new gameplay, with a new player character
     * @param player the player's character
     * @return a new world game
     */
    public WorldGame buildNewWorldGame(PlayableCharacter player) {
        WorldGame worldGame = new BaseWorldGame(buildAdventure(), buildNewEmporium(), new SaveSlot());
        worldGame.enter(player);
        return worldGame;
    }

    /**
     * Builds a world game, based on the last game state
     * @param gameState the game state of the last session
     * @return the last stated world game
     */
    public WorldGame buildSavedWorldGame(GameState gameState) {
        LevelManager adventure = buildAdventure();
        adventure.setCurrentLevel(gameState.currentLevelIndex());
        WorldGame worldGame = new BaseWorldGame(adventure, buildSavedEmporium(gameState), new SaveSlot());
        gameState.player().getSheet().reset();
        worldGame.enter(gameState.player());
        return worldGame;
    }

    /**
     * Builds a level manager for the adventure mode of the game
     * @return a level manager for the adventure mode
     */
    private LevelManager buildAdventure() {
        List<Level> levels = levelRepository.findAll();
        LevelManager adventure = new BaseAdventure(levels);
        for (Level level : levels) {
            LevelPrize entry = levelPrizeRepository.findByLevel(level);
            if (entry != null) level.setPrize(new ItemStack(entry.getPrize(), entry.getQuantity()));
        }
        return adventure;
    }

    /**
     * Builds a new market for the market mode
     * @return a new market for the market mode
     */
    private Market buildNewEmporium() {
        Map<Item, ItemStack> emporiumItems = new HashMap<>();
        emporiumItems.putAll(weaponRepository.findAll()
                .stream().collect(
                        HashMap::new, (m, v)
                                -> m.put(v, new ItemStack(v, v.getMaxAmount())), HashMap::putAll));
        emporiumItems.putAll(potionRepository.findAll()
                .stream().collect(
                        HashMap::new, (m, v)
                                -> m.put(v, new ItemStack(v, v.getMaxAmount())), HashMap::putAll));
        InventoryBehaviour emporiumInv = new Inventory(emporiumItems);
        return new Emporium(emporiumInv);
    }

    /**
     * Builds an emporium, according to the last saved game state
     * @param gameState the last game state saved
     * @return the last saved emporium
     */
    private Market buildSavedEmporium(GameState gameState) { return new Emporium(gameState.emporiumInventory()); }

    /**
     * Makes the player enter into the market of the given world game
     * @param worldGame the world game
     */
    public void enterMarket(WorldGame worldGame) { worldGame.enterMarket(); }

    /**
     * Closes the current game play
     * @param worldGame the world to close
     */
    public void exit(WorldGame worldGame) { worldGame.exit(); }
}
