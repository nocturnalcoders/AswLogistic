package com.ASWLogistic.repository;

import com.ASWLogistic.model.Claim;
import com.ASWLogistic.model.ExtraFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExtraFeeRepository extends JpaRepository<ExtraFee, String> {
    List<ExtraFee> findAll();

    ExtraFee getExtraFeeById(String id);

    void deleteExtraFeeById(String id);
}
