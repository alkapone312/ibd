package main.java.com.bazydanych.hotel.controllers;

import main.java.com.bazydanych.hotel.database.Connector;
import main.java.com.bazydanych.hotel.database.DatabaseQuery;
import main.java.com.bazydanych.hotel.model.Equipment;
import main.java.com.bazydanych.hotel.model.PricingIncrease;
import main.java.com.bazydanych.hotel.repository.AdditionalEquipmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdditionalEquipmentController {
	DatabaseQuery query = new DatabaseQuery(new Connector());
	AdditionalEquipmentRepository repo = new AdditionalEquipmentRepository(query);

	@GetMapping("/additionalEquipments/{reservationId}")
	public List<Equipment> getEquipmentForReservation(@PathVariable int id) {
		return repo.getEquipmentForReservation(id);
	}

	@GetMapping("/additionalEquipment/{equipmentId}")
	public Equipment getEquipment(@PathVariable("equipmentId") int id) {
		return repo.getEquipment(id);
	}

	@GetMapping("/additionalEquipments")
	public List<Equipment> getEquipments() {
		return repo.getEquipments();
	}

	@GetMapping("/additionalEquipment/pricingIncrease/{additionalEquipmentId}")
	public PricingIncrease getPricingIncreaseForAdditionalEquipment(@PathVariable("additionalEquipmentId") int id) {
		return repo.getPricingIncreaseForAdditionalEquipment(id);
	}

	@PostMapping("/equipment/reservation/{reservationId}")
	public Equipment addAdditionalEquipmentToReservation(
		@PathVariable("reservationId") int reservationId,
		@RequestParam("equipmentId") int equipmentId
	) {
		return repo.addEquipmentToRoom(reservationId, equipmentId) ? repo.getEquipment(equipmentId) : null;
	}

	@PostMapping(value = "/additionalEquipment", produces = "application/json")
	public Equipment insertEquipment(
		@RequestParam("name") String name,
		@RequestParam("increase") double increase,
		@RequestParam("increaseType") String increaseType
	) {
		Equipment additionalEquipment = new Equipment(0, name);
		PricingIncrease pricingIncrease = new PricingIncrease(0, increase, increaseType);

		return repo.persist(additionalEquipment, pricingIncrease) ? additionalEquipment : null;
	}

	@PutMapping(value = "/additionalEquipment/{additionalEquipmentId}", produces = "application/json")
	public Equipment updateEquipment(
		@PathVariable("additionalEquipmentId") int id,
		@RequestParam("name") String name,
		@RequestParam("increase") double increase,
		@RequestParam("increaseType") String increaseType
	) {
		Equipment additionalEquipment = new Equipment(id, name);
		PricingIncrease pricingIncrease = new PricingIncrease(id, increase, increaseType);

		return repo.persist(additionalEquipment, pricingIncrease) ? additionalEquipment : null;
	}
}