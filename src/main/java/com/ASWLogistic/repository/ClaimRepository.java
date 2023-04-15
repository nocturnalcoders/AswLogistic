package com.ASWLogistic.repository;

import com.ASWLogistic.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, String> {
    List<Claim> findAll();

    Claim getClaimsById(String id);

    void deleteClaimById(String id);

}
