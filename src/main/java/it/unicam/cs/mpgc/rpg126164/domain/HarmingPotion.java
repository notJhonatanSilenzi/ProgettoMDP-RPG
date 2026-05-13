package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.Character;
import it.unicam.cs.mpgc.rpg126164.abstractions.Consumable;
import it.unicam.cs.mpgc.rpg126164.abstractions.Item;

/**
 * This class represents a harming potion, which causes damage directly on the hp of the enemy, without taking in
 * count the defense of the target
 */
public class HarmingPotion extends Item implements Consumable {

    private final int HPdamage;

    /**
     * Creates a harming potion
     * @param name its name
     * @param description its description
     * @param maxAmount its max amount
     * @param HPdamage its
     */
    public HarmingPotion(String name, String  description, int maxAmount, int HPdamage) {
        if (HPdamage <= 0) throw new IllegalArgumentException("HP damage must be greater than 0");

        super(name, description, maxAmount);
        this.HPdamage = HPdamage;
    }

    @Override
    public void consume(Character character) {
        // TODO - Deve ridurre il numero di HP del bersaglio
    }
}
