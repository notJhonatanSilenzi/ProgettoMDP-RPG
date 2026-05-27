package it.unicam.cs.mpgc.rpg126164.inventory;

/**
 * This interface allows to the objects that implement this interface to work as wallets. In particularly, in
 * allows to cash in or to spend certain amounts of money
 */
public interface MoneyCollector {

    /**
     * This method allows to collect a certain amount of money
     * @param n the amount of money that the character is collecting. It gets cut if n is bigger than the
     *          difference between max amount and current amount
     */
    void cash(int n);

    /**
     * This method allows to spend a certain amount of money
     * @param n the amount of money that the character is spending
     * @throws IllegalArgumentException if n is greater than the current amount
     */
    void spend(int n);

    /**
     * This method allows to check if the player can spend a certain amount of money, according to this wallet
     * @param n the amount of money to check
     * @return true if the player can afford this buying, false otherwise
     */
    boolean canAfford(int n);
}
