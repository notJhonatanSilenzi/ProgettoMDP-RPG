package it.unicam.cs.mpgc.rpg126164;

import it.unicam.cs.mpgc.rpg126164.services.GameBootstrap;
import it.unicam.cs.mpgc.rpg126164.services.GameService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * In this class, the game gets initialized through the game bootstrap
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Label label = new Label("JavaFX funziona");

        Scene scene = new Scene(
                new StackPane(label),
                400,
                300
        );

        stage.setScene(scene);
        stage.setTitle("Test JavaFX");
        stage.show();
    }

    public static void main(String[] args) {

        GameBootstrap bootstrap = new GameBootstrap();
        GameService gameService = bootstrap.initGamePlay();

        launch(args);
    }
}
