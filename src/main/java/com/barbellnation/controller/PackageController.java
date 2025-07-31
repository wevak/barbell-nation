package com.barbellnation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbellnation.custom_exceptions.ResourceNotFoundException;
import com.barbellnation.dto.PackageRespDTO;
import com.barbellnation.service.PackageService;

@RestController
@RequestMapping("/packages")
public class PackageController {

	@Autowired
	private PackageService packageService;

	public PackageController() {
		super();
	}

	public PackageController(PackageService packageService) {
		super();
		this.packageService = packageService;
	}

	@GetMapping
	public ResponseEntity<?> getAllPackages() {
		
		try {
			List<PackageRespDTO> packages = packageService.getAllPackages();
			if (packages.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}

			return ResponseEntity.ok(packages);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResourceNotFoundException("Package not found"));
		}

	}

}
