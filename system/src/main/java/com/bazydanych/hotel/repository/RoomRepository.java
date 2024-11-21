package main.java.com.bazydanych.hotel.repository;

import main.java.com.bazydanych.hotel.database.DatabaseQuery;
import main.java.com.bazydanych.hotel.model.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomRepository {
    private DatabaseQuery query;

    public RoomRepository(DatabaseQuery query) {
        this.query = query;
    }

    public Room getRoom(int id) {
        try {
            ResultSet r = query.select("SELECT * FROM Room WHERE id = " + id);
            r.next();

            return mapToRoom(r);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Room> getRooms() {
        try {
            ResultSet r = query.select("SELECT * FROM Room");
            List<Room> rooms = new ArrayList<>();
            while(r.next()) {
                rooms.add(mapToRoom(r));
            }

            return rooms;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Room> getAvailableRooms(Date from, Date to) {
        try {
            System.out.println("SELECT * FROM Room WHERE id NOT IN " +
                "(SELECT room_id  FROM `Reservation` " +
                "WHERE (checkin_date >= " + from.getTime() +
                " AND checkin_date <= " + to.getTime() + ")" +
                " OR (checkout_date >= " + from.getTime() +
                " AND checkout_date <= " + to.getTime() + "));");
            ResultSet r = query.select(
                "SELECT * FROM Room WHERE id NOT IN " +
                    "(SELECT room_id  FROM `Reservation` " +
                        "WHERE (checkin_date >= " + from.getTime() +
                            " AND checkin_date <= " + to.getTime() + ")" +
                            " OR (checkout_date >= " + from.getTime() +
                            " AND checkout_date <= " + to.getTime() + "));");
            List<Room> rooms = new ArrayList<>();
            while(r.next()) {
                rooms.add(mapToRoom(r));
            }

            return rooms;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean persist(Room room) {
        String queryString = "INSERT INTO Room VALUES(NULL, " +
            room.getRoomSize() + ", " +
            room.getBasePrice() + ", " +
            room.getCapacity() + ", " +
            room.getRoomNumber() + ", \"" +
            room.getName() + "\", \"" +
            room.getDescription() + "\" " +
            ")";
        if(room.getId() != 0) {
            queryString = "UPDATE Room SET " +
                "roomSize = " + room.getRoomSize() +
                ", basePrice = " + room.getBasePrice() +
                ", capacity = " + room.getCapacity() +
                ", roomNumber = " + room.getRoomNumber() +
                ", name = \"" + room.getName() +
                "\", description = \"" + room.getDescription() +
                "\" WHERE id = " + room.getId() + ";";
        }

        return query.query(queryString);
    }

    private Room mapToRoom(ResultSet r) throws SQLException {
        return new Room(
            r.getInt("id"),
            r.getInt("roomSize"),
            r.getDouble("basePrice"),
            r.getInt("capacity"),
            r.getInt("roomNumber"),
            r.getString("name"),
            r.getString("description")
        );
    }
}
