package main.java.com.bazydanych.hotel.services;

import main.java.com.bazydanych.hotel.model.Client;
import main.java.com.bazydanych.hotel.repository.ClientRepository;

public class LoginService {
    ClientRepository repository;

    public LoginService(ClientRepository repository) {
        this.repository = repository;
    }

    public String login(String login, String password) {
        try {
            Client client = repository.getClient(login);
            if(client.getPassword().equals(MessegeDigest.md5(password))) {
                return client.getId() + ":" + client.getPassword();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "Unable to login";
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
