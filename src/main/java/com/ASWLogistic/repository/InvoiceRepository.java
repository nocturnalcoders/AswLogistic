package com.ASWLogistic.repository;

import com.ASWLogistic.model.ExtraFee;
import com.ASWLogistic.model.Invoice;
import com.ASWLogistic.model.InvoiceDetail;
import com.ASWLogistic.model.POD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {
    List<Invoice> findAll();

    Invoice getInvoiceById(String id);

    void deleteInvoiceById(String id);
}

