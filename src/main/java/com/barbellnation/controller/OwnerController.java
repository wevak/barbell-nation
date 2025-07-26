package com.barbellnation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbellnation.entities.Owner;
import com.barbellnation.service.OwnerService;

@RestController
@RequestMapping("/owners")
public class OwnerController {
	
	@Autowired
	private OwnerService ownerService;
	
	public OwnerController() {
		System.out.println("in ctor" + getClass());
	}
	
	@GetMapping
	public ResponseEntity<?> getAllOwners(){
		
		List<Owner> owners = ownerService.getAllOwners();
		if(owners.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
		return ResponseEntity.ok(owners);
		
	}
	
}
