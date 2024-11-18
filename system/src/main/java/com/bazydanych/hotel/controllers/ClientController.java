package main.java.com.bazydanych.hotel.controllers;

import main.java.com.bazydanych.hotel.database.Connector;
import main.java.com.bazydanych.hotel.database.DatabaseQuery;
import main.java.com.bazydanych.hotel.model.Client;
import main.java.com.bazydanych.hotel.repository.ClientRepository;
import org.springframework.web.bind.annotation.*;

import main.java.com.bazydanych.hotel.model.Room;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {
	DatabaseQuery query = new DatabaseQuery(new Connector());

	@GetMapping("/clients")
	public List<Client> getClients() {
		var repo = new ClientRepository(query);

		return repo.getClients();
	}

	@GetMapping("/client/{clientId}")
	public Client getClient(@PathVariable("clientId") int id) {
		var repo = new ClientRepository(query);

		return repo.getClient(id);
	}
}