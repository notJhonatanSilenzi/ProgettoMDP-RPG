package it.unicam.cs.mpgc.rpg126164.abstractions;

/**
 * This class represents a generic character which is in the world game. It can't be initialized, it requires
 * more specifications to be initialized.
 */
public abstract class Character {

    private String name;

    /**
     * Constructor that initializes a generic character with an id and a name
     * @param name the character's name
     * @throws IllegalArgumentException if id or name are null
     */
    public Character(String name) {
        if (name == null)
            throw new IllegalArgumentException("Name and id can't be null");

        this.name = name;
    }

    // Getters

    public String getName() { return this.name; }
}
