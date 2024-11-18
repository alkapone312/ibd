package main.java.com.bazydanych.hotel.controllers;

import main.java.com.bazydanych.hotel.database.Connector;
import main.java.com.bazydanych.hotel.database.DatabaseQuery;
import main.java.com.bazydanych.hotel.repository.ClientRepository;
import main.java.com.bazydanych.hotel.services.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {
    ClientRepository clientRepository = new ClientRepository(new DatabaseQuery(new Connector()));

    @PostMapping("/login")
    public String login(
        @RequestParam("login") String login,
        @RequestParam("password") String password
    ) {
        return new LoginService(clientRepository).login(login, password);
    }

    @PostMapping("/register")
    public String register(
        @RequestParam("login") String login,
        @RequestParam("password") String password
    ) {
        return new LoginService(clientRepository).register(login, password) ?
            "{\"status\": \"ok\"}" : "Error";
    }
}
