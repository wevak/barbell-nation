package com.barbellnation.service;

import java.util.List;

import com.barbellnation.dto.ApiResponse;
import com.barbellnation.dto.PackageReqDTO;
import com.barbellnation.dto.PackageRespDTO;

public interface PackageService {
	   List<PackageRespDTO> getAllPackages();
	   
	   ApiResponse addNewPackage(PackageReqDTO packageEntity);
	   
	   PackageRespDTO packageGetById(Long id);
	   
	   ApiResponse updatePackageDetails(Long id, PackageReqDTO packageReq);
}
