package com.barbellnation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import com.barbellnation.dto.CustomerReqDTO;
import com.barbellnation.dto.CustomerRespDTO;
import com.barbellnation.entities.Customer;
import com.barbellnation.service.CustomerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerController {
	
	@Autowired
	private  CustomerService customerService;
	
	@GetMapping
	public ResponseEntity<?> getAllCustomer(){
		try {
			List<CustomerRespDTO> data = customerService.getAllCustomer();
			if(data.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			return ResponseEntity.ok(data);
			
		} catch (Exception e) {
			 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResourceNotFoundException("Customer not found" ));
		}
		 
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addNewCustomer(@RequestBody CustomerReqDTO customer){
		 
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addNewCustomer(customer));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new RuntimeException(e));
		}
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody CustomerReqDTO customer){
		  try {
			   return ResponseEntity.ok(customerService.updateCustomer(id, customer));
		} catch (RuntimeException e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new ResourceNotFoundException("updation failed"));
		}
	}
	

}
