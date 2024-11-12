package main.java.com.bazydanych.hotel.model;

public class Discount {
    private int id;
    private double decrease;
    private String type;

    public Discount(
        int id,
        double decrease,
        String type
    ) {
        this.id = id;
        this.decrease = decrease;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public double getDecrease() {
        return decrease;
    }

    public void setDecrease(double decrease) {
        this.decrease = decrease;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
