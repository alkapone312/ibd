package main.java.com.bazydanych.hotel.repository;

import main.java.com.bazydanych.hotel.database.DatabaseQuery;
import main.java.com.bazydanych.hotel.model.Client;
import main.java.com.bazydanych.hotel.model.Reservation;
import main.java.com.bazydanych.hotel.model.Room;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RentRepository {
    private DatabaseQuery query;
    private ClientRepository clientRepository;
    private RoomRepository roomRepository;

    public RentRepository(DatabaseQuery query) {
        this.query = query;
        clientRepository = new ClientRepository(query);
        roomRepository = new RoomRepository(query);
    }

    public List<Reservation> getRents() {
        try {
            ResultSet r = query.select("SELECT * FROM Reservation;");
            List<Reservation> l = new ArrayList<>();
            while (r.next()) {
                l.add(mapToRent(r));
            }

            return l;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Reservation> getRentsForClient(Client client) {
        try {
            ResultSet r = query.select("SELECT * FROM Reservation WHERE client_id = " + client.getId() + ";");
            List<Reservation> l = new ArrayList<>();
            while (r.next()) {
                l.add(mapToRent(r));
            }

            return l;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }    }

    public Reservation rentRoom(Reservation rent, double price) {
        try {
            query.query("INSERT INTO ReservationPricing VALUES(NULL, " + price + ")");
            CallableStatement statement = query.prepare("INSERT INTO Reservation VALUES(NULL, ?, ?, ?, ?)");
            statement.setInt(1, rent.getClient().getId());
            statement.setInt(2, rent.getRoom().getId());
            statement.setDate(3, new java.sql.Date(rent.getCheckInDate().getTime()));
            statement.setDate(4, new java.sql.Date(rent.getCheckOutDate().getTime()));
            statement.execute();
            return rent;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean cancelRent(int id) {
        return query.query("DELETE FROM Reservation WHERE id = " + id) && query.query("DELETE FROM ReservationPricing WHERE reservation_id = " + id);
    }

    private Reservation mapToRent(ResultSet r) throws SQLException {
        Client client = clientRepository.getClient(r.getInt("client_id"));
        Room room = roomRepository.getRoom(r.getInt("room_id"));

        return new Reservation(
            r.getInt("id"),
            client,
            room,
            r.getDate("checkin_date"),
            r.getDate("checkout_date")
        );
    }
}
