package com.ASWLogistic.service;

import com.ASWLogistic.constant.FieldConstants;
import com.ASWLogistic.dto.ClaimDTO;
import com.ASWLogistic.dto.ExtraFeeDTO;
import com.ASWLogistic.dto.InvoiceDTO;
import com.ASWLogistic.exception.throwable.NotFoundException;
import com.ASWLogistic.model.Claim;
import com.ASWLogistic.model.ExtraFee;
import com.ASWLogistic.model.Invoice;
import com.ASWLogistic.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;


    public Invoice addInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(invoiceDTO.getCustomerId());
        invoice.setReceivingNumber(invoiceDTO.getReceivingNumber());
        invoice.setPodId(invoiceDTO.getPodId());
        invoice.setDiscountFinal(invoiceDTO.getDiscountFinal());
        invoice.setDeliveryFee(invoiceDTO.getDeliveryFee());
        invoice.setTotal(invoiceDTO.getTotal());
        invoice.setBalance(invoiceDTO.getBalance());
        invoice.setStatus(invoiceDTO.getStatus());
        return invoiceRepository.save(invoice);
    }

    public Invoice getInvoiceById(String id) {
        return invoiceRepository.getInvoiceById(id);
    }

    public Invoice updateInvoicesById(String id, InvoiceDTO invoiceDTO) throws NotFoundException {
        Invoice invoice = invoiceRepository.getInvoiceById(id);
        if (invoice == null) {
            throw new NotFoundException(FieldConstants.ID, FieldConstants.ID);
        }
        invoice.setCustomerId(invoiceDTO.getCustomerId());
        invoice.setReceivingNumber(invoiceDTO.getReceivingNumber());
        invoice.setPodId(invoiceDTO.getPodId());
        invoice.setDiscountFinal(invoiceDTO.getDiscountFinal());
        invoice.setDeliveryFee(invoiceDTO.getDeliveryFee());
        invoice.setTotal(invoiceDTO.getTotal());
        invoice.setBalance(invoiceDTO.getBalance());
        invoice.setStatus(invoiceDTO.getStatus());
        return invoiceRepository.save(invoice);
    }

    public void deleteInvoiceById(String id) {
        invoiceRepository.deleteInvoiceById(id);
    }

    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }



}
