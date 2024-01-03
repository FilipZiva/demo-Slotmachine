package com.example.slotmachine.Model;

public class CreditManager {
    private double credit;

    public CreditManager() {
        this.credit = 500.0;
    }

    public double getBalance() {
        return credit;
    }

    public void addCredits(double amount) {
        if(credit > 0){
            credit+=amount;
        }
    }

    public void subtractCredits(double amount) {
        if(credit > 0 && credit >= amount){
            credit-=amount;
        }
    }

    public void resetCredits() {
        credit = 500.0;
    }


}
