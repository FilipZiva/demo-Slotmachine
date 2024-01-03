package com.example.slotmachine.Model;

import java.util.List;

public class MultiplierManager {

    public double calculateMultiplier(List<Symbol> symbols) {
        int longestStreak = 0;
        int currentStreak = 0;
        Symbol lastSymbol = null;
        double lastNonWildMultiplier = 1.0;

        for (Symbol symbol : symbols) {
            if (symbol.equals(lastSymbol) || (symbol.isWild() && lastSymbol != null) || (lastSymbol != null && lastSymbol.isWild())) {
                currentStreak++;
            } else {
                currentStreak = 1;
            }

            if (currentStreak > longestStreak) {
                longestStreak = currentStreak;
                if (!symbol.isWild()) {
                    lastNonWildMultiplier = symbol.getMultiplier();
                }
            }

            lastSymbol = symbol;
        }

        if (longestStreak >= 3) {
            return lastNonWildMultiplier;
        }

        return -1;
    }
}
