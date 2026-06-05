package it.unicam.cs.mpgc.rpg126164.persistance;

import it.unicam.cs.mpgc.rpg126164.domain.characters.Enemy;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.BaseLevel;
import jakarta.persistence.*;

import java.util.UUID;

/**
 * This class works as an association class for the db, giving information on the enemies to create, when
 * the given level will be initialized from the database
 */
@Entity
@Table(name = "level_enemy")
public class LevelEnemy {

    @Id
    private String id;

    @ManyToOne
    private BaseLevel level;

    @ManyToOne
    private Enemy enemy;

    @Column(name = "quantity")
    private int quantity;

    public LevelEnemy() {}

    /**
     * Creates a level-enemy association for the game
     * @param level the given level
     * @param enemy the given enemy
     * @param quantity the amount of the given enemy in this level
     */
    public LevelEnemy(BaseLevel level, Enemy enemy, int quantity) {
        if (level == null || enemy == null || quantity < 0)
            throw new IllegalArgumentException("Level, Enemy and quantity must be valid");

        this.id = UUID.randomUUID().toString();
        this.level = level;
        this.enemy = enemy;
        this.quantity = quantity;
    }


    // GETTERS

    public String getId() { return id; }

    public BaseLevel getLevel() { return level; }

    public Enemy getEnemy() { return enemy; }

    public int getQuantity() { return quantity; }
}
