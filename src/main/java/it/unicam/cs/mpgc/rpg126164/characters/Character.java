package it.unicam.cs.mpgc.rpg126164.characters;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

/**
 * This class represents a generic character which is in the world game. It can't be initialized, it requires
 * more specifications to be initialized.
 */
@MappedSuperclass
public abstract class Character {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @Column(name = "description",  nullable = false)
    private String description;

    public Character() {}

    /**
     * Constructor that initializes a generic character with an id and a name
     * @param name the character's name
     * @param description the character's description
     * @throws IllegalArgumentException if id or name are null
     */
    public Character(String name, String description) {
        if (name == null || description == null)
            throw new IllegalArgumentException("Name and description can't be null");

        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
    }

    /**
     * Checks if the given object is equal to this character, basing on id
     * @param obj   the reference object with which to compare.
     * @return true if the two objects are the same, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return Objects.equals(((Character) obj).getId(), this.id);
    }

    /**
     * Returns the hash code of this character, which is based on its id
     * @return the hash code of this character
     */
    @Override
    public int hashCode() { return Objects.hash(this.getId()); }

    // GETTERS

    public String getId() { return this.id; }

    public String getName() { return this.name; }

    public String getDescription() { return this.description; }
}
