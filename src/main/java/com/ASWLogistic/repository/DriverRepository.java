package com.ASWLogistic.repository;

import com.ASWLogistic.model.Driver;
import com.ASWLogistic.model.POD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DriverRepository extends JpaRepository<Driver, UUID> {
    List<Driver> findAll();
}
