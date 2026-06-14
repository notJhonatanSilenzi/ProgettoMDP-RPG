package it.unicam.cs.mpgc.rpg126164.gui.views.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * This class works as a view for the instructions of how to play the game, and how it works
 */
public class InfoView {

    private final Runnable onBack;

    public InfoView(Runnable onBack) { this.onBack = onBack; }

    public Scene createScene() {
        // ===================================== TITLE =====================================
        Label title = new javafx.scene.control.Label("HOW TO PLAY");

        // ===================================== INFO =====================================
        Text info = new Text(this.getInfo());
        info.setTextAlignment(TextAlignment.CENTER);

        // ===================================== BUTTON =====================================
        Button back = new Button("Back");
        back.setOnAction(_ -> onBack.run());

        // ===================================== ROOT =====================================
        VBox root = new VBox(20, title, info, back);
        root.setAlignment(Pos.CENTER);

        // ===================================== STYLE =====================================
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("/css/style.css");
        root.getStyleClass().add("root");
        title.getStyleClass().add("title");
        info.getStyleClass().add("normal-text");
        back.getStyleClass().add("button");
        return scene;
    }

    /**
     * Returns the basic info about this game
     * @return the basic info
     */
    private String getInfo() {
        return """
                This game allows you to create a new game, or to load a saved one. Entering the
                world game, you can see four options: starting the adventure, entering the market,
                save the game or returning to the menu.
                 \
                In the adventure mode, you can fight enemies and complete levels, in order to gain
                prizes and complete the game. There are 5 levels and different types of enemies.
                In the market, you can buy exclusive items with the gold you will gain by defeating
                the enemies in the adventure mode.
                Saving the game allows you to continue the game in a future session.
                Fighting is simple. You just have to attack with your weapon, consume potions or change
                weapon, and defeat enemies. Enemies can harm you, so watch out!
                 \
                Have fun and enjoy the game!""";
    }
}
