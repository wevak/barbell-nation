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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbellnation.custom_exceptions.ResourceNotFoundException;
import com.barbellnation.dto.InventoryReqDTO;
import com.barbellnation.dto.InventoryRespDTO;
import com.barbellnation.service.InventoryService;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/inventory")
@CrossOrigin(origins = "http://localhost:5173")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    public InventoryController() {
        super();
    }

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<?> getAllInventoryItems() {
        try {
            List<InventoryRespDTO> items = inventoryService.getAllInventory();
            if (items.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResourceNotFoundException("No inventory items found"));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewInventoryItem(@RequestBody InventoryReqDTO newItem) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(inventoryService.addInventory(newItem));
        } catch (Exception e) {
        	return ResponseEntity.status(HttpStatus.CONFLICT).body(new RuntimeException(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInventoryById(@PathVariable @NotNull @Min(1) Long id) {
        try {
            return ResponseEntity.ok(inventoryService.getInventoryById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResourceNotFoundException("Inventory item not found"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInventory(@PathVariable Long id, @RequestBody InventoryReqDTO updatedItem) {
        try {
            return ResponseEntity.ok(inventoryService.updateInventory(id, updatedItem));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResourceNotFoundException("Inventory item not found"));
        }
    }
}
