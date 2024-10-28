package com.tauseef.app.entities;

public class Dermatologist {

    private static int indexId = 1;
    final private int id;
    final private String name;

    public Dermatologist(String name) {
        this.id = indexId++;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static int getIndexId() {
        return indexId;
    }

    @Override
    public String toString() {
        return "Dermatologist: { id: " + id+ ", name: " + name + " }";
    }
}
