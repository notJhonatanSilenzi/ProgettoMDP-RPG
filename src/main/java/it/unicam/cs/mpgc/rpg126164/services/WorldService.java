package it.unicam.cs.mpgc.rpg126164.services;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayableCharacter;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.Item;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.domain.inventory.Inventory;
import it.unicam.cs.mpgc.rpg126164.domain.inventory.InventoryBehaviour;
import it.unicam.cs.mpgc.rpg126164.domain.world.gameplay.BaseAdventure;
import it.unicam.cs.mpgc.rpg126164.domain.world.gameplay.Emporium;
import it.unicam.cs.mpgc.rpg126164.domain.world.gameplay.LevelManager;
import it.unicam.cs.mpgc.rpg126164.domain.world.gameplay.Market;
import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.BaseWorldGame;
import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.GameState;
import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.SaveManager;
import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.WorldGame;
import it.unicam.cs.mpgc.rpg126164.persistance.repositories.*;

import java.util.HashMap;
import java.util.Map;

public class WorldService {

    private final WeaponRepository weaponRepository;
    private final PotionRepository potionRepository;
    private final LevelRepository levelRepository;

    public WorldService(WeaponRepository weaponRepository, PotionRepository potionRepository,LevelRepository levelRepository) {
        this.weaponRepository = weaponRepository;
        this.potionRepository = potionRepository;
        this.levelRepository = levelRepository;
    }

    /**
     * Builds a world game for a new gameplay, with a new player character
     * @param saveManager the save slot
     * @param player the player's character
     * @return a new world game
     */
    public WorldGame buildNewWorldGame(SaveManager saveManager, PlayableCharacter player) {
        WorldGame worldGame = new BaseWorldGame(buildAdventure(), buildEmporium(), saveManager);
        worldGame.enter(player);
        return worldGame;
    }

    /**
     * Builds a world game, based on the last game state
     * @param saveManager the save slot
     * @param gameState the game state of the last session
     * @return the last stated world game
     */
    public WorldGame buildSavedWorldGame(SaveManager saveManager, GameState gameState) {
        LevelManager adventure = buildAdventure();
        adventure.setCurrentLevel(levelRepository.findById(gameState.currentLevelId()));
        WorldGame worldGame = new BaseWorldGame(adventure, buildEmporium(), saveManager);
        worldGame.enter(gameState.getPlayer());
        return worldGame;
    }

    /**
     * Builds a level manager for the adventure mode of the game
     * @return a level manager for the adventure mode
     */
    private LevelManager buildAdventure() { return new BaseAdventure(levelRepository.findAll()); }

    /**
     * Builds a market for the market mode
     * @return a market for the market mode
     */
    private Market buildEmporium() {
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
     * Makes the player enter into the market of the given world game
     * @param worldGame the world game
     */
    public void enterMarket(WorldGame worldGame) { worldGame.enterMarket(); }

    /**
     * Makes the player enter into the adventure mode of the given world game
     * @param worldGame the world game
     */
    public void enterAdventure(WorldGame worldGame) { worldGame.enterAdventure(); }
}
