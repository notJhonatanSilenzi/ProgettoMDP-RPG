package it.unicam.cs.mpgc.rpg126164.gui.views;

import it.unicam.cs.mpgc.rpg126164.gui.controllers.CombatController;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.LevelController;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.Label;

public class LevelCombatView {

    private final LevelController levelController;
    private final CombatController combatController;
    private final Runnable onVictory;
    private final Runnable onLoss;

    public LevelCombatView(LevelController levelController, CombatController combatController, Runnable onVictory, Runnable onLoss) {
        this.levelController = levelController;
        this.combatController = combatController;
        this.onVictory = onVictory;
        this.onLoss = onLoss;
    }

    public Scene createScene(Stage stage) {
        // TITLE TODO
        Label title = new Label(this.levelController.getWorldGame().getLevelManager().getCurrentLevel().getName());
        return new Scene(title,300, 300);
    }
}
