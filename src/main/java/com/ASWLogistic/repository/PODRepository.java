package com.ASWLogistic.repository;

import com.ASWLogistic.model.POD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PODRepository extends JpaRepository<POD, UUID> {
    POD findByContainerCode(String containerCode);
    List<POD> findAll();
}
