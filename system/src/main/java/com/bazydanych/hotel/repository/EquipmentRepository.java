package main.java.com.bazydanych.hotel.repository;

import main.java.com.bazydanych.hotel.database.DatabaseQuery;
import main.java.com.bazydanych.hotel.model.Equipment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentRepository {
    private DatabaseQuery query;

    public EquipmentRepository(DatabaseQuery query) {
        this.query = query;
    }

    public Equipment getEquipment(int id) {
        try {
            ResultSet r = query.select("SELECT * FROM Equipment WHERE id = " + id);
            r.next();

            return mapToEquipment(r);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Equipment> getEquipments() {
        try {
            ResultSet r = query.select("SELECT * FROM Equipment");
            List<Equipment> equipments = new ArrayList<>();
            while(r.next()) {
                equipments.add(mapToEquipment(r));
            }

            return equipments;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean persist(Equipment equipment) {
        String queryString = "INSERT INTO Equipment VALUES(NULL, \"" +
            equipment.getName() + "\", " +
            ")";
        if(equipment.getId() != 0) {
            queryString = "UPDATE Equipment SET " +
                "name = \"" + equipment.getName() +
                "\" WHERE id = " + equipment.getId() + ";";
        }

        return query.query(queryString);
    }

    private Equipment mapToEquipment(ResultSet r) throws SQLException {

        return new Equipment(
            r.getInt("id"),
            r.getString("name")
        );
    }
}
