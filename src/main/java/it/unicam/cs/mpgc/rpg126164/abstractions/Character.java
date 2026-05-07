package it.unicam.cs.mpgc.rpg126164.abstractions;

/**
 * This class represents a generic character which is in the world game. It can't be initialized, it requires
 * more specifications to be initialized.
 */
public abstract class Character {

    private String id;
    private String name;

    /**
     * Constructor that initializes a generic character with an id and a name
     *
     * @param id the identifier of this character
     * @param name the character's name
     * @throws IllegalArgumentException if id or name are null
     */
    public Character(String id, String name) {
        if (id == null || name == null)
            throw new IllegalArgumentException("Name and id can't be null");

        this.id = id;
        this.name = name;
    }

    // Getters
    public String getId() { return this.id; }
    public String getName() { return this.name; }
}
