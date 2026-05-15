package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.Character;
import it.unicam.cs.mpgc.rpg126164.abstractions.CharacterSheet;
import it.unicam.cs.mpgc.rpg126164.abstractions.Equipment;
import it.unicam.cs.mpgc.rpg126164.abstractions.Fighter;

public class Enemy extends Character implements Fighter {

    private final Equipment equipment;
    private final CharacterSheet sheet;
    private final Archetype archetype;
    private final EnemyType type;

    public Enemy(String name, Equipment equipment, Archetype archetype, EnemyType type) {
        super(name);
        this.equipment = equipment;
        this.archetype = archetype;
        this.type = type;
        this.sheet = archetype.getSheet(type.getMultiplier());
    }

    @Override
    public CharacterSheet getSheet() { return this.sheet; }

    @Override
    public Archetype getArchetype() { return this.archetype; }

    @Override
    public Equipment getEquipment() { return this.equipment; }

    @Override
    public void setEquipment(Equipment equipment) { throw new RuntimeException("Enemies can't change their equipment"); }

    public EnemyType getEnemyType()  { return this.type; }
}
