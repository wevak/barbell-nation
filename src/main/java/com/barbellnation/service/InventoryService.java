package com.barbellnation.service;

import java.util.List;

import com.barbellnation.dto.ApiResponse;
import com.barbellnation.dto.InventoryReqDTO;
import com.barbellnation.dto.InventoryRespDTO;

public interface InventoryService {

    // Add a new inventory item
    ApiResponse addInventory(InventoryReqDTO dto);

    // Get all inventory items
    List<InventoryRespDTO> getAllInventory();

    // Get inventory item by ID
    InventoryRespDTO getInventoryById(Long id);
    
    ApiResponse updateInventory(Long id, InventoryReqDTO inventoryReqDto);
}
