package com.tauseef.app.entities;

public class Treatment {

    private String name;
    private double price;

    public Treatment(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double calculateTotalPrice() {
        double tax = price * 0.025;
        return Math.ceil((price + tax) * 100) / 100;
    }

}
