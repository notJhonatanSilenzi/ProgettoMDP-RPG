package it.unicam.cs.mpgc.rpg126164.gui.views;

import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;

/**
 * This record works as a record for the result context, after the ending of a fight
 * @param type the type of result to show
 * @param title the title
 * @param description the description of the result
 * @param prize the prize to claim
 */
public record ResultContext(
        ResultView type,
        String title,
        String description,
        ItemStack prize
) {
}
