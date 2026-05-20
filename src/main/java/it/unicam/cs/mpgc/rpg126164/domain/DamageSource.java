package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.AbstractItem;
import it.unicam.cs.mpgc.rpg126164.abstractions.Equipment;
import it.unicam.cs.mpgc.rpg126164.abstractions.Fighter;

/**
 * This class represents a damage source, especially weapons or spells. They're reusable multiple times in fights,
 * and it contains an additive of ATK to the fighter's base ATK. They're not cumulable, so they can't be stacked
 * in the inventory and their count is always 1.
 */
public class DamageSource extends AbstractItem implements Equipment {

    private final int ATK;

    /**
     * Creates a damage source, equippable by the players
     * @param name its name
     * @param description its description
     * @param maxAmount the maximum amount of this item that can be stacked in the inventory, must be 1 for this type of item
     * @param tradeValue its value in the market/shop
     * @param ATK the additive of ATK to the fighter's base ATK when equipped
     */
    public DamageSource(String name, String description, int maxAmount, int tradeValue, int ATK) {
        if (maxAmount != 1 || ATK <= 0) throw new IllegalArgumentException("Invalid parameters");

        super(name, description, maxAmount, tradeValue);
        this.ATK = ATK;
    }

    @Override
    public int useEquipment() {
        return this.getATK();
    }


    // GETTERS AND SETTERS

    public int getATK() { return ATK; }
}
