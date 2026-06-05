package it.unicam.cs.mpgc.rpg126164.domain.gamemechanics;

import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.Potion;
import jakarta.persistence.*;

import java.util.UUID;

/**
 * This class works as a level-prize association for the database, in order to recreate the correct
 * item stack of prizes
 */
@Entity
@Table(name = "level_prizes")
public class LevelPrize {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @ManyToOne(optional = false)
    private BaseLevel level;

    @ManyToOne
    private Potion prize;

    @Column(nullable = false)
    private int quantity;

    public LevelPrize() {}

    /**
     * Creates a level-prize association
     * @param level the level
     * @param potion the prize
     * @param quantity the prize's quantity
     */
    public LevelPrize(BaseLevel level, Potion potion, int quantity) {
        if (level == null || potion == null || quantity <= 0)
            throw new IllegalArgumentException("Invalid parameters for LevelPrize");

        this.id = UUID.randomUUID().toString();
        this.level = level;
        this.prize = potion;
        this.quantity = quantity;
    }


    // GETTERS

    public String getId() { return id; }

    public BaseLevel getLevel() { return level; }

    public Potion getPrize() { return prize; }

    public int getQuantity() { return quantity; }
}
