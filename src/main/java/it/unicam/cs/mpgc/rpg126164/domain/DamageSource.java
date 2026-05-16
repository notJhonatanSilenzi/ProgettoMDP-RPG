package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.Equipment;
import it.unicam.cs.mpgc.rpg126164.abstractions.Fighter;
import it.unicam.cs.mpgc.rpg126164.abstractions.Item;

/**
 * This class represents a damage source, especially weapons or spells. They're reusable multiple times in fights,
 * and it contains an additive of ATK to the fighter's base ATK. They're not cumulable, so they can't be stacked
 * in the inventory and their count is always 1.
 */
public class DamageSource extends Item implements Equipment {

    private final int ATK;

    /**
     * Creates a damage source, equippable by the players
     * @param name its name
     * @param description its description
     * @param maxAmount its max amount, which has to be 1
     * @param tradeValue its value in the market/shop
     * @param ATK the additive of ATK to the fighter's base ATK when equipped
     */
    public DamageSource(String name, String description, int maxAmount, int tradeValue, int ATK) {
        if (maxAmount != 1) throw new IllegalArgumentException("Weapons are not cumulable");

        super(name, description, maxAmount, tradeValue);
        this.ATK = ATK;
    }

    @Override
    public void useEquipment(Fighter target) {
        // TODO - Deve essere l'item corrente nell'inventario
    }


    // GETTERS AND SETTERS

    public int getATK() { return ATK; }

    public void setCount(int count) {
        throw new IllegalArgumentException("Weapons are not cumulable, count cannot be changed");
    }
}
