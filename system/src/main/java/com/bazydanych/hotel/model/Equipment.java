package main.java.com.bazydanych.hotel.model;

public class Equipment {
    private int id;
    private String name;

    public Equipment(
        int id,
        String name
    ) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
