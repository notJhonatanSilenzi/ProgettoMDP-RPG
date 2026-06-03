package it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions;

import it.unicam.cs.mpgc.rpg126164.domain.characters.Fighter;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.Item;

/**
 * This interface allows any object that implements this interface to be consumed, in order to apply specific
 * effects and advantages to the character that uses the consumable or to other characters. It can only be used
 * once. It offers also a method to return the type of target that receives the effects of the consumable object.
 */
public interface Consumable extends Item {

    /**
     * This method allows to consume this consumable object, applying the specific effects and advantages
     * to the target that uses it or to other characters. The target that consumes this item must be a
     * target able to fight
     * @param target the target that consumes this consumable object
     */
    void consume(Fighter target);

    /**
     * Returns the type of target that receives the effects of this consumable item
     * @return the type of target
     */
    PotionTargetType getTargetType();
}
