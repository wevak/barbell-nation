package com.barbellnation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barbellnation.entities.Inventory;

public interface InventroyDao extends JpaRepository<Inventory, Long> {

}
