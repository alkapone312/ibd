package main.java.com.bazydanych.hotel.repository;

import main.java.com.bazydanych.hotel.database.DatabaseQuery;
import main.java.com.bazydanych.hotel.model.Equipment;
import main.java.com.bazydanych.hotel.model.PricingIncrease;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdditionalEquipmentRepository {
    private DatabaseQuery query;

    public AdditionalEquipmentRepository(DatabaseQuery query) {
        this.query = query;
    }

    public List<Equipment> getEquipmentForReservation(int id) {
        try {
            ResultSet r = query.select("SELECT * FROM `AdditionalEquipment` WHERE id IN (SELECT additional_equipment_id FROM AdditionalEquipmentForReservation WHERE reservation_id = " + id + ")");
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
            ResultSet r = query.select("SELECT * FROM AdditionalEquipment");
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

    public Equipment getEquipment(int id) {
        try {
            ResultSet r = query.select("SELECT * FROM AdditionalEquipment WHERE id = " + id + ";");
            r.next();

            return mapToEquipment(r);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public PricingIncrease getPricingIncreaseForAdditionalEquipment(int id) {
        try {
            ResultSet r = query.select("SELECT * FROM  AdditionalEquipmentPricingIncrease WHERE additional_equipment_id = " + id + ";");
            r.next();

            return mapToPricingIncrease(r);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean addEquipmentToRoom(int reservationId, int equipmentId) {
        String queryString = "INSERT INTO AdditionalEquipmentForReservation VALUES(NULL, " + equipmentId + ", " + reservationId + ")";

        return query.query(queryString);
    }

    public boolean deleteEquipmentFromRoom(int id) {
        String queryString = "DELETE FROM AdditionalEquipmentForReservation WHERE id = " + id + ";";

        return query.query(queryString);
    }

    public boolean persist(Equipment equipment, PricingIncrease increase) {
        String queryString = "CALL insert_additional_equipment(" +
            "\"" + equipment.getName() + "\", " +
            increase.getIncrease() + ", \"" +
            increase.getType() + "\");";
        if(equipment.getId() != 0) {
            queryString = "UPDATE AdditionalEquipment SET " +
                "name = \"" + equipment.getName() +
                "\" WHERE id = " + equipment.getId() + ";";
            query.query(queryString);
            queryString = "UPDATE AdditionalEquipmentPricingIncrease SET " +
                "increase = " + increase.getIncrease() +
                ", type = \"" + increase.getType() + "\" WHERE additional_equipment_id = " + increase.getId() + ";";
        }

        return query.query(queryString);
    }

    private Equipment mapToEquipment(ResultSet r) throws SQLException {

        return new Equipment(
            r.getInt("id"),
            r.getString("name")
        );
    }

    private PricingIncrease mapToPricingIncrease(ResultSet r) throws SQLException {
        return new PricingIncrease(
            r.getInt("additional_equipment_id"),
            r.getDouble("increase"),
            r.getString("type")
        );
    }
}
