package com.barbellnation.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.barbellnation.custom_exceptions.ResourceNotFoundException;
import com.barbellnation.dao.InventroyDao;
import com.barbellnation.dao.OwnerDao;
import com.barbellnation.dto.ApiResponse;
import com.barbellnation.dto.InventoryReqDTO;
import com.barbellnation.dto.InventoryRespDTO;
import com.barbellnation.entities.Inventory;
import com.barbellnation.entities.Owner;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {

	private final InventroyDao inventoryDao;
	private final OwnerDao ownerDao;
	private final ModelMapper modelMapper;

	public InventoryServiceImpl(InventroyDao inventoryDao, OwnerDao ownerDao, ModelMapper modelMapper) {
		this.inventoryDao = inventoryDao;
		this.ownerDao = ownerDao;
		this.modelMapper = modelMapper;
	}
//	@Override
//	public ApiResponse addNewPackage(PackageReqDTO packageEntity) {
//		
//		//to check valid owner id
//		Owner owner = ownerDao.findById(packageEntity.getOwnerId())
//							.orElseThrow(() -> new ResourceNotFoundException("Invalid owner id!"));
//		
//		Package newEntity = modelMapper.map(packageEntity, Package.class);
//		
//		owner.addPackage(newEntity);
//		return new ApiResponse("package added to owner");
//	}
	@Override
	public ApiResponse addInventory(InventoryReqDTO dto) {
		Owner owner = ownerDao.findById(dto.getOwnerId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid owner id"));

		Inventory inventory = modelMapper.map(dto, Inventory.class);
		owner.addInventory(inventory); // Ensure addInventory method is implemented in Owner
		modelMapper.map(inventory, InventoryRespDTO.class);
		return new ApiResponse("Inventory added to gym");
	}

	@Override
	public List<InventoryRespDTO> getAllInventory() {
		return inventoryDao.findAll()
				.stream()
				.map(inv -> modelMapper.map(inv, InventoryRespDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public InventoryRespDTO getInventoryById(Long id) {
		Inventory inv = inventoryDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Inventory ID"));
		return modelMapper.map(inv, InventoryRespDTO.class);
	}



	@Override
	public ApiResponse updateInventory(Long id, InventoryReqDTO inventoryReqDto) {
		Inventory existingInventory = inventoryDao.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Invalid Inventory Id"));
		Owner existingOwner = ownerDao.findById(inventoryReqDto.getOwnerId())
				.orElseThrow(()-> new ResourceNotFoundException("Invalid owner Id"));
		existingInventory.setOwnerId(existingOwner);
		if (existingInventory.getOwnerId() != null) {
			existingInventory.getOwnerId().getPackages().remove(existingInventory); // Remove from old order's collection
		}
		existingOwner.getInventories().add(existingInventory);
		modelMapper.map(inventoryReqDto, existingInventory);
		return new ApiResponse("Update Successfully");
	}
}
