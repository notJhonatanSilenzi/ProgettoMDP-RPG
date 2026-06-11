package it.unicam.cs.mpgc.rpg126164.domain.characters;

import it.unicam.cs.mpgc.rpg126164.domain.characters.stats.Archetype;
import it.unicam.cs.mpgc.rpg126164.domain.characters.stats.CharacterSheet;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.equipment.Equipment;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.equipment.Weapon;
import jakarta.persistence.*;

/**
 * This class represents an NPC character able to fight with the getPlayer, and it's similar to playable characters
 * in its representation. The difference is that it can't change weapon and doesn't have an inventory. There
 * are three types of enemies, and the type influences the stats in the sheet
 */
@Entity
@Table(name = "enemies")
public class Enemy extends Character implements EnemyFighter {

    @ManyToOne
    private Weapon equipment;

    @Transient
    private CharacterSheet sheet;

    @Column(name = "archetype", nullable = false)
    @Enumerated(EnumType.STRING)
    private Archetype archetype;

    @Column(name = "enemy_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EnemyType type;

    /**
     * Creates an enemy
     * @param name its name
     * @param description its description
     * @param equipment its weapon
     * @param archetype its archetype, which influences the sheet creation
     * @param type its type, which influences the amount of stats to adapt its strength
     */
    public Enemy(String name, String description, Weapon equipment, Archetype archetype, EnemyType type) {
        super(name, description);
        this.equipment = equipment;
        this.archetype = archetype;
        this.type = type;
        this.sheet = archetype.getSheet(type.getMultiplier());
    }

    public Enemy() { super(); }

    /**
     * Recreates the enemy sheet, after querying an enemy in the database through the repository in
     * a hibernate session
     */
    @PostLoad
    public void rebuildSheet() { this.sheet = archetype.getSheet(type.getMultiplier()); }


    // GETTERS AND SETTERS

    @Override
    public CharacterSheet getSheet() { return this.sheet; }

    @Override
    public Archetype getArchetype() { return this.archetype; }

    @Override
    public int getWeaponAttack() { return this.equipment.useEquipment(); }

    @Override
    public Equipment getCurrentEquipment() { return this.equipment; }

    @Override
    public EnemyType getEnemyType()  { return this.type; }

    @Override
    public int getGoldForDefeat() { return this.type.getGoldForDefeat(); }
}
