package it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * This class works as an implementation of the SaveManager interface, and it saves the given game state
 * into a text file, through serialization
 */
public final class SaveSlot implements SaveManager {

    // The file where the game state is saved
    private static final Path SAVE_FILE = Path.of("save.dat");

    @Override
    public void save(GameState gameState) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(SAVE_FILE));
            out.writeObject(gameState);
            out.close();
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to save game state", e);
        }
    }

    @Override
    public GameState load() {
        if (!Files.exists(SAVE_FILE)) throw new IllegalStateException("No save file found");

        try {
            ObjectInputStream in = new ObjectInputStream(Files.newInputStream(SAVE_FILE));
            GameState gameState = (GameState) in.readObject();
            in.close();
            return gameState;
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete() {
        try {
            Files.deleteIfExists(SAVE_FILE);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete save file", e);
        }
    }
}
