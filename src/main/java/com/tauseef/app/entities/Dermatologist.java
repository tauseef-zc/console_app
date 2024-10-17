package com.tauseef.app.entities;

public class Dermatologist {

    private int id;
    private String name;

    public Dermatologist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Dermatologist: { id: " + id+ ", name: " + name + " }";
    }
}
