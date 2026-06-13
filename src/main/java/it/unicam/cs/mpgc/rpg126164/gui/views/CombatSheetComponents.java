package it.unicam.cs.mpgc.rpg126164.gui.views;

import it.unicam.cs.mpgc.rpg126164.domain.characters.Fighter;
import javafx.scene.control.Label;

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
