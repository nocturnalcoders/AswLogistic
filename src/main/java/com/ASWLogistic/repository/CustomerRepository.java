package com.ASWLogistic.repository;

import com.ASWLogistic.model.Claim;
import com.ASWLogistic.model.Customer;
import com.ASWLogistic.model.ExtraFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    List<Customer> findAll();

    Customer getCustomerById(String id);

    void deleteCustomerById(String id);
}
