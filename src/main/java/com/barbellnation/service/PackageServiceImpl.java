package com.barbellnation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barbellnation.custom_exceptions.ResourceNotFoundException;
import com.barbellnation.dao.OwnerDao;
import com.barbellnation.dao.PackageDao;
import com.barbellnation.dto.ApiResponse;
import com.barbellnation.dto.PackageReqDTO;
import com.barbellnation.dto.PackageRespDTO;
import com.barbellnation.entities.Owner;
import com.barbellnation.entities.Package;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
public class PackageServiceImpl implements PackageService {

	private final OwnerDao ownerDao;
	private final PackageDao packageDao;
	private final ModelMapper modelMapper;

	public PackageServiceImpl(PackageDao packageDao, OwnerDao ownerDao, ModelMapper modelMapper) {
		super();
		this.packageDao = packageDao;
		this.ownerDao = ownerDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<PackageRespDTO> getAllPackages() {

		return packageDao.findAll()
				.stream()
				.map(pkg -> modelMapper.map(pkg, PackageRespDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public ApiResponse addNewPackage(PackageReqDTO packageEntity) {
		
		//to check valid owner id
		Owner owner = ownerDao.findById(packageEntity.getOwnerId())
							.orElseThrow(() -> new ResourceNotFoundException("Invalid owner id!"));
		
		Package newEntity = modelMapper.map(packageEntity, Package.class);
		
		owner.addPackage(newEntity);
		return new ApiResponse("package added to owner");
	}

	@Override
	public PackageRespDTO packageGetById(Long id) {
		com.barbellnation.entities.Package packageEntity = packageDao.findById(id)
							.orElseThrow(() -> new ResourceNotFoundException("Invalid package id!"));
		return modelMapper.map(packageEntity, PackageRespDTO.class);
	}




		@Override
		public ApiResponse updatePackageDetails(Long id, PackageReqDTO packageReq) {

			Package existingPackage = packageDao.findById(id)
					.orElseThrow(()-> new ResourceNotFoundException("Invalid Package Id"));

			Owner existingOwner = ownerDao.findById(packageReq.getOwnerId())
					.orElseThrow(()-> new ResourceNotFoundException("Invalid owner Id"));
			
			existingPackage.setOwnerId(existingOwner);
			
			if (existingPackage.getOwnerId() != null) {
				existingPackage.getOwnerId().getPackages().remove(existingPackage); // Remove from old order's collection
			}
			existingOwner.getPackages().add(existingPackage); // Add to new order's collection

			
			modelMapper.map(packageReq, existingPackage);
			return new ApiResponse("Update Successfully");
		}
}
