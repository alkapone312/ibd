package main.java.com.bazydanych.hotel.controllers;

import main.java.com.bazydanych.hotel.database.Connector;
import main.java.com.bazydanych.hotel.database.DatabaseQuery;
import main.java.com.bazydanych.hotel.model.Equipment;
import main.java.com.bazydanych.hotel.model.PricingIncrease;
import main.java.com.bazydanych.hotel.repository.EquipmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EquipmentController {
	DatabaseQuery query = new DatabaseQuery(new Connector());
	EquipmentRepository repo = new EquipmentRepository(query);

	@GetMapping("/equipments")
	public List<Equipment> getEquipments() {
		return repo.getEquipments();
	}

	@GetMapping("/equipment/room/{roomId}")
	public List<Equipment> getEquipmentForRoom(@PathVariable("roomId") int id) {
		return repo.getEquipmentForRoom(id);
	}

	@GetMapping("/equipment/{equipmentId}")
	public Equipment getEquipment(@PathVariable("equipmentId") int id) {
		return repo.getEquipment(id);
	}

	@GetMapping("/equipment/pricingIncrease/{equipmentId}")
	public PricingIncrease getPricingIncreaseForAdditionalEquipment(@PathVariable("equipmentId") int id) {
		return repo.getPricingIncreaseForEquipment(id);
	}

	@PostMapping("/equipment/room/{roomId}")
	public Equipment addEquipmentToRoom(
		@PathVariable("roomId") int roomId,
		@RequestParam("equipmentId") int equipmentId
	) {
		return repo.addEquipmentToRoom(roomId, equipmentId) ? repo.getEquipment(equipmentId) : null;
	}

	@DeleteMapping("/equipment/room/{roomId}")
	public boolean deleteEquipmentFromRoom(@PathVariable("roomId") int id) {
		return repo.deleteEquipmentFromRoom(id);
	}

	@PostMapping(value = "/equipment", produces = "application/json")
	public Equipment insertEquipment(
		@RequestParam("name") String name,
		@RequestParam("increase") double increase,
		@RequestParam("increaseType") String type
	) {
		Equipment equipment = new Equipment(0, name);
		PricingIncrease inc = new PricingIncrease(0, increase, type);

		return repo.persist(equipment, inc) ? equipment : null;
	}

	@PutMapping(value = "/equipment/{equipmentId}", produces = "application/json")
	public Equipment updateEquipment(
		@PathVariable("equipmentId") int id,
		@RequestParam("name") String name,
		@RequestParam("increase") double increase,
		@RequestParam("increaseType") String type
	) {
		Equipment equipment = new Equipment(id, name);
		PricingIncrease inc = new PricingIncrease(id, increase, type);

		return repo.persist(equipment, inc) ? equipment : null;
	}
}