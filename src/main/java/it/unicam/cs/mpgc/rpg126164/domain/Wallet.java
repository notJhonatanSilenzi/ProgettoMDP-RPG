package it.unicam.cs.mpgc.rpg126164.domain;

import it.unicam.cs.mpgc.rpg126164.abstractions.MoneyCollector;

/**
 * This class represents a wallet for a playable character. It requires a money name, the current amount and the
 * maximum amount, and it implements MoneyCollector
 */
public class Wallet implements MoneyCollector {

    private final String moneyName;
    private int amount;
    private final int maxAmount;

    /**
     * Creates a wallet for a playable character
     * @param moneyName the name of the money that this wallet contains
     * @param amount the starting amount of money
     * @param maxAmount the maximum amount of money that this wallet can contain
     * @throws IllegalArgumentException if money name is empty, if amount or max amount are negative or if
     *          amount is greater than max amount
     */
    public Wallet(String moneyName, int amount, int maxAmount) {
        if (moneyName.isEmpty() || amount < 0 || maxAmount < 0)
            throw new IllegalArgumentException("Name can't be empty and amount and max amount can't be negative");
        if (amount > maxAmount)
            throw new IllegalArgumentException("Amount can't be greater than max amount");

        this.moneyName = moneyName;
        this.amount = amount;
        this.maxAmount = maxAmount;
    }

    @Override
    public void collect(int n) { amount += Math.min(n, maxAmount - amount); }

    @Override
    public void spend(int n) {
        if (n > amount)
            throw new IllegalArgumentException("Amount to spend can't be greater than current amount");

        amount -= n;
    }

    @Override
    public boolean canAfford(int n) { return amount >= n; }

    // GETTERS

    public String getMoneyName() { return moneyName; }

    public int getAmount() { return amount; }

    public int getMaxAmount() { return maxAmount; }
}
