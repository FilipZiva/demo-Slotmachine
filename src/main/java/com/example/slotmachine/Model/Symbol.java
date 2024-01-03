package com.example.slotmachine.Model;

import javafx.scene.image.Image;

public class Symbol {
    private Image image;
    private String name;
    private double multiplier;
    private boolean isWild;
    private boolean isFreeSpinSymbol; // für freispiele später

    public Symbol(Image image, String name, double multiplier, boolean isWild, boolean isFreeSpinSymbol) {
        this.image = image;
        this.name = name;
        this.multiplier = multiplier;
        this.isWild = isWild;
        this.isFreeSpinSymbol = isFreeSpinSymbol;
    }

    public Image getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public boolean isWild() {
        return isWild;
    }

    public boolean isFreeSpinSymbol() {
        return isFreeSpinSymbol;
    }
}
