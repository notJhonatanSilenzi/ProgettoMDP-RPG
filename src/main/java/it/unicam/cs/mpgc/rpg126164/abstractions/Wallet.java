package it.unicam.cs.mpgc.rpg126164.abstractions;

/**
 * This interface allows to the objects that implement this interface to work as wallets. In particulartly, in
 * allows to collect or to spend certain amounts of money
 */
public interface Wallet {

    /**
     * This method allows to collect a certain amount of money, adding it to the current amount of money in
     * the wallet
     * @param n the amount of money that the character is collecting
     */
    void collect(int n);

    /**
     * This method allows to spend a certain amount of money, taking it off from the current amount of money in
     * the wallet
     * @param n the amount of money that the character is spending
     */
    void spend (int n);
}
