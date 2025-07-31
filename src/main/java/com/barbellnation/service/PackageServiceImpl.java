package com.barbellnation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barbellnation.dao.PackageDao;
import com.barbellnation.dto.PackageRespDTO;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
public class PackageServiceImpl implements PackageService {

	private final PackageDao packageDao;
	private final ModelMapper modelMapper;

	public PackageServiceImpl(PackageDao packageDao, ModelMapper modelMapper) {
		super();
		this.packageDao = packageDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<PackageRespDTO> getAllPackages() {

		return packageDao.findAll()
				.stream()
				.map(pkg -> modelMapper.map(pkg, PackageRespDTO.class))
				.collect(Collectors.toList());
	}

}
