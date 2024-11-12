package main.java.com.bazydanych.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import main.java.com.bazydanych.hotel.model.Room;

@RestController
public class HelloController {
	@GetMapping("/")
	public Room handle() {
		return new Room(2, "Pokój #2");
	}
}