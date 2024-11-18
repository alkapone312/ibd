package main.java.com.bazydanych.hotel.model;

import java.util.Date;

public class Reservation {
    private int id;

    private Client client;

    private Room room;

    private Date checkInDate;

    private Date checkOutDate;

    public Reservation(
        int id,
        Client client,
        Room room,
        Date checkInDate,
        Date checkOutDate
    ) {
        this.id = id;
        this.client = client;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
