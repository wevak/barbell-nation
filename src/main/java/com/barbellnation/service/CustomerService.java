package com.barbellnation.service;

import java.util.List;

import com.barbellnation.dto.ApiResponse;
import com.barbellnation.dto.CustomerReqDTO;
import com.barbellnation.dto.CustomerRespDTO;
import com.barbellnation.entities.Customer;

public interface CustomerService {
	
    List<CustomerRespDTO> getAllCustomer(); 
    
    ApiResponse addNewCustomer(CustomerReqDTO crDTO);
    
    ApiResponse updateCustomer(Long id, CustomerReqDTO crDTO);
}
