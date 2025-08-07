package com.barbellnation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbellnation.entities.Trainer;
import com.barbellnation.service.TrainerService;

@RestController
@RequestMapping("/trainers")
@CrossOrigin(origins = "http://localhost:5173")
public class TrainerController {
	
	@Autowired
	private  TrainerService trainerService;
	
	public TrainerController() {
		System.out.println("in ctor" + getClass());
	}
	
	@GetMapping
	public ResponseEntity<?> getAllTrainers() {
		List<Trainer> trainers = trainerService.getAllTrainers();
		
		if(trainers.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
		return ResponseEntity.ok(trainers);
	}
}
