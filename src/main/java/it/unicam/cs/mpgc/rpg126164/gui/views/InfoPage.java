package it.unicam.cs.mpgc.rpg126164.gui.views;

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
public class InfoPage {

    private final Runnable onBack;

    public InfoPage(Runnable onBack) { this.onBack = onBack; }

    public Scene createScene() {
        Label title = new javafx.scene.control.Label("HOW TO PLAY");
        title.setStyle(
                "-fx-text-fill: black;" +
                "-fx-font-size: 50px;" +
                "-fx-font-style: bold;" +
                "-fx-font-family: sans-serif;"
        );

        Text info = new Text(this.getInfo());
        info.setStyle(
                "-fx-text-fill: black;" +
                "-fx-font-size: 20px;" +
                "-fx-font-family: sans-serif;" +
                "-fx-padding: 20px;"
        );
        info.setTextAlignment(TextAlignment.CENTER);

        Button back = new Button("Back");
        back.setOnAction(e -> onBack.run());

        VBox root = new VBox(20, title, info, back);
        root.setStyle("-fx-background-color: #8b6f47;");
        root.setAlignment(Pos.CENTER);
        return new Scene(root, 800, 600);
    }

    private String getInfo() {
        return "This game allows you to create a new game, or to load a saved one. Entering the\n" +
                "world game, you can see four options: starting the adventure, entering the market\n" +
                ", save the game or returning to the menu.\n" +
                "In the adventure mode, you can fight enemies and complete levels, in order to gain\n" +
                "prizes and complete the game. There are 5 levels and different types of enemies.\n" +
                "In the market, you can buy exclusive items with the gold you will gain by defeating\n " +
                "the enemies in the adventure mode.\n" +
                "Saving the game allows you to continue the game in a future session.\n" +
                "Fighting is simple. You just have to attack with your weapon, consume potions or change\n" +
                "weapon, and defeat enemies. Enemies can harm you, so watch out!\n" + "\n" +
                "Have fun and enjoy the game!";
    }
}
