package main.java.com.bazydanych.hotel.controllers;

import main.java.com.bazydanych.hotel.database.Connector;
import main.java.com.bazydanych.hotel.database.DatabaseQuery;
import main.java.com.bazydanych.hotel.model.Equipment;
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

	@GetMapping("/equipment/{equipmentId}")
	public Equipment getEquipment(@PathVariable("equipmentId") int id) {
		return repo.getEquipment(id);
	}

	@PostMapping(value = "/equipment", produces = "application/json")
	public Equipment insertEquipment(@RequestParam("name") String name) {
		Equipment equipment = new Equipment(0, name);

		return repo.persist(equipment) ? equipment : null;
	}

	@PutMapping(value = "/equipment/{equipmentId}", produces = "application/json")
	public Equipment updateEquipment(
		@PathVariable("equipmentId") int id,
		@RequestParam("name") String name
	) {
		Equipment equipment = new Equipment(id, name);

		return repo.persist(equipment) ? equipment : null;
	}
}