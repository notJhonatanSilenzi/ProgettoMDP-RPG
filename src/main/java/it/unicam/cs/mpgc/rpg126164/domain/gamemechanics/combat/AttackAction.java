package it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.combat;

import it.unicam.cs.mpgc.rpg126164.domain.characters.Fighter;
import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayableCharacter;

/**
 * This class represents the action of attacking an enemy character for the player, or to attack the player for
 * the enemy. It implements the GameAction interface, and it contains the attacker and the target.
 * The execution doesn't calculate the damage, it must be calculated by another object
 */
public class AttackAction implements GameAction {

    private final PlayableCharacter player;
    private final Fighter target;

    /**
     * Creates an attack action to execute to process a turn during a fight
     * @param player the player
     * @param target the target
     */
    public AttackAction (PlayableCharacter player, Fighter target) {
        if (player == null || target == null || player == target)
            throw new IllegalArgumentException("Invalid arguments");

        this.player = player;
        this.target = target;
    }

    @Override
    public void execute(Fight fight) {
        if (fight == null)
            throw new IllegalArgumentException("Invalid argument");

        fight.attack(player, target);
    }


    // GETTERS

    @Override
    public PlayableCharacter getPlayer() { return player; }

    public Fighter getTarget() { return target; }
}
