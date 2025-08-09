package com.barbellnation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barbellnation.entities.Trainer;
import java.util.List;


public interface TrainerDao extends JpaRepository<Trainer, Long> {
     List<Trainer> findByPackageId(Package packageId);
} 
