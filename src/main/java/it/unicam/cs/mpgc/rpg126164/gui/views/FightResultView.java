package it.unicam.cs.mpgc.rpg126164.gui.views;

import it.unicam.cs.mpgc.rpg126164.domain.collectibles.ItemStack;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.CombatController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * This class works as a fight result view after ending a fight
 */
public class FightResultView {

    private final CombatController combatController;
    private final Runnable primaryAction;
    private final Runnable secondaryAction;
    private final ResultContext resultContext;

    /**
     * Creates a fight result view
     * @param cc the combat controller
     * @param primaryAction the primary action
     * @param secondaryAction the secondary action
     * @param context the result context
     */
    public FightResultView(CombatController cc, Runnable primaryAction, Runnable secondaryAction, ResultContext context) {
        this.combatController = cc;
        this.primaryAction = primaryAction;
        this.secondaryAction = secondaryAction;
        this.resultContext = context;
    }

    /**
     * Creates a scene to show the fight result
     * @return the fight result scene
     */
    public Scene createScene() {
        String moneyName = combatController.getWorldGame().getPlayer().getMoneyCollector().getMoneyName();

        // ===================================== TITLE =====================================
        Label title = new Label(resultContext.title().replace('_', ' '));
        title.setAlignment(Pos.CENTER);
        Label description = new Label(resultContext.description());
        description.setAlignment(Pos.CENTER);

        // ===================================== BUTTONS =====================================
        Button primaryButton = new Button();
        Button secondaryButton = new Button();

        // ===================================== PRIZE =====================================
        VBox prizeDetails = new VBox();

        switch(resultContext.type()) {
            case ResultView.LEVEL_COMPLETED -> { // LEVEL COMPLETED, GAME NOT COMPLETED
                primaryButton.setText("Continue");
                secondaryButton.setText("Hub");
                prizeDetails = getPrizeDetails(resultContext.prize(), moneyName);
                primaryButton.setOnAction(_ -> primaryAction.run());
                secondaryButton.setOnAction(_ -> secondaryAction.run());
            }
            case ResultView.LEVEL_FAILED -> { // LEVEL FAILED
                primaryButton.setText("Retry");
                secondaryButton.setText("Hub");

                primaryButton.setOnAction(_ -> {
                    combatController.restartLevel();
                    primaryAction.run();
                });
                secondaryButton.setOnAction(_ -> secondaryAction.run());
            }
            case ResultView.GAME_COMPLETED -> { // GAME FINISHED
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

        // ===================================== ROOT =====================================
        BorderPane root = new BorderPane();
        root.setTop(center);
        root.setBottom(buttons);
        if (resultContext.type() == ResultView.LEVEL_COMPLETED) root.setCenter(prizeDetails);
        BorderPane.setMargin(buttons, new Insets(20));

        // ===================================== STYLE =====================================
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("/css/style.css");
        root.getStyleClass().add("root");
        title.getStyleClass().add("title");
        description.getStyleClass().add("normal-text");
        for (Node button : buttons.getChildren()) button.getStyleClass().add("button");

        return scene;
    }

    /**
     * Sets up the VBox for the prize details, in case of victory
     * @param prize the prize gained by the player
     * @param moneyName the money name
     * @return the prize details box
     */
    private VBox getPrizeDetails(ItemStack prize, String moneyName) {
        // ===================================== PRIZE =====================================
        Label name = new Label("Name: " + prize.getItem().getName());
        Label desc = new Label("Description: " + prize.getItem().getDescription());
        Label count = new Label("Quantity: " + prize.getCount());
        Label value = new Label("Value: " + prize.getItem().getTradeValue() + " " + moneyName +
                " (" + prize.getItem().getTradeValue()/2 + " if sold)");
        VBox prizeDetails = new VBox(8, name, desc, count, value);
        prizeDetails.getStylesheets().add("/css/style.css");
        name.getStyleClass().add("inventory-text");
        desc.getStyleClass().add("inventory-text");
        count.getStyleClass().add("inventory-text");
        value.getStyleClass().add("inventory-text");
        prizeDetails.getStyleClass().add("inventory-details");

        return prizeDetails;
    }
}
