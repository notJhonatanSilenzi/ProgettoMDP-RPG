package it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.combat;

import it.unicam.cs.mpgc.rpg126164.domain.characters.Fighter;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.Consumable;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.equipment.Equipment;
import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayableCharacter;

/**
 * This interface represents a generic Fight between characters that are able to fight with other fighting characters.
 * It contains the methods  to verify the final result of the fight and also
 * to simulate the actions of attack, consuming a consumable item or equipping an equipable item.
 * It must be started to process turns
 */
public interface Fight {

    /**
     * This method starts the fight, allowing to process turns.
     */
    void startFight();

    /**
     * This method updates the state of the fight at the end of every turn. The fight ends if the getPlayer dies or
     * if all enemies die
     * @return the current status of this fight
     */
    FightResult getFinalResult();

    /**
     * Simulates the attack action from the player to the selected target. It calculates the amount of damage
     * that the target receives, if he doesn't evade the attack
     * @param index the index of the enemy to attack
     */
    void playerAttackEnemy(int index);

    /**
     * Simulates the counterattack action of an enemy towards the player
     * @param index the index of the enemy that counterattacks
     */
    void enemyCounterAttack(int index);

    /**
     * Simulates the action of consuming a consumable item, applying the effects to the given character.
     * The target must be a fighter, which can be the getPlayer or an enemy
     * @param index the index ot the target that receives the effects of the potion. If it's below zero,
     *              the player receives the effects.
     * @param consumable the consumable item to consume
     */
    void consumeItem(int index, Consumable consumable);

    /**
     * Simulates the action of equipping an equipable item. This action doesn't waste turns in the fight
     * @param player the getPlayer that equips the item
     * @param equipment the item to equip
     */
    void equipItem(PlayableCharacter player, Equipment equipment);

    /**
     * Resets the current fight, means the getPlayer stats and enemies' stats get reset to the initial values,
     * and prepares the fight to get started again.
     */
    void reset();
}
