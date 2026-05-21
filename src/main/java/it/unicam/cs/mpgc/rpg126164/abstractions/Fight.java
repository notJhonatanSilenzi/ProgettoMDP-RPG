package it.unicam.cs.mpgc.rpg126164.abstractions;

import it.unicam.cs.mpgc.rpg126164.domain.FightResult;
import it.unicam.cs.mpgc.rpg126164.domain.PlayableCharacter;

/**
 * This interface represents a generic Fight between characters that are able to fight with other fighting characters.
 * It contains the methods to process a single turn in the game, to verify the final result of the fight and also
 * to simulate the actions of attack and of consuming a consumable item. It must be started to process turns
 */
public interface Fight {

    /**
     * This method starts the fight, allowing to process turns.
     */
    void startFight();

    /**
     * This method processes a turn in the fight, given a specific action
     * @param gameAction the action chosen by the player/enemy
     */
    void processTurn(GameAction gameAction);

    /**
     * This method updates the state of the fight at the end of every turn. The fight ends if the player dies or
     * if all enemies die
     * @return the current status of this fight
     */
    FightResult getFinalResult();

    /**
     * Simulates the attack action from the attacker to the selected target. It calculates the amount of damage
     * that the target receives, if he doesn't evade the attack
     * @param attacker the fighter that gives the damage
     * @param target the fighter that receives the damage
     */
    void attack(PlayableCharacter attacker, Fighter target);

    /**
     * Simulates the action of consuming a consumable item, applying the effects to the given character.
     * The target must be a fighter, which can be the player or an enemy
     * @param target the fighter that receives the effects of the potion
     * @param consumable the consumable item to consume
     */
    void consumeItem(Fighter target, Consumable consumable);

    /**
     * Resets the current fight, means the player stats and enemies' stats get reset to the initial values,
     * and prepares the fight to get started again.
     */
    void reset();
}
