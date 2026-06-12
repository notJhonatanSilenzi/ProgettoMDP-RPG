package it.unicam.cs.mpgc.rpg126164.gui.views;

import it.unicam.cs.mpgc.rpg126164.domain.characters.*;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.equipment.Equipment;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.Consumable;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.PotionTargetType;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.combat.Fight;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.CombatController;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.LevelController;
import it.unicam.cs.mpgc.rpg126164.services.CombatTurnResult;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class LevelCombatView {

    private final LevelController levelController;
    private final CombatController combatController;
    private final Runnable onVictory;
    private final Runnable onDefeat;
    private EnemyFighter selectedEnemy;

    public LevelCombatView(LevelController levelController, CombatController combatController, Runnable onVictory, Runnable onLoss) {
        this.levelController = levelController;
        this.combatController = combatController;
        this.onVictory = onVictory;
        this.onDefeat = onLoss;
    }

    public Scene createScene(Stage stage) {
        Fight fight = combatController.getCurrentFight();
        PlayerFighter player = fight.getPlayer();
        List<EnemyFighter> enemies = fight.getCurrentEnemies();

        // ===================================== TITLE =====================================
        Label title = new Label(combatController.getWorldGame().getLevelManager().getCurrentLevel().getName().toUpperCase());
        title.setAlignment(Pos.CENTER);

        // ===================================== PLAYER =====================================
        Label name = new Label();
        Label description = new Label();
        Label archetype = new Label();
        Label hp = new Label();
        Label atk = new Label();
        Label df = new Label();
        Label evade = new Label();
        Label weapon = new Label();
        CombatSheetComponents playerLabels = new CombatSheetComponents(name, description, archetype, hp, atk, df, evade, weapon);
        playerLabels.updateLabels(player);
        VBox playerSheet = new VBox(name, description, archetype, hp, atk, df, evade, weapon);
        playerSheet.setAlignment(Pos.CENTER_LEFT);

        // ===================================== ENEMIES =====================================
        Label enemyName = new Label("Name: ");
        Label enemyDesc = new Label("Description:");
        Label enemyArch = new Label("Archetype: ");
        Label enemyHP = new Label("HP: ");
        Label enemyATK =  new Label("ATK: ");
        Label enemyDF = new Label("DF: ");
        Label enemyEvade = new Label("Evade Chance: ");
        Label enemyWeapon = new Label("Equipment: ");

        CombatSheetComponents enemyLabels = new CombatSheetComponents(enemyName, enemyDesc, enemyArch, enemyHP, enemyATK, enemyDF, enemyEvade, enemyWeapon);
        ComboBox<EnemyFighter> enemyList = new ComboBox<>();
        enemyList.setItems(FXCollections.observableArrayList(enemies));
        enemyList.setCellFactory(_ -> new ListCell<>() {
            @Override
            protected void updateItem(EnemyFighter enemy, boolean empty) {
                super.updateItem(enemy, empty);

                if (empty || enemy == null) {
                    setText(null);
                    return;
                }

                setText(enemy.getName());
            }
        });
        enemyList.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(EnemyFighter enemy, boolean empty) {
                super.updateItem(enemy, empty);

                if (empty || enemy == null) {
                    setText(null);
                    return;
                }

                setText(enemy.getName());
            }
        });
        enemyList.getSelectionModel()
                .selectedItemProperty()
                .addListener((_, _, selected) -> {

                    if (selected == null)
                        return;

            selectedEnemy = selected;
            enemyLabels.updateLabels(selectedEnemy);
        });
        VBox enemySheet = new VBox(10, enemyName, enemyDesc, enemyArch, enemyHP, enemyATK, enemyDF, enemyEvade, enemyWeapon);

        VBox enemySide = new VBox(10, enemyList, enemySheet);
        enemySide.setAlignment(Pos.CENTER);

        // ===================================== TURN LOG =====================================
        Label playerTurn = new Label();
        Label enemyTurn = new Label();
        VBox combatLog = new VBox(10, playerTurn, enemyTurn);

        // ===================================== BATTLEFIELD =====================================
        HBox battleField = new HBox(80, playerSheet, enemySide);
        battleField.setAlignment(Pos.CENTER);

        Label details = new Label();

        // ===================================== BUTTONS =====================================
        Button attackBtn = attackButtonSetup(playerTurn, enemyTurn, enemyList, playerLabels, enemyLabels);
        Button potionBtn = potionButtonSetup(playerTurn, enemyTurn, enemyList, playerLabels, enemyLabels, stage);
        Button weaponBtn = weaponButtonSetup(details, stage);

        HBox actions = new HBox(15, attackBtn, potionBtn, weaponBtn);
        actions.setAlignment(Pos.CENTER);

        // ===================================== LAYOUT =====================================
        VBox bottom = new VBox(10, combatLog, actions, details);

        BorderPane root = new BorderPane();
        root.setTop(title);
        root.setCenter(battleField);
        root.setBottom(bottom);

        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 1100, 700);
        scene.getStylesheets().add("/css/style.css");
        title.getStyleClass().add("title");

        playerSheet.getStyleClass().add("root");
        enemySheet.getStyleClass().add("root");
        combatLog.getStyleClass().add("root");
        this.setLabelStyle(List.of(name, description, archetype, hp, atk, df, evade, weapon));
        this.setLabelStyle(List.of(enemyName, enemyDesc, enemyArch, enemyHP, enemyATK, enemyDF, enemyEvade, enemyWeapon));
        enemyList.getStyleClass().add("list-view");
        attackBtn.getStyleClass().add("button");
        potionBtn.getStyleClass().add("button");
        weaponBtn.getStyleClass().add("button");
        details.getStyleClass().add("floating-text");
        battleField.setStyle("-fx-background-color: transparent;");

        this.getBattlefieldImage(this.levelController.getWorldGame().getLevelManager().getCurrentLevel().getName(), root);

        return scene;
    }

    private Button attackButtonSetup(Label playerTurn, Label enemyTurn, ComboBox<EnemyFighter> enemyList,
                                    CombatSheetComponents playerLabels, CombatSheetComponents enemyLabels) {
        Button attackBtn = new Button("Attack");
        attackBtn.setOnAction(_ -> {
            if (selectedEnemy == null || !selectedEnemy.getSheet().isAlive()) {
                playerTurn.setText("No valid target selected");
                return;
            }
            CombatTurnResult result = combatController.playerAttackEnemy(selectedEnemy);
            this.updateFightStatus(result, playerTurn, enemyTurn, enemyList, playerLabels, enemyLabels);
        });
        return attackBtn;
    }

    private Button potionButtonSetup(Label playerTurn, Label enemyTurn, ComboBox<EnemyFighter> enemyList,
                                     CombatSheetComponents playerLabels, CombatSheetComponents enemyLabels, Stage stage) {
        Button potionBtn = new Button("Potion");
        potionBtn.setOnAction(_ -> {
            FightItemsView fiv = new FightItemsView(
                    combatController,
                    InventoryMode.POTION,
                    (itemStack) -> {
                        Consumable potion = (Consumable) itemStack.getItem();
                        Fighter target;
                        if (potion.getTargetType() == PotionTargetType.SELF) target = combatController.getCurrentFight().getPlayer();
                        else {
                            if (selectedEnemy == null || !selectedEnemy.getSheet().isAlive()) {
                                playerTurn.setText("No enemy selected");
                                return;
                            }
                            target = selectedEnemy;
                        }
                        CombatTurnResult result = combatController.playerConsumesPotion(potion, target);
                        this.updateFightStatus(result, playerTurn, enemyTurn, enemyList, playerLabels, enemyLabels);
                    },
                    () -> stage.setScene(createScene(stage))
            );
            stage.setScene(fiv.createScene());
        });
        return potionBtn;
    }

    private Button weaponButtonSetup(Label details, Stage stage) {
        PlayerFighter player = combatController.getWorldGame().getPlayer();
        Button weaponBtn = new Button("Weapon");
        weaponBtn.setOnAction(_ -> {
            if (player.getInventory().getWeaponCount() <= 1)
                details.setText("You have only one weapon, can't open this page");
            else {
                FightItemsView fiv = new FightItemsView(
                        combatController,
                        InventoryMode.WEAPON,
                        (itemStack) -> combatController.changeEquipment((Equipment) itemStack.getItem()),
                        () -> stage.setScene(createScene(stage))
                );
                stage.setScene(fiv.createScene());
            }
        });
        return weaponBtn;
    }

    private void checkEnemyDead(CombatSheetComponents enemyLabels, CombatTurnResult result) {
        selectedEnemy = null;
        if (result.enemyDied()) {
            enemyLabels.name().setText("Name: ");
            enemyLabels.archetype().setText("Archetype: ");
            enemyLabels.description().setText("Description: ");
            enemyLabels.hp().setText("HP: ");
            enemyLabels.atk().setText("ATK: ");
            enemyLabels.df().setText("DF: ");
            enemyLabels.evade().setText("Evade Chance: ");
            enemyLabels.weapon().setText("Weapon: ");
        }
    }

    private void updateFightStatus(CombatTurnResult result, Label playerTurn, Label enemyTurn, ComboBox<EnemyFighter> enemyList, CombatSheetComponents playerLabels, CombatSheetComponents enemyLabels) {
        playerTurn.setText(result.playerTurn());
        enemyTurn.setText(result.enemyTurn());
        enemyList.getSelectionModel().clearSelection();
        checkEnemyDead(enemyLabels, result);
        playerLabels.updateLabels(combatController.getWorldGame().getPlayer());
        enemyList.getItems().setAll(combatController.getCurrentFight().getCurrentEnemies());
        if (levelController.getWorldGame().getLevelManager().getCurrentLevel().playerHasWon())
            onVictory.run();
        else if (levelController.getWorldGame().getLevelManager().getCurrentLevel().playerHasLost())
            onDefeat.run();
    }

    private void getBattlefieldImage(String name, BorderPane root) {
        switch (name) {
            case "Level 1 - Old Ruins" -> root.setStyle("-fx-background-image: url('/images/ruins-arena.jpg');");
            case "Level 2 - Dry Desert" -> root.setStyle("-fx-background-image: url('/images/desert-arena.png');");
            case "Level 3 - Lava Cave" -> root.setStyle("-fx-background-image: url('/images/cave-arena.png');");
            case "Level 4 - Mystic Forest" -> root.setStyle("-fx-background-image: url('/images/forest-arena.jpg');");
            case "Level 5 - Snow Mountain" -> root.setStyle("-fx-background-image: url('/images/mountain-arena.jpg');");
            default -> throw new IllegalStateException("Unexpected value: " + name);
        }
    }

    private void setLabelStyle(List<Label> labels) {
        for (Label label : labels)
            label.getStyleClass().add("character-value");
    }
}