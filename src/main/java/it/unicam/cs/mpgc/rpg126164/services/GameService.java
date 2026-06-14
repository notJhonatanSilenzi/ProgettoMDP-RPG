package it.unicam.cs.mpgc.rpg126164.services;

import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayableCharacter;
import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayerFighter;
import it.unicam.cs.mpgc.rpg126164.domain.characters.stats.Archetype;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.GameState;
import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.SaveManager;
import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.WorldGame;
import it.unicam.cs.mpgc.rpg126164.persistance.repositories.*;
import it.unicam.cs.mpgc.rpg126164.services.utils.PlayerBuilder;

/**
 * This class initializes a game service, the base handler for this game. It receives all the repositories
 */
public class GameService {

    private final SaveManager saveManager;
    private final PotionRepository potionRepository;
    private final WeaponRepository weaponRepository;

    /**
     * Creates a game service
     * @param saveManager the save slot
     * @param pr the potion repository
     * @param wr the weapon repository
     */
    public GameService(SaveManager saveManager, PotionRepository pr, WeaponRepository wr) {
        if (saveManager == null || pr == null || wr == null)
            throw new IllegalArgumentException("Invalid parameters");

        this.saveManager = saveManager;
        this.potionRepository = pr;
        this.weaponRepository = wr;
    }

    /**
     * Creates a new gameplay
     * @param name the name of the getPlayer's character
     * @param description its description
     * @param archetype its chosen archetype
     * @return the new player for the world game
     */
    public PlayerFighter createNewGame(String name, String description, Archetype archetype) {
        PlayerBuilder builder = new PlayerBuilder(potionRepository, weaponRepository);
        PlayerFighter player = new PlayableCharacter(
                name,
                description,
                archetype,
                builder.buildInventory(archetype),
                builder.buildEquipmentManager(archetype),
                builder.buildWallet()
        );
        player.getInventory().collect(new ItemStack(player.getCurrentEquipment(), 1));
        return player;
    }

    /**
     * Creates a gameplay, based on the saved data in the save slot
     * @return the last saved world game, according to the loaded game state
     */
    public GameState loadGame() { return saveManager.load(); }

    /**
     * Saves the current game state
     * @param worldGame the world game to save
     */
    public void saveGame(WorldGame worldGame) { worldGame.save(); }

    /**
     * Deletes the game state saved in the last game session
     */
    public void deleteSaveSlot() { saveManager.delete(); }
}

