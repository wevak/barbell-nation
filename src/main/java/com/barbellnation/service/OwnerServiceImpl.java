package com.barbellnation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barbellnation.dao.OwnerDao;
import com.barbellnation.entities.Owner;

@Service // => spring bean containing B.L
@Transactional
public class OwnerServiceImpl implements OwnerService {
	
	@Autowired
	private OwnerDao ownerDao;
	
	@Override
	public List<Owner> getAllOwners()
	{
		return ownerDao.findAll();
	}
}
