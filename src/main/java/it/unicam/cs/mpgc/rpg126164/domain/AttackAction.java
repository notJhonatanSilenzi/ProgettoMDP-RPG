package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.Fight;
import it.unicam.cs.mpgc.rpg126164.abstractions.Fighter;
import it.unicam.cs.mpgc.rpg126164.abstractions.GameAction;

/**
 * This class represents the action of attacking an enemy character for the player, or to attack the player for
 * the enemy. It implements the GameAction interface, and it contains the attacker and the target.
 * The execution doesn't calculate the damage, it must be calculated by another object
 */
public class AttackAction implements GameAction {

    private final Fighter attacker;
    private final Fighter target;

    /**
     * Creates an attack action to execute to process a turn during a fight
     * @param attacker the attacker
     * @param target the target
     */
    public AttackAction (Fighter attacker, Fighter target) {
        if (attacker == null || target == null || attacker == target)
            throw new IllegalArgumentException("Invalid arguments");

        this.attacker = attacker;
        this.target = target;
    }

    @Override
    public void execute(Fight fight) {
        if (fight == null)
            throw new IllegalArgumentException("Invalid argument");

        fight.attack(attacker, target);
    }


    // GETTERS

    public Fighter getAttacker() { return attacker; }

    public Fighter getTarget() { return target; }
}
