package main.java.com.bazydanych.hotel.repository;

import main.java.com.bazydanych.hotel.database.DatabaseQuery;
import main.java.com.bazydanych.hotel.model.Equipment;
import main.java.com.bazydanych.hotel.model.PricingIncrease;

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

    public List<Equipment> getEquipmentForRoom(int id) {
        try {
            ResultSet r = query.select("SELECT * FROM Equipment WHERE id IN (SELECT equipment_id FROM EquipmentInRoom WHERE room_id = " + id + ");");
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

    public boolean addEquipmentToRoom(int roomId, int equipmentId) {
        String queryString = "INSERT INTO EquipmentInRoom VALUES(NULL, " + equipmentId + ", " + roomId + ")";

        return query.query(queryString);
    }

    public boolean deleteEquipmentFromRoom(int id) {
        String queryString = "DELETE FROM EquipmentInRoom WHERE id = " + id + ";";

        return query.query(queryString);
    }

    public boolean persist(Equipment equipment, PricingIncrease increase) {
        String queryString = "CALL insert_equipment(" +
            "\"" + equipment.getName() + "\", " +
            increase.getIncrease() + ", \"" +
            increase.getType() + "\");";
        if(equipment.getId() != 0) {
            queryString = "UPDATE Equipment SET " +
                "name = \"" + equipment.getName() +
                "\" WHERE id = " + equipment.getId() + ";";
            query.query(queryString);
            queryString = "UPDATE EquipmentPricingIncrease SET " +
                "increase = " + increase.getIncrease() +
                ", type = \"" + increase.getType() + "\" WHERE equipment_id = " + increase.getId() + ";";
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
