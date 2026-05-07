package it.unicam.cs.mpgc.rpg126164.abstractions;

/**
 * This interface allows to any object that implements this interface to be used as a save slot for the game,
 * in order to maintain the progress in the game, so that you can complete the game in multiple sections
 */
public interface SaveSlot {

    /**
     * This method allows to load the game, given a save slot
     */
    void load();

    /**
     * This method allows to upload the current game status into a save slot, given the index
     * <p>
     * NOTE: any uploading process eliminates the previous progresses in the considered save slot, if present. If
     * you want to maintain more versions of the game, you should use more save slots
     */
    void upload();

    /**
     * This method allows to cancel all the progresses in this save slot
     */
    void delete();
}
