package com.barbellnation.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.barbellnation.custom_exceptions.ResourceNotFoundException;
import com.barbellnation.dao.CustomerDao;
import com.barbellnation.dao.PackageDao;
import com.barbellnation.dto.ApiResponse;
import com.barbellnation.dto.CustomerReqDTO;
import com.barbellnation.dto.CustomerRespDTO;
import com.barbellnation.entities.Customer;
import com.barbellnation.entities.Package;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
@Service
@Transactional
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;
    private final ModelMapper modelMapper;
    private final PackageDao packageDao;



	
	
	@Override
	public List<CustomerRespDTO> getAllCustomer() {

		return customerDao.findAll()
				.stream()
				.map(customer -> modelMapper.map(customer, CustomerRespDTO.class))
				.collect(Collectors.toList());
	}





	@Override
	public ApiResponse addNewCustomer(CustomerReqDTO crDTO) {
		// TODO Auto-generated method stub
		
		Package pkg = packageDao.findById(crDTO.getPackageId())
				.orElseThrow(()-> new ResourceNotFoundException("Invalid Package id hai bhaiya id sahi dalo"));
		Customer customer = modelMapper.map(crDTO, Customer.class);
		
		pkg.addCustomer(customer);
		return new ApiResponse("Customer Added Successfully");
	}





	@Override
	public ApiResponse updateCustomer(Long id, CustomerReqDTO crDTO) {
		// TODO Auto-generated method stub
		Package pkg = packageDao.findById(crDTO.getPackageId()).
				orElseThrow(()->new ResourceNotFoundException("Invalid Package id"));
		Customer customer = customerDao.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Invalid customer id"));
		
		customer.setPackageId(pkg);
		if(customer.getPackageId()!=null) {
			customer.getPackageId().getCustomers().remove(customer);
		}
		pkg.getCustomers().add(customer);
		
		modelMapper.map(crDTO, customer);
		return new ApiResponse("Customer update successfully");
	}




}
