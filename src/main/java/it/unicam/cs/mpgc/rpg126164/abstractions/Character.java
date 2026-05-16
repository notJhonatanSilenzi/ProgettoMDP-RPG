package it.unicam.cs.mpgc.rpg126164.abstractions;

/**
 * This class represents a generic character which is in the world game. It can't be initialized, it requires
 * more specifications to be initialized.
 */
public abstract class Character {

    private String name;
    private String description;

    /**
     * Constructor that initializes a generic character with an id and a name
     * @param name the character's name
     * @param description the character's description
     * @throws IllegalArgumentException if id or name are null
     */
    public Character(String name, String description) {
        if (name == null || description == null)
            throw new IllegalArgumentException("Name and description can't be null");

        this.name = name;
        this.description = description;
    }

    // Getters

    public String getName() { return this.name; }

    public String getDescription() { return this.description; }
}
