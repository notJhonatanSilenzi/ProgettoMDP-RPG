package it.unicam.cs.mpgc.rpg126164.gui.views;

import it.unicam.cs.mpgc.rpg126164.gui.controllers.CombatController;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.LevelController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class FightResultView {

    private final LevelController levelController;
    private final CombatController combatController;
    private final Runnable primaryAction;
    private final Runnable secondaryAction;
    private final ResultView resultView;

    public FightResultView(LevelController lc, CombatController cc, Runnable primaryAction, Runnable secondaryAction, ResultView resultView) {
        this.levelController = lc;
        this.combatController = cc;
        this.primaryAction = primaryAction;
        this.secondaryAction = secondaryAction;
        this.resultView = resultView;
    }

    public Scene createScene(Stage stage) {

        Label title = new Label();
        Label description = new Label();

        Button primaryButton = new Button();
        Button secondaryButton = new Button();
        System.out.println("======== VICTORY ========");
        System.out.println("Current level: " +
                levelController.getWorldGame()
                        .getLevelManager()
                        .getCurrentLevel()
                        .getName());

        System.out.println("Current index: " +
                levelController.getWorldGame()
                        .getLevelManager()
                        .getCurrentLevelIndex());

        System.out.println("isLastLevel: " +
                levelController.getWorldGame()
                        .getLevelManager()
                        .isLastLevel());
        switch(resultView) {

            case ResultView.LEVEL_COMPLETED -> {

                title.setText("LEVEL COMPLETED");
                description.setText("You defeated all enemies.\n\n");

                primaryButton.setText("Continue");
                secondaryButton.setText("Hub");
                primaryButton.setOnAction(_ -> {
                    primaryAction.run();
                });
                secondaryButton.setOnAction(_ -> {
                    secondaryAction.run();
                });
            }

            case ResultView.LEVEL_FAILED -> {

                title.setText("DEFEAT");

                description.setText("Your adventure ends here.");

                primaryButton.setText("Retry");
                secondaryButton.setText("Hub");

                primaryButton.setOnAction(_ -> {
                    combatController.restartLevel();
                    primaryAction.run();
                });
                secondaryButton.setOnAction(_ -> secondaryAction.run());
            }

            case ResultView.GAME_COMPLETED -> {

                title.setText("GAME COMPLETED");

                description.setText("You defeated the final boss.\n\n");

                primaryButton.setText("Hub");
                primaryButton.setOnAction(_ -> secondaryAction.run());
            }
        }

        VBox center = new VBox(20, title, description);

        center.setAlignment(Pos.CENTER);

        HBox buttons = (secondaryButton.getText().isBlank())
                ? new HBox(20, primaryButton)
                : new HBox(20, primaryButton, secondaryButton);
        buttons.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(center);
        root.setBottom(buttons);
        BorderPane.setMargin(buttons, new Insets(20));

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(Objects.requireNonNull(FightResultView.class.getResource("/css/style.css")).toExternalForm());
        root.getStyleClass().add("root");
        title.getStyleClass().add("title");

        return scene;
    }
}
