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
import com.barbellnation.dto.PackageReqDTO;
import com.barbellnation.dto.PackageRespDTO;
import com.barbellnation.service.PackageService;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/packages")
@CrossOrigin(origins = "http://localhost:5173")
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
	
	@PostMapping("/add")
	public ResponseEntity<?> addNewPackage(@RequestBody PackageReqDTO newPackage) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED)
								.body(packageService.addNewPackage(newPackage));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new RuntimeException(e.getMessage()));
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPackageById(@PathVariable @NotNull @Min(1) Long id) {
		try {
			return ResponseEntity.ok(packageService.packageGetById(id));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResourceNotFoundException("Package not found!"));
		}
	}


	@PutMapping("/{id}")
	public ResponseEntity<?> updatePackageDetails(@PathVariable Long id, @RequestBody PackageReqDTO newPackage) {
		
		try {
			return ResponseEntity.ok(packageService.updatePackageDetails(id, newPackage));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResourceNotFoundException("Package not found!"));
		}
	}
}
