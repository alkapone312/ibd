package main.java.com.bazydanych.hotel.repository;

import main.java.com.bazydanych.hotel.database.DatabaseQuery;
import main.java.com.bazydanych.hotel.model.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository {
    private DatabaseQuery query;

    public ClientRepository(DatabaseQuery query) {
        this.query = query;
    }

    public Client getClient(int id) {
        try {
            ResultSet r = query.select("SELECT * FROM Client WHERE id = " + id);
            r.next();

            return mapToClient(r);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Client getClient(String login) {
        try {
            ResultSet r = query.select("SELECT * FROM Client WHERE email = \"" + login + "\";");
            r.next();

            return mapToClient(r);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Client> getClients() {
        try {
            ResultSet r = query.select("SELECT * FROM Client");
            List<Client> clients = new ArrayList<>();
            while(r.next()) {
                clients.add(mapToClient(r));
            }

            return clients;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean persist(Client client) {
        String queryString = "INSERT INTO Client VALUES(NULL, \"" +
            client.getEmail() + "\", \"" +
            client.getPassword() + "\", " +
            client.isVerified() + ", " +
            client.isDisabled() +
        ")";
        if(client.getId() != 0) {
            queryString = "UPDATE Client SET " +
                "email = \"" + client.getEmail() +
                "\", password = \"" + client.getPassword() +
                "\", verified = " + (client.isVerified() ? 1 : 0) +
                ", disabled = " + (client.isDisabled() ? 1 : 0) +
                " WHERE id = " + client.getId() + ";";
        }

        return query.query(queryString);
    }

    private Client mapToClient(ResultSet r) throws SQLException {

        return new Client(
            r.getInt("id"),
            r.getString("email"),
            r.getString("password"),
            r.getBoolean("verified"),
            r.getBoolean("disabled")
        );
    }
}
