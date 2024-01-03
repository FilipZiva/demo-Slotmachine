package com.example.slotmachine.Model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SlotMachine {
    private final List<Symbol> symbolList;
    private List<Symbol> currentSpinSymbols;

    private CreditManager creditManager;
    private BetManager betManager;
    private MultiplierManager multiplierManager;

    public SlotMachine() {
        this.symbolList = new ArrayList<>();
        this.creditManager = new CreditManager();
        this.betManager =  new BetManager(creditManager);
        this.multiplierManager = new MultiplierManager();
        initializeSymbols();
    }

    public BetManager getBetManager() {
        return betManager;
    }

    public List<Symbol> getCurrentSpinSymbols() {
        return currentSpinSymbols;
    }

    public CreditManager getCreditManager() {
        return creditManager;
    }
    private void initializeSymbols() {
        // Symbole mit Grading "Never Quit" (1)
        symbolList.add(new Symbol(null, "U1", 0.0, false,false));
        symbolList.add(new Symbol(null, "U3", 0.0, false,false));
        symbolList.add(new Symbol(null, "U6", 0.0, false,false));

        // Symbole mit Grading "Bad" (2)
        symbolList.add(new Symbol(null, "Redbull", 0.5, false,false));
        symbolList.add(new Symbol(null, "Marlboro", 1.5, false,false));

        // Symbole mit Grading "Nice" (3)
        symbolList.add(new Symbol(null, "Leberkas", 2.0, false,false));

        // Symbole mit Grading "Happy" (4)
        symbolList.add(new Symbol(null, "Lugner", 3.0, false,false));

        // Symbole mit Grading "Lucky" (5)
        symbolList.add(new Symbol(null, "GIS", 5.5, false,false));

        // Symbole mit Grading "Free Games" (6)
        // symbolList.add(new Symbol(new Image("resources/symbols/image_16erBlech.png"), "16er Blech", 1.0, false,true));

        // Symbole mit Grading "Wild"
        symbolList.add(new Symbol(null, "Wild", 0, true,false));
    }

    private void initializeSymbolsWithPictures() {
        symbolList.add(new Symbol(new Image("resources/symbols/image_U1.png"), "U1", 0.0, false,false));
        symbolList.add(new Symbol(new Image("resources/symbols/image_U3.png"), "U3", 0.0, false,false));
        symbolList.add(new Symbol(new Image("resources/symbols/image_U6.png"), "U6", 0.0, false,false));
        symbolList.add(new Symbol(new Image("resources/symbols/image_redbull.png"), "Redbull", 0.5, false,false));
        symbolList.add(new Symbol(new Image("resources/symbols/image_marlboro.png"), "Marlboro", 1.5, false,false));
        symbolList.add(new Symbol(new Image("resources/symbols/image_leberkas.png"), "Leberkas", 2.0, false,false));
        symbolList.add(new Symbol(new Image("resources/symbols/image_lugner.png"), "Lugner", 3.0, false,false));
        symbolList.add(new Symbol(new Image("resources/symbols/image_gis.png"), "GIS", 5.5, false,false));
       // symbolList.add(new Symbol(new Image("resources/symbols/image_16erBlech.png"), "16er Blech", 1.0, false,true)); -> Freispiele
        symbolList.add(new Symbol(new Image("resources/symbols/image_wild.png"), "Wild", 0, true,false));
    }

    public void spin(){
        this.currentSpinSymbols = getRandomSymbols(symbolList, 5);
        evaluateSpinResult();
    }

    public void evaluateSpinResult() {
        double multiplier = multiplierManager.calculateMultiplier(currentSpinSymbols);
        if (multiplier != -1) {
            double winAmount = betManager.getCurrentBet() * multiplier;
            creditManager.addCredits(winAmount);
        }
    }

    public List<Symbol> getRandomSymbols(List<Symbol> originalList, int numberOfSymbols) {
        List<Symbol> randomSymbols = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < numberOfSymbols; i++) {
            int randomIndex = random.nextInt(originalList.size());
            randomSymbols.add(originalList.get(randomIndex));
        }
        return randomSymbols;
    }


    public static void main(String[] args) {
        SlotMachine slotMachine = new SlotMachine();

        double[] bets = {20.0,100.0,10.0,20.0,50.0};
        for (double bet : bets) {
            if (slotMachine.getBetManager().placeBet(bet)) {
                System.out.println("Gesetzte Summe: " + bet);
                slotMachine.spin();
                displaySpinResults(slotMachine);
            } else {
                System.out.println("Nicht genügend Credits für den Einsatz: " + bet);
            }
        }
    }

    private static void displaySpinResults(SlotMachine slotMachine) {
        List<Symbol> spinResults = slotMachine.getCurrentSpinSymbols();
        System.out.println("Spin-Ergebnisse:");
        System.out.println("---------");
        for (Symbol symbol : spinResults) {
            System.out.println("Symbol: " + symbol.getName() + ", Multiplier: " + symbol.getMultiplier());
        }
        System.out.println("---------");
        System.out.println("Verbleibende Credits: " + slotMachine.getCreditManager().getBalance());
        System.out.println();
    }
}
