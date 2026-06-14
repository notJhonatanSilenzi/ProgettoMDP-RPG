package it.unicam.cs.mpgc.rpg126164.gui.views.utilities;

import it.unicam.cs.mpgc.rpg126164.domain.characters.Fighter;
import javafx.scene.control.Label;

/**
 * This record works as a container of labels to maintain updated through combat turns, in order to
 * show the correct last info about the player and the enemies
 * @param name the label name
 * @param description the label description
 * @param archetype the label archetype
 * @param hp the label HP
 * @param atk the label ATK
 * @param df the label DF
 * @param evade the label evade chance
 * @param weapon the label weapon
 */
public record CombatSheetComponents(
        Label name,
        Label description,
        Label archetype,
        Label hp,
        Label atk,
        Label df,
        Label evade,
        Label weapon
) {
    /**
     * Updates the labels with the info of the given fighter
     * @param fighter the fighter to get info from
     */
    public void updateLabels(Fighter fighter) {
        name.setText("Name: "+ fighter.getName());
        description.setText("Description: " + fighter.getDescription());
        archetype.setText("Archetype: " + fighter.getArchetype());
        hp.setText("HP: " + fighter.getSheet().getHP());
        atk.setText("ATK: " + fighter.getSheet().getATK());
        df.setText("DF: " + fighter.getSheet().getDF());
        evade.setText("Evade Chance: " + fighter.getSheet().getEvadeChance());
        weapon.setText("Equipment: " + fighter.getCurrentEquipment().getName() + " (" +
                fighter.getCurrentEquipment().useEquipment() + " ATK)");
    }
}
