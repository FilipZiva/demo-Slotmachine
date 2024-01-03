package com.example.slotmachine.Model;

public class BetManager {
    private final CreditManager creditManager;
    private double currentBet;

    public BetManager(CreditManager creditManager) {
        this.creditManager = creditManager;
        this.currentBet = 0;
    }

    public boolean placeBet(double bet) {
        if (creditManager.getBalance() >= bet && bet > 0) {
            currentBet = bet;
            creditManager.subtractCredits(bet);
            return true;
        }
        return false;
    }

    public double getCurrentBet() {
        return currentBet;
    }

    public void resetCurrentBet() {
        currentBet = 0;
    }
}

