package com.barbellnation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barbellnation.entities.Owner;

public interface OwnerDao extends JpaRepository<Owner, Long> {

}
