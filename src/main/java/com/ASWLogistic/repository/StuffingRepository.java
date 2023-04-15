package com.ASWLogistic.repository;

import com.ASWLogistic.model.ExtraFee;
import com.ASWLogistic.model.Stuffing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StuffingRepository extends JpaRepository<Stuffing, String> {
    List<Stuffing> findAll();
    Optional<Stuffing> findById(String id);

    @Query("SELECT s.containerCode FROM Stuffing s")
    List<String> findAllContainerCode();


    Stuffing getStuffingById(String id);

    void deleteStuffingById(String id);
}
