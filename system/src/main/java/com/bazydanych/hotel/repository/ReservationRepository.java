package main.java.com.bazydanych.hotel.repository;

import main.java.com.bazydanych.hotel.database.DatabaseQuery;
import main.java.com.bazydanych.hotel.model.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {
    private DatabaseQuery query;

    public ReservationRepository(DatabaseQuery query) {
        this.query = query;
    }

    public Reservation getReservation(int id) {
        try {
            ResultSet r = query.select("SELECT * FROM Reservation WHERE id = " + id);
            r.next();

            return mapToReservation(r);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Reservation> getReservations() {
        try {
            ResultSet r = query.select("SELECT * FROM Reservation");
            List<Reservation> reservations = new ArrayList<>();
            while(r.next()) {
                reservations.add(mapToReservation(r));
            }

            return reservations;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean persist(Reservation reservation) {
        String queryString = "INSERT INTO Reservation VALUES(NULL, " +
            reservation.getClient().getId() + ", " +
            reservation.getRoom().getId() + ", " +
            reservation.getCheckInDate().getTime() + ", " +
            reservation.getCheckOutDate().getTime() +
            ")";
        if(reservation.getId() != 0) {
            queryString = "UPDATE Reservation SET " +
                "client_id = " + reservation.getClient().getId() +
                ", room_id = " + reservation.getRoom().getId() +
                ", checkin_date = " + reservation.getCheckInDate().getTime() +
                ", checkout_date = " + reservation.getCheckOutDate().getTime() +
                " WHERE id = " + reservation.getId() + ";";
        }

        return query.query(queryString);
    }

    private Reservation mapToReservation(ResultSet r) throws SQLException {
        return new Reservation(
            r.getInt("id"),
            new ClientRepository(query).getClient(r.getInt("client_id")),
            new RoomRepository(query).getRoom(r.getInt("room_id")),
            r.getDate("checkin_date"),
            r.getDate("checkout_date")
        );
    }
}
