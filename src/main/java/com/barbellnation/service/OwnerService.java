package com.barbellnation.service;

import java.util.List;

import com.barbellnation.dto.ApiResponse;
import com.barbellnation.dto.OwnerRespDTO;
import com.barbellnation.dto.UserReqDTO;
import com.barbellnation.entities.Owner;

public interface OwnerService {
	// to get all owners
	List<OwnerRespDTO> getAllOwners();
	
	ApiResponse signUp(UserReqDTO dto);
}
