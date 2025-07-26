package com.barbellnation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barbellnation.entities.Trainer;

public interface TrainerDao extends JpaRepository<Trainer, Long> {

}
