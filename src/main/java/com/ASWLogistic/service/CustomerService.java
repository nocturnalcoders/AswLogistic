package com.ASWLogistic.service;

import com.ASWLogistic.constant.ErrorMessageConstants;
import com.ASWLogistic.constant.FieldConstants;
import com.ASWLogistic.dto.ClaimDTO;
import com.ASWLogistic.dto.CustomerDTO;
import com.ASWLogistic.dto.ExtraFeeDTO;
import com.ASWLogistic.exception.throwable.BadRequestException;
import com.ASWLogistic.exception.throwable.NotFoundException;
import com.ASWLogistic.model.Claim;
import com.ASWLogistic.model.Customer;
import com.ASWLogistic.model.ExtraFee;
import com.ASWLogistic.model.Stuffing;
import com.ASWLogistic.repository.ClaimRepository;
import com.ASWLogistic.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;


    public Customer getCustomerById(String id) {
        return customerRepository.getCustomerById(id);
    }

    public Customer addCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setAddress(customerDTO.getAddress());
        customer.setMarking(customerDTO.getMarking());
        customer.setProvince(customerDTO.getProvince());
        customer.setAccountNumber(customerDTO.getAccountNumber());
        return customerRepository.save(customer);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }


    public Customer updateCustomerById(String id, CustomerDTO customerDTO) throws NotFoundException {
        Customer customer = customerRepository.getCustomerById(id);
        if (customer == null) {
            throw new NotFoundException(FieldConstants.ID, FieldConstants.ID);
        }
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setAddress(customerDTO.getAddress());
        customer.setMarking(customerDTO.getMarking());
        customer.setProvince(customerDTO.getProvince());
        customer.setAccountNumber(customerDTO.getAccountNumber());
        return customerRepository.save(customer);
    }
    public void deleteCustomerById(String id) {
        customerRepository.deleteCustomerById(id);
    }

}
