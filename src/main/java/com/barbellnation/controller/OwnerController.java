package com.barbellnation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbellnation.custom_exceptions.ResourceNotFoundException;
import com.barbellnation.dto.OwnerRespDTO;
import com.barbellnation.entities.Owner;
import com.barbellnation.service.OwnerService;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/owners")
@CrossOrigin(origins = "http://localhost:5173")
public class OwnerController {
	
	@Autowired
	private OwnerService ownerService;
	
	public OwnerController() {
		System.out.println("in ctor" + getClass());
	}
	
	@GetMapping
	public ResponseEntity<?> getAllOwners(){
		try {
			List<OwnerRespDTO> owners = ownerService.getAllOwners();
			if(owners.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			
			return ResponseEntity.ok(owners);
			  
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResourceNotFoundException("owner list not found"));
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOwnerById(@PathVariable @NotNull @Min(1) Long id){
		try {
			return ResponseEntity.ok(ownerService.findOwnerById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResourceNotFoundException("Package not found!"));
		}
	}
	
}
