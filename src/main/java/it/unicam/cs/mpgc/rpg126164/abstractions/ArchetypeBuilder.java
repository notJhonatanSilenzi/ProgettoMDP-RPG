package it.unicam.cs.mpgc.rpg126164.abstractions;

import java.util.Map;

public interface ArchetypeBuilder {

    /**
     * This method sets the initial amount of HP of this playable Character
     * @param hp the initial HP of this playable Character
     */
    void setHP(int hp);

    /**
     * This method sets the initial amount of ATK of this playable character
     * @param atk the initial ATK of this playable character
     */
    void setATK(int atk);

    /**
     * This method sets the initial amount of DF of this playable character
     * @param df the initial DF of this playable character
     */
    void setDF(int df);

    /**
     * this method sets the initial items in the inventory of this playable character
     * @param items the initial items in the inventory of this playable character
     */
    void setItems(Map<Item, Integer> items);

    /**
     * This method sets up the initial wallet of this playable character
     * @param current the initial amount of money in the wallet of this playable character
     * @param max the maximum amount of money for the wallet of this playable character
     */
    void setWallet(int current, int max);

    /**
     * This method returns the initial Inventory of this playable character
     * @return the initial Inventory of this playable character
     */
    InventoryBehaviour getInventory();

    // TODO - DEVE RITORNARE UN PERSONAGGIO GIOCABILE
}
