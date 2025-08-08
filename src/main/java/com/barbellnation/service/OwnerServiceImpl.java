package com.barbellnation.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barbellnation.custom_exceptions.ResourceNotFoundException;
import com.barbellnation.dao.OwnerDao;
import com.barbellnation.dto.ApiResponse;
import com.barbellnation.dto.OwnerRespDTO;
import com.barbellnation.dto.UserReqDTO;
import com.barbellnation.entities.Owner;

import lombok.AllArgsConstructor;

@Service // => spring bean containing B.L
@Transactional
@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService {

	@Autowired
	private OwnerDao ownerDao;

	private ModelMapper modelMapper;

	private PasswordEncoder encoder;

	@Override
	public List<OwnerRespDTO> getAllOwners() {
		
		return ownerDao.findAll()
						.stream()
						.map(owner -> modelMapper.map(owner, OwnerRespDTO.class))
						.collect(Collectors.toList());
	}

	@Override
	public ApiResponse signUp(UserReqDTO dto) {
		Owner ownerEntity = modelMapper.map(dto, Owner.class);

		ownerEntity.setPassword(encoder.encode(ownerEntity.getPassword()));

		Owner persistentEntity = ownerDao.save(ownerEntity);
		
		return new ApiResponse("User registered with ID " + persistentEntity.getId());
	}

	@Override
	public OwnerRespDTO findOwnerById(long id) {

		Owner ownerEntity = ownerDao.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Invalid owner id!"));
		
		return modelMapper.map(ownerEntity, OwnerRespDTO.class);

	}
}
