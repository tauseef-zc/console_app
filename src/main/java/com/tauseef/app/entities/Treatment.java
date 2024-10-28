package com.tauseef.app.entities;

public class Treatment {

    final private String name;
    final private double price;

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

}
