package main.java.com.bazydanych.hotel.controllers;

import main.java.com.bazydanych.hotel.database.Connector;
import main.java.com.bazydanych.hotel.database.DatabaseQuery;
import main.java.com.bazydanych.hotel.model.Reservation;
import main.java.com.bazydanych.hotel.repository.ClientRepository;
import main.java.com.bazydanych.hotel.repository.ReservationRepository;
import main.java.com.bazydanych.hotel.repository.RoomRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ReservationController {
	DatabaseQuery query = new DatabaseQuery(new Connector());
	ReservationRepository repo = new ReservationRepository(query);
	ClientRepository repoClient = new ClientRepository(query);
	RoomRepository repoRoom = new RoomRepository(query);

	@GetMapping("/reservations")
	public List<Reservation> getReservations() {
		return repo.getReservations();
	}

	@GetMapping("/reservation/{reservationId}")
	public Reservation getReservation(@PathVariable("reservationId") int id) {
		return repo.getReservation(id);
	}

	@PostMapping("/reservation")
	public Reservation insertReservation(
		@RequestParam("client_id") int clientId,
		@RequestParam("room_id") int roomId,
		@RequestParam("checkin_date") Date checkinDate,
		@RequestParam("checkout_date") Date checkoutDate
	) {
		Reservation reservation = new Reservation(
			0,
			repoClient.getClient(clientId),
			repoRoom.getRoom(roomId),
			checkinDate,
			checkoutDate
		);

		return repo.persist(reservation) ? reservation : null;
	}

	@PutMapping("/reservation/{reservationId}")
	public Reservation updateReservation(
		@PathVariable("reservationId") int id,
		@PathVariable("client_id") int clientId,
		@RequestParam("room_id") int roomId,
		@RequestParam("checkin_date") Date checkinDate,
		@RequestParam("checkout_date") Date checkoutDate

	) {
		Reservation reservation = new Reservation(
			id,
			repoClient.getClient(clientId),
			repoRoom.getRoom(roomId),
			checkinDate,
			checkoutDate
		);

		return repo.persist(reservation) ? reservation : null;
	}
}