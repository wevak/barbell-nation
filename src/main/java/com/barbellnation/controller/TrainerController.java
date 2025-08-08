package com.barbellnation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbellnation.custom_exceptions.ResourceNotFoundException;
import com.barbellnation.dto.TrainerReqDTO;
import com.barbellnation.dto.TrainerRespDTO;
import com.barbellnation.service.TrainerService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/trainers")
@CrossOrigin(origins = "http://localhost:5173")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    public TrainerController() {
        super();
    }

    public TrainerController(TrainerService trainerService) {
        super();
        this.trainerService = trainerService;
    }

    @GetMapping
    public ResponseEntity<?> getAllTrainers() {
        try {
            List<TrainerRespDTO> trainers = trainerService.getAllTrainers();
            if (trainers.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(trainers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResourceNotFoundException("Trainers not found"));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTrainer(@Valid @RequestBody TrainerReqDTO trainerReqDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(trainerService.addTrainer(trainerReqDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RuntimeException(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTrainerById(@PathVariable @NotNull @Min(1) Long id) {
        try {
            return ResponseEntity.ok(trainerService.getTrainerById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResourceNotFoundException("Trainer not found!"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTrainer(@PathVariable Long id, @Valid @RequestBody TrainerReqDTO trainerReqDTO) {
        try {
            return ResponseEntity.ok(trainerService.updateTrainer(id, trainerReqDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResourceNotFoundException("Trainer not found!"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrainer(@PathVariable @NotNull @Min(1) Long id) {
        try {
            trainerService.deleteTrainer(id);
            return ResponseEntity.ok("Trainer deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResourceNotFoundException("Trainer not found!"));
        }
    }
}
