package it.unicam.cs.mpgc.rpg126164.gui.views.scenes;

import it.unicam.cs.mpgc.rpg126164.domain.characters.*;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.equipment.Equipment;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.Consumable;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.PotionTargetType;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.combat.Fight;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.CombatController;
import it.unicam.cs.mpgc.rpg126164.gui.controllers.LevelController;
import it.unicam.cs.mpgc.rpg126164.gui.views.utilities.CombatSheetComponents;
import it.unicam.cs.mpgc.rpg126164.gui.views.utilities.InventoryMode;
import it.unicam.cs.mpgc.rpg126164.services.utils.CombatTurnResult;
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

/**
 * This class works as a combat view for levels and fights that are required to complete, in order to
 * move to the next levels
 */
public class LevelCombatView {

    private final LevelController levelController;
    private final CombatController combatController;
    private final Runnable onVictory;
    private final Runnable onDefeat;
    private EnemyFighter selectedEnemy;

    /**
     * Creates a level combat view
     * @param levelController the level controller
     * @param combatController the combat controller
     * @param onVictory the victory view, in case of winning the fight
     * @param onLoss the defeat view, in case of losing the fight
     */
    public LevelCombatView(LevelController levelController, CombatController combatController, Runnable onVictory, Runnable onLoss) {
        this.levelController = levelController;
        this.combatController = combatController;
        this.onVictory = onVictory;
        this.onDefeat = onLoss;
    }

    /**
     * Creates a combat scene for the given stage
     * @param stage the current stage
     * @return the level combat scene
     */
    public Scene createScene(Stage stage) {
        // ===================================== INPUTS =====================================
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
        VBox playerSheet = new VBox(10, name, description, archetype, hp, atk, df, evade, weapon);
        playerSheet.setAlignment(Pos.CENTER_LEFT);

        // ===================================== ENEMIES =====================================
        Label enemyName = new Label("Name: ");
        Label enemyDesc = new Label("Description: ");
        Label enemyArch = new Label("Archetype: ");
        Label enemyHP = new Label("HP: ");
        Label enemyATK =  new Label("ATK: ");
        Label enemyDF = new Label("DF: ");
        Label enemyEvade = new Label("Evade Chance: ");
        Label enemyWeapon = new Label("Equipment: ");

        // Updates the enemy sheet and the combo box of current enemies
        CombatSheetComponents enemyLabels = new CombatSheetComponents(enemyName, enemyDesc, enemyArch, enemyHP, enemyATK, enemyDF, enemyEvade, enemyWeapon);
        ComboBox<EnemyFighter> enemyList = new ComboBox<>();
        enemyList.setItems(FXCollections.observableArrayList(enemies));
        enemyList.setCellFactory(_ -> new ListCell<>() {
            @Override
            protected void updateItem(EnemyFighter enemy, boolean empty) { // Sets the name of the enemies
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
            protected void updateItem(EnemyFighter enemy, boolean empty) { // Sets the names of the enemies
                super.updateItem(enemy, empty);
                if (empty || enemy == null) {
                    setText(null);
                    return;
                }
                setText(enemy.getName());
            }
        });
        enemyList.getSelectionModel() // Updates the selected enemy
                .selectedItemProperty()
                .addListener((_, _, selected) -> {
                    if (selected == null)
                        return;
            selectedEnemy = selected;
            enemyLabels.updateLabels(selectedEnemy);
        });
        VBox enemySheet = new VBox(10, enemyName, enemyDesc, enemyArch, enemyHP, enemyATK, enemyDF, enemyEvade, enemyWeapon);
        enemySheet.setAlignment(Pos.CENTER_LEFT);

        playerSheet.setPrefSize(300, 250);
        playerSheet.setMinSize(300, 250);
        playerSheet.setMaxSize(300, 250);

        enemySheet.setPrefSize(300, 250);
        enemySheet.setMinSize(300, 250);
        enemySheet.setMaxSize(300, 250);

        // ===================================== TURN LOG =====================================
        CombatTurnResult lastResult = combatController.getLastTurnResult();
        Label playerTurn = new Label();
        Label enemyTurn = new Label();
        playerTurn.setAlignment(Pos.CENTER);
        enemyTurn.setAlignment(Pos.CENTER);
        if (lastResult != null) {
            playerTurn.setText(lastResult.playerTurn());
            enemyTurn.setText(lastResult.enemyTurn());
        }
        VBox combatLog = new VBox(10, playerTurn, enemyTurn);
        combatLog.setAlignment(Pos.CENTER);
        combatLog.setPrefHeight(50);
        combatLog.setMaxHeight(50);
        combatLog.setPrefSize(800, 80);
        combatLog.setMaxSize(800, 80);
        combatLog.setPadding(new Insets(20));

        // ===================================== BATTLEFIELD =====================================
        HBox battleField = new HBox(450, playerSheet, enemySheet);
        battleField.setAlignment(Pos.CENTER);

        Label details = new Label();

        // ===================================== BUTTONS =====================================
        Button attackBtn = attackButtonSetup(playerTurn, enemyTurn, enemyList, playerLabels, enemyLabels);
        Button potionBtn = potionButtonSetup(enemyList, playerLabels, enemyLabels, stage);
        Button weaponBtn = weaponButtonSetup(details, stage);

        HBox actions = new HBox(15, attackBtn, potionBtn, weaponBtn, enemyList);
        VBox combat = new VBox(10, combatLog, actions, details);
        actions.setAlignment(Pos.CENTER);
        combat.setAlignment(Pos.CENTER);

        // ===================================== LAYOUT =====================================
        VBox centerWrapper = new VBox(20);
        centerWrapper.setAlignment(Pos.CENTER);
        centerWrapper.setPadding(new Insets(30, 0, 0, 0));
        centerWrapper.getChildren().add(battleField);

        BorderPane root = new BorderPane();
        root.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);
        root.setCenter(centerWrapper);
        root.setBottom(combat);
        BorderPane.setMargin(combat, new Insets(90, 0, 0, 0));
        BorderPane.setAlignment(combatLog, Pos.CENTER);

        root.setPadding(new Insets(15, 5, 5, 5));

        // ===================================== STYLE =====================================
        Scene scene = new Scene(root, 1100, 750);
        scene.getStylesheets().add("/css/style.css");
        title.getStyleClass().add("title");

        playerSheet.getStyleClass().add("inventory-details");
        enemySheet.getStyleClass().add("inventory-details");
        combatLog.getStyleClass().add("inventory-details");
        this.setLabelStyle(List.of(name, description, archetype, hp, atk, df, evade, weapon));
        this.setLabelStyle(List.of(enemyName, enemyDesc, enemyArch, enemyHP, enemyATK, enemyDF, enemyEvade, enemyWeapon));
        enemyList.getStyleClass().add("list-view");
        attackBtn.getStyleClass().add("button");
        potionBtn.getStyleClass().add("button");
        weaponBtn.getStyleClass().add("button");
        details.getStyleClass().add("floating-text");
        battleField.setStyle("-fx-background-color: transparent;");

        playerTurn.getStyleClass().add("floating-text");
        enemyTurn.getStyleClass().add("floating-text");

        this.getBattlefieldImage(this.levelController.getWorldGame().getLevelManager().getCurrentLevel().getName(), root);

        return scene;
    }

    /**
     * Sets up the attack button
     * @param playerTurn the player turn label of the combat log
     * @param enemyTurn the enemy turn label of the combat log
     * @param enemyList the combo box of current enemies
     * @param playerLabels the player labels sheet
     * @param enemyLabels the enemy labels sheet
     * @return the attack button
     */
    private Button attackButtonSetup(Label playerTurn, Label enemyTurn, ComboBox<EnemyFighter> enemyList,
                                    CombatSheetComponents playerLabels, CombatSheetComponents enemyLabels) {
        Button attackBtn = new Button("Attack");
        attackBtn.setOnAction(_ -> {
            if (selectedEnemy == null || selectedEnemy.getSheet().isDead()) {
                playerTurn.setText("No valid target selected");
                enemyTurn.setText("");
                return;
            }
            // Return the results of this turn
            CombatTurnResult result = combatController.playerAttackEnemy(selectedEnemy);
            playerTurn.setText(result.playerTurn());
            enemyTurn.setText(result.enemyTurn());
            enemyLabels.updateLabels(selectedEnemy);
            this.updateFightStatus(result, enemyList, playerLabels, enemyLabels);
        });
        return attackBtn;
    }

    /**
     * Sets up the open potion inventory button
     * @param enemyList the combo box of current enemies
     * @param playerLabels the player labels sheet
     * @param enemyLabels the enemy labels sheet
     * @param stage the current stage
     * @return the potion inventory button
     */
    private Button potionButtonSetup(ComboBox<EnemyFighter> enemyList,
                                     CombatSheetComponents playerLabels, CombatSheetComponents enemyLabels, Stage stage) {
        Button potionBtn = new Button("Potion");
        potionBtn.setOnAction(_ -> {
            FightItemsView fiv = new FightItemsView( // Create a potion only view for the fight
                    combatController,
                    InventoryMode.POTION,
                    (itemStack) -> {
                        Consumable potion = (Consumable) itemStack.getItem();
                        Fighter target; // Update the target
                        if (potion.getTargetType() == PotionTargetType.SELF) target = combatController.getCurrentFight().getPlayer();
                        else {
                            if (selectedEnemy == null || selectedEnemy.getSheet().isDead()) {
                                CombatTurnResult result = new CombatTurnResult("No enemy selected, potion not consumed", "", false);
                                combatController.setLastTurnResult(result);
                                updateFightStatus(result, enemyList, playerLabels, enemyLabels);
                                return;
                            }
                            target = selectedEnemy;
                        }
                        // Return the results of this turn
                        CombatTurnResult result = combatController.playerConsumesPotion(potion, target);
                        combatController.setLastTurnResult(result);
                        this.updateFightStatus(result, enemyList, playerLabels, enemyLabels);
                    },
                    () -> stage.setScene(createScene(stage))
            );
            stage.setScene(fiv.createScene());
        });
        return potionBtn;
    }

    /**
     * Sets up the open weapon inventory button
     * @param details the details label for exceptions
     * @param stage the current stage
     * @return the weapon inventory button
     */
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

    /**
     * Updates the status of the fight, according to the last turn results
     * @param result the last turn result
     * @param enemyList the combo box with current enemies
     * @param playerLabels the player labels sheet
     * @param enemyLabels the enemy labels sheet
     */
    private void updateFightStatus(CombatTurnResult result, ComboBox<EnemyFighter> enemyList, CombatSheetComponents playerLabels, CombatSheetComponents enemyLabels) {
        enemyList.getSelectionModel().clearSelection(); // Clear the selected enemy and check if it's dead
        checkEnemyDead(enemyLabels, result);
        playerLabels.updateLabels(combatController.getWorldGame().getPlayer());
        enemyList.getItems().setAll(combatController.getCurrentFight().getCurrentEnemies());
        if (levelController.getWorldGame().getLevelManager().getCurrentLevel().playerHasWon()) {
            combatController.setLastTurnResult(null);
            onVictory.run();
        }
        else if (levelController.getWorldGame().getLevelManager().getCurrentLevel().playerHasLost()) {
            combatController.setLastTurnResult(null);
            onDefeat.run();
        }
    }

    /**
     * Checks if the enemy is dead, and updates the enemy labels sheet if necessary
     * @param enemyLabels the enemy labels sheet
     * @param result the last turn results
     */
    private void checkEnemyDead(CombatSheetComponents enemyLabels, CombatTurnResult result) {
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
        selectedEnemy = null;
    }

    /**
     * Sets up the combat wallpaper for the right level
     * @param name the level name
     * @param root the root pane of the combat view
     */
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

    /**
     * Sets the style of the given labels
     * @param labels the labels to style
     */
    private void setLabelStyle(List<Label> labels) {
        for (Label label : labels)
            label.getStyleClass().add("combat-text");
    }
}