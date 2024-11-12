package main.java.com.bazydanych.hotel.model;

public class PricingIncrease {
    private int id;
    private double increase;
    private String type;

    public PricingIncrease(
        int id,
        double increase,
        String type
    ) {
        this.id = id;
        this.increase = increase;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public double getIncrease() {
        return increase;
    }

    public void setIncrease(double increase) {
        this.increase = increase;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
