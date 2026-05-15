package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.Character;
import it.unicam.cs.mpgc.rpg126164.abstractions.Equipment;
import it.unicam.cs.mpgc.rpg126164.abstractions.Item;

/**
 * This class represents a damage source, especially weapons or spells. They're reusable multiple times in fights
 */
public class DamageSource extends Item implements Equipment {

    private final int ATK;

    public DamageSource(String name, String description, int maxAmount, int ATK) {
        if (maxAmount != 1) throw new IllegalArgumentException("Weapons are not cumulable");

        super(name, description, maxAmount);
        this.ATK = ATK;
    }

    @Override
    public void useEquipment(Character target) {
        // TODO - Deve essere l'item corrente nell'inventario
    }


    // GETTERS AND SETTERS

    public int getATK() { return ATK; }

    public void setCount(int count) {
        throw new IllegalArgumentException("Weapons are not cumulable, count cannot be changed");
    }
}
