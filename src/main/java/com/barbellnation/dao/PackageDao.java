package com.barbellnation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageDao extends JpaRepository<com.barbellnation.entities.Package, Long> {

}
