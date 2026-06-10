package it.unicam.cs.mpgc.rpg126164.gui.views;

import it.unicam.cs.mpgc.rpg126164.domain.characters.Enemy;
import it.unicam.cs.mpgc.rpg126164.domain.characters.Fighter;
import it.unicam.cs.mpgc.rpg126164.domain.characters.PlayableCharacter;
import it.unicam.cs.mpgc.rpg126164.domain.characters.stats.Archetype;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.equipment.Equipment;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.Consumable;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.PotionTargetType;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.combat.Fight;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.combat.FightResult;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.CombatController;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.LevelController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.control.Label;

import java.util.List;
import java.util.Objects;

public class LevelCombatView {

    private final LevelController levelController;
    private final CombatController combatController;
    private final Runnable onVictory;
    private final Runnable onDefeat;
    private Enemy selectedEnemy;

    public LevelCombatView(LevelController levelController, CombatController combatController, Runnable onVictory, Runnable onLoss) {
        this.levelController = levelController;
        this.combatController = combatController;
        this.onVictory = onVictory;
        this.onDefeat = onLoss;
    }

    public Scene createScene(Stage stage) {
        Fight fight = combatController.getCurrentFight();
        PlayableCharacter player = fight.getPlayer();

        // ======================
        // TITLE
        // ======================

        Label title = new Label("LEVEL: " + combatController.getWorldGame().getLevelManager().getCurrentLevel().getName());

        // ======================
        // PLAYER AREA
        // ======================

        VBox playerBox = new VBox(10);
        ImageView playerImg = new ImageView(getPlayerImage(player.getArchetype()));
        playerImg.setFitWidth(200);
        playerImg.setFitHeight(200);
        playerImg.setPreserveRatio(true);

        playerBox.getChildren().addAll(
                new Label(player.getName()),
                playerImg,
                new Label("HP: " + player.getSheet().getHP())
        );

        playerBox.setAlignment(Pos.CENTER_LEFT);

        // ======================
        // ENEMIES AREA
        // ======================

        VBox enemiesContainer = new VBox(10);
        enemiesContainer.setAlignment(Pos.CENTER);

        HBox enemiesBox = new HBox(20);
        List<Enemy> enemies = fight.getCurrentEnemies();

        for (Enemy enemy : enemies) {

            VBox enemyCard = new VBox(5);
            enemyCard.setAlignment(Pos.CENTER);

            ImageView enemyImg = new ImageView(getEnemyImage(enemy.getName()));
            enemyImg.setFitWidth(150);
            enemyImg.setFitHeight(150);
            enemyImg.setPreserveRatio(true);

            Label name = new Label(enemy.getName());
            Label hpLabel = new Label("HP: " + enemy.getSheet().getHP());

            enemyCard.getChildren().addAll(name, enemyImg, hpLabel);

            enemyCard.setOnMouseClicked(e -> {
                selectedEnemy = enemy;
                System.out.println("Selected enemy: " + enemy.getName());
            });

            enemiesBox.getChildren().add(enemyCard);
        }

        enemiesContainer.getChildren().add(enemiesBox);

        HBox battleField = new HBox(80, playerBox, enemiesBox);
        battleField.setAlignment(Pos.CENTER);

        // ======================
        // LOG
        // ======================

        ListView<String> logView = new ListView<>();
        logView.setPrefHeight(150);
        logView.setStyle("-fx-background-image: url('/images/map-wallpaper-2.jpg')");

        // ======================
        // ACTIONS
        // ======================

        Button attackBtn = new Button("Attack");
        Button potionBtn = new Button("Potion");
        Button weaponBtn = new Button("Weapon");

        HBox actions = new HBox(15, attackBtn, potionBtn, weaponBtn);
        actions.setAlignment(Pos.CENTER);

        // ======================
        // ATTACK
        // ======================

        attackBtn.setOnAction(e -> {
            if (selectedEnemy == null || !selectedEnemy.getSheet().isAlive()) {
                logView.getItems().add("No valid target selected");
                return;
            }
            String result = combatController.playerAttackEnemy(selectedEnemy);
            logView.getItems().add(result);
            selectedEnemy = null;
            refreshScene(stage, logView); // IMPORTANTISSIMO
            if (levelController.getWorldGame().getLevelManager().getCurrentLevel().playerHasWon()) onVictory.run();
            else if (levelController.getWorldGame().getLevelManager().getCurrentLevel().playerHasLost()) onDefeat.run();
        });

        // ======================
        // POTION VIEW
        // ======================

        potionBtn.setOnAction(e -> {
            FightItemsView fiv = new FightItemsView(
                    combatController,
                    InventoryMode.POTION,
                    (itemStack) -> {
                        Consumable potion = (Consumable) itemStack.getItem();
                        Fighter target;
                        if (potion.getTargetType() == PotionTargetType.SELF) target = fight.getPlayer();
                        else {
                            if (selectedEnemy == null) {
                                logView.getItems().add("No enemy selected");
                                return;
                            }
                            target = selectedEnemy;
                        }

                        String result = combatController.playerConsumesPotion(potion, target);
                        logView.getItems().add(result);
                        selectedEnemy = null;
                        refreshScene(stage, logView);
                    },
                    () -> stage.setScene(createScene(stage))
            );
            stage.setScene(fiv.createScene(stage));
            if (levelController.getWorldGame().getLevelManager().getCurrentLevel().playerHasWon()) onVictory.run();
        });

        // ======================
        // WEAPON VIEW
        // ======================
        Label details = new Label();

        weaponBtn.setOnAction(e -> {
            if (player.getInventory().getWeaponCount() <= 1)
                details.setText("You have only one weapon, can't open this page");
            else {
                FightItemsView fiv = new FightItemsView(
                        combatController,
                        InventoryMode.WEAPON,
                        (itemStack) -> combatController.changeEquipment((Equipment) itemStack.getItem()),
                        () -> stage.setScene(createScene(stage))
                );
                stage.setScene(fiv.createScene(stage));
            }
        });

        // ======================
        // LAYOUT
        // ======================

        VBox bottom = new VBox(10, logView, actions, details);

        BorderPane root = new BorderPane();
        root.setTop(title);
        root.setCenter(battleField);
        root.setBottom(bottom);

        root.setPadding(new Insets(15));

        Scene scene =  new Scene(root, 1100, 700);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/style.css")).toExternalForm());

        title.getStyleClass().add("title");
        bottom.getStyleClass().add("root");
        this.getBattlefieldImage(this.levelController.getWorldGame().getLevelManager().getCurrentLevel().getName(), battleField);
        battleField.getStyleClass().add("root");

        return scene;
    }

    private void refreshScene(Stage stage, ListView<String> logView) {
        stage.setScene(createScene(stage));
    }

    private void getBattlefieldImage(String name, HBox battleField) {
        switch (name) {
            case "Level 1 - Old Ruins" -> battleField.setStyle("-fx-background-image: url('/images/ruins-arena.jpg');");
            case "Level 2 - Dry Desert" -> battleField.setStyle("-fx-background-image: url('/images/desert-arena.png');");
            case "Level 3 - Lava Cave" -> battleField.setStyle("-fx-background-image: url('/images/cave-arena.png');");
            case "Level 4 - Mystic Forest" -> battleField.setStyle("-fx-background-image: url('/images/forest-arena.jpg');");
            case "Level 5 - Snow Mountain" -> battleField.setStyle("-fx-background-image: url('/images/mountain-arena.jpg');");
            default -> throw new IllegalStateException("Unexpected value: " + name);
        };
    }


    private String getPlayerImage(Archetype archetype) {
        return switch (archetype) {
            case WARRIOR -> "/images/warrior-player.png";
            case BERSERKER -> "/images/berserker-player.png";
            case CLERIC -> "/images/cleric-player.png";
            case SORCERER -> "/images/sorcerer-player.png";
        };
    }

    private String getEnemyImage(String name) {
        return switch (name) {
            case "Apprentice Sorcerer" -> "/images/apprentice-sorcerer.png";
            case "Archmage" -> "/images/archmage.png";
            case "Assassin" -> "images/assassin.png";
            case "Bandit" -> "/images/bandit.png";
            case "Cultist" -> "/images/cultist.png";
            case "High Priest" -> "/images/high-priest.png";
            case "Militia Guard" -> "/images/militia-guard.png";
            case "Orc Veteran" -> "/images/orc.png";
            default -> throw new IllegalStateException("Unexpected value: " + name);
        };
    }
}