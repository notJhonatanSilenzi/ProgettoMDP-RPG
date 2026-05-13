package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.Character;
import it.unicam.cs.mpgc.rpg126164.abstractions.Consumable;
import it.unicam.cs.mpgc.rpg126164.abstractions.Item;

/**
 * This class represents a healing potion, an item that can increase the amount of HP of a character
 */
public class HealingPotion extends Item implements Consumable {

    private final int HPincrease;

    /**
     * Creates a harming potion
     * @param name its name
     * @param description its description
     * @param maxAmount its max amount
     * @param HPincrease its
     */
    public HealingPotion(String name, String  description, int maxAmount, int HPincrease) {
        if (HPincrease <= 0)
            throw new IllegalArgumentException("Invalid HP increase value");

        super(name, description, maxAmount);
        this.HPincrease = HPincrease;
    }

    @Override
    public void consume(Character character) {
        // TODO - Deve aumentare il numero di HP del personaggio
    }
}
