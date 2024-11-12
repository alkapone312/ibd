package main.java.com.bazydanych.controllers;

import main.java.com.bazydanych.database.Connector;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import main.java.com.bazydanych.hotel.model.Room;

@RestController
@RequestMapping("/api")
public class HelloController {
	@GetMapping("/")
	public Room handle() {
		new Connector();

		return new Room(2, 10, 10.0, 10, 1);
	}
}