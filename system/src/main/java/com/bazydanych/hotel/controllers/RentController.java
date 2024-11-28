package main.java.com.bazydanych.hotel.controllers;

import main.java.com.bazydanych.hotel.database.Connector;
import main.java.com.bazydanych.hotel.database.DatabaseQuery;
import main.java.com.bazydanych.hotel.model.Client;
import main.java.com.bazydanych.hotel.model.Reservation;
import main.java.com.bazydanych.hotel.repository.ClientRepository;
import main.java.com.bazydanych.hotel.repository.RentRepository;
import main.java.com.bazydanych.hotel.repository.RoomRepository;
import main.java.com.bazydanych.hotel.services.LoginService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RentController {
    DatabaseQuery q = new DatabaseQuery(new Connector());
    LoginService loginService = new LoginService(new ClientRepository(q));
    RentRepository repository = new RentRepository(q);
    RoomRepository roomRepository = new RoomRepository(q);

    @PostMapping("/rent/{roomId}")
    public String rentRoom(
        @PathVariable("roomId") int roomId,
        @RequestParam("dateFrom") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom,
        @RequestParam("dateTo") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo,
        @RequestParam("price") double price,
        @RequestHeader("Authorization") String token
    ) {
        if(dateFrom.before(new Date()) || dateTo.before(dateFrom)) {
            return "{\"error\": \"Błąd\", \"code\": 403}";
        }

        Client client = loginService.getClient(token);

        if(client == null) {
            return "{\"error\": \"Błąd\", \"code\": 403}";
        }

        repository.rentRoom(new Reservation(
            0,
            client,
            roomRepository.getRoom(roomId),
            dateFrom,
            dateTo
        ), price);

        return "{\"status\": \"ok\"}";
    }

    @GetMapping("/reservations")
    public List<Reservation> getReservations(@RequestHeader("Authorization") String token) {
        Client client = loginService.getClient(token);
        if(client.getPrivilege().equals("ADMIN")) {
            return repository.getRents();
        }

        return null;
    }

    @GetMapping("/myReservations")
    public List<Reservation> getReservationsForClient(@RequestHeader("Authorization") String token) {
        Client client = loginService.getClient(token);

        return client != null ? repository.getRentsForClient(client) : null;
    }

    @DeleteMapping("/reservation")
    public String cancelReservation(@RequestParam("reservationId") int id) {
        return repository.cancelRent(id) ? "{\"status\": \"ok\"}" : "{\"error\": \"Nie można anulować rezerwacji!\"}";
    }
}
