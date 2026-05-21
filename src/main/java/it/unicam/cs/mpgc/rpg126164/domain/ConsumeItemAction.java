package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.Consumable;
import it.unicam.cs.mpgc.rpg126164.abstractions.Fight;
import it.unicam.cs.mpgc.rpg126164.abstractions.Fighter;
import it.unicam.cs.mpgc.rpg126164.abstractions.GameAction;

/**
 * This class represents the action of consuming a potion, taking in count that:
 * - players cannot receive harming, attack debuffs or weakness effects
 * - enemies cannot receive healing, defense buffs or attack buffs effects
 * It implements the GameAction interface, and it contains the target Fighter and the item to consume
 */
public class ConsumeItemAction implements GameAction {

    private final PlayableCharacter player;
    private final Fighter target;
    private final Consumable consumable;

    /**
     * Creates a consume potion action to be applied in a turn, during a fight
     * @param player the player involved in this action
     * @param target the fighter that receives the effects of the potion
     * @param consumable the potion to consume
     * @throws IllegalArgumentException if:
     *   - player, target or consumable are null
     *   - a healing potion is tried to be consumed by an enemy
     *   - a harming potion is tried to be applied to a player
     */
    public ConsumeItemAction(PlayableCharacter player, Fighter target, Consumable consumable) {
        if (player == null || target == null || consumable == null)
            throw new IllegalArgumentException("Null parameters");

        this.player = player;
        this.target = target;
        this.consumable = consumable;
    }

    @Override
    public void execute(Fight fight) {
        if (fight == null)
            throw new IllegalArgumentException("Invalid argument");
        if (consumable.getTargetType().isValid(player, target))
            throw new IllegalArgumentException("Invalid target for this consumable");

        // Using the item causes the removing from the inventory
        fight.consumeItem(target, consumable);
        player.getInventory().drop(new ItemStack(consumable, 1));
    }


    // GETTERS

    public Fighter getTarget() { return target; }

    public Consumable getConsumable() { return consumable; }

    @Override
    public PlayableCharacter getPlayer() { return player; }
}
