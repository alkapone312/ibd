package main.java.com.bazydanych.hotel.services;

import main.java.com.bazydanych.hotel.model.Client;
import main.java.com.bazydanych.hotel.repository.ClientRepository;

public class LoginService {
    ClientRepository repository;

    public LoginService(ClientRepository repository) {
        this.repository = repository;
    }

    public Client getClient(String token) {
        String[] splittedToken = token.split(":");
        if(splittedToken.length != 3) {
            return null;
        }
        int id = Integer.parseInt(splittedToken[0]);
        String md5 = splittedToken[1];
        Client client = repository.getClient(id);
        if(client.getEmail().equals("admin")) {
            client.setPrivilege("ADMIN");
        }

        if(client != null && client.getPassword().equals(md5)) {
            return client;
        }

        return null;
    }

    public String login(String login, String password) {
        try {
            Client client = repository.getClient(login);
            if(client.getEmail().equals("admin")) {
                client.setPrivilege("ADMIN");
            }
            if(client.getPassword().equals(MessegeDigest.md5(password))) {
                return client.getId() + ":" + client.getPassword() + ":" + client.getPrivilege();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean register(String login, String password) {
        try {
            String p = MessegeDigest.md5(password);
            return repository.persist(new Client(0, login, p, true, false));
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
