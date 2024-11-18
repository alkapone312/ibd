package main.java.com.bazydanych.hotel.model;

public class Room {
    private int id;

    private int roomSize;

    private double basePrice;

    private int capacity;

    private int roomNumber;

    private String name;

    private String description;

    public Room(
        int id,
        int roomSize,
        double basePrice,
        int capacity,
        int roomNumber,
        String name,
        String description
    ) {
        this.id = id;
        this.roomSize = roomSize;
        this.basePrice = basePrice;
        this.capacity = capacity;
        this.roomNumber = roomNumber;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}