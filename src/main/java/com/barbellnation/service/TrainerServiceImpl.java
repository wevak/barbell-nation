package com.barbellnation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barbellnation.dao.TrainerDao;
import com.barbellnation.entities.Trainer;

@Service // => spring bean containing B.L
@Transactional
public class TrainerServiceImpl implements TrainerService {
	
	@Autowired
	private TrainerDao trainerDao;
	
	@Override
	public List<Trainer> getAllTrainers()
	{
		return trainerDao.findAll();
	}
}
