package it.unicam.cs.mpgc.rpg126164.abstractions;

/**
 * This interface represents a generic Fight between characters that are able to fight with other fighting characters.
 * It contains the methods to process a single turn in the game, to verify the final result of the fight and also
 * to simulate the actions of attack and of consuming a consumable item
 */
public interface Fight {

    /**
     * This method processes a turn in the fight, given a specific action
     * @param gameAction the action chosen by the player/enemy
     */
    void processTurn(GameAction gameAction);

    /**
     * This method updates the state of the fight at the end of every turn. The fight ends if the player dies or
     * if all enemies die
     */
    void getFinalResult();

    /**
     * Simulates the attack action from the attacker to the selected target. It calculates the amount of damage
     * that the target receives, if he doesn't evade the attack
     * @param attacker the fighter that gives the damage
     * @param target the fighter that receives the damage
     */
    void attack(Fighter attacker, Fighter target);

    /**
     * Simulates the action of consuming a consumable item, applying the effects to the given character.
     * The target must be a fighter, which can be the player or an enemy
     * @param target the fighter that receives the effects of the potion
     * @param consumable the consumable item to consume
     */
    void consumeItem(Fighter target, Consumable consumable);
}
