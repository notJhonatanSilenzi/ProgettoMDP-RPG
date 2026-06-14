package it.unicam.cs.mpgc.rpg126164.domain.inventory;

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
     * @throws IllegalArgumentException if n is greater than the current amount, means the wallet can't
     * afford the buying
     */
    void spend(int n);

    /**
     * This method allows to check if the getPlayer can spend a certain amount of money, according to this wallet
     * @param n the amount of money to check
     * @return true if the getPlayer can afford this buying, false otherwise
     */
    boolean canAfford(int n);

    /**
     * Returns the name of the coin collected in the wallet
     * @return the coin's name of this wallet
     */
    String getMoneyName();

    /**
     * Returns the current amount of money in this wallet
     * @return the current amount
     */
    int getCurrentAmount();

    /**
     * Returns the max amount of money reachable for this wallet
     * @return the max amount of money reachable
     */
    int getMaxAmount();
}
