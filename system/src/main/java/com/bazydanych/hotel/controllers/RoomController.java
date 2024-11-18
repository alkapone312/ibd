package main.java.com.bazydanych.hotel.controllers;

import main.java.com.bazydanych.hotel.database.Connector;
import main.java.com.bazydanych.hotel.database.DatabaseQuery;
import main.java.com.bazydanych.hotel.model.Room;
import main.java.com.bazydanych.hotel.repository.RoomRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoomController {
	DatabaseQuery query = new DatabaseQuery(new Connector());
	RoomRepository repo = new RoomRepository(query);

	@GetMapping("/rooms")
	public List<Room> getRooms() {
		return repo.getRooms();
	}

	@GetMapping("/room/{roomId}")
	public Room getRoom(@PathVariable("roomId") int id) {
		return repo.getRoom(id);
	}

	@PostMapping("/room")
	public Room insertRoom(
		@RequestParam("roomSize") int roomSize,
		@RequestParam("basePrice") double basePrice,
		@RequestParam("capacity") int capacity,
		@RequestParam("roomNumber") int roomNumber,
		@RequestParam("name") String name,
		@RequestParam("description") String description
	) {
		Room room = new Room(
			0,
			roomSize,
			basePrice,
			capacity,
			roomNumber,
			name,
			description
		);

		return repo.persist(room) ? room : null;
	}

	@PutMapping("/room/{roomId}")
	public Room updateRoom(
		@PathVariable("roomId") int id,
		@RequestParam("roomSize") int roomSize,
		@RequestParam("basePrice") double basePrice,
		@RequestParam("capacity") int capacity,
		@RequestParam("roomNumber") int roomNumber,
		@RequestParam("name") String name,
		@RequestParam("description") String description
	) {
		Room room = new Room(
			0,
			roomSize,
			basePrice,
			capacity,
			roomNumber,
			name,
			description
		);

		return repo.persist(room) ? room : null;
	}
}