package it.unicam.cs.mpgc.rpg126164.gui.controllers;

import it.unicam.cs.mpgc.rpg126164.domain.characters.EnemyFighter;
import it.unicam.cs.mpgc.rpg126164.domain.characters.Fighter;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.equipment.Equipment;
import it.unicam.cs.mpgc.rpg126164.domain.collectibles.potions.Consumable;
import it.unicam.cs.mpgc.rpg126164.domain.gamemechanics.combat.Fight;
import it.unicam.cs.mpgc.rpg126164.domain.world.savingmechanics.WorldGame;
import it.unicam.cs.mpgc.rpg126164.gui.GameSession;
import it.unicam.cs.mpgc.rpg126164.services.CombatService;
import it.unicam.cs.mpgc.rpg126164.services.utils.CombatTurnResult;

/**
 * This class works as a controller for the combat service
 */
public class CombatController {

    private final CombatService service;
    private final GameSession session;
    private CombatTurnResult lastTurnResult;

    /**
     * Creates a combat controller
     * @param service the combat service
     * @param session the current game session
     */
    public CombatController(CombatService service, GameSession session) {
        this.service = service;
        this.session = session;
    }

    /**
     * Starts the current fight
     */
    public void startFight() { this.service.startFight(this.getCurrentFight()); }

    /**
     * Makes the player attack the enemy, and then the player may receive a counterattack by the target
     * @param enemy the enemy attacked by the player
     */
    public CombatTurnResult playerAttackEnemy(EnemyFighter enemy) {
        return this.service.playerAttackEnemy(
                this.getCurrentFight(),
                enemy
        );
    }

    /**
     * Makes the player consume a potion
     * @param potion the potion to consume
     * @param target the target that receives the potion's effect
     * @return the last combat turn result for the UI
     */
    public CombatTurnResult playerConsumesPotion(Consumable potion, Fighter target) {
        return this.service.playerConsumesPotion(
                this.getCurrentFight(),
                potion,
                target
        );
    }

    /**
     * Makes the player change weapon with the given one
     * @param weapon the weapon to equip
     */
    public void changeEquipment(Equipment weapon) {
        this.service.changeEquipment(
                this.getWorldGame().getPlayer(),
                this.getCurrentFight(),
                weapon
        );
    }

    /**
     * Restarts the level after losing the current fight
     */
    public void restartLevel() { this.service.restartLevel(this.getCurrentFight()); }

    /**
     * Returns the current world game of this game session
     * @return the current world game
     */
    public WorldGame getWorldGame() { return session.getWorldGame(); }

    /**
     * Returns the current fight of this game session
     * @return the current fight
     */
    public Fight getCurrentFight() { return session.getCurrentFight(); }

    /**
     * Returns the last combat turn result
     * @return the last combat turn result
     */
    public CombatTurnResult getLastTurnResult() { return lastTurnResult; }

    /**
     * Sets the given combat result as the current last turn result
     * @param result the last combat result turn to update
     */
    public void setLastTurnResult(CombatTurnResult result) { this.lastTurnResult = result; }
}
