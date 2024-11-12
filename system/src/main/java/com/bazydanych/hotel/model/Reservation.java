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
}
