package com.example.bank.service;

import com.example.bank.model.Customer;
import com.example.bank.repository.CustomerRepository;
import com.example.bank.dto.CustomerDTO;
import com.example.bank.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(CustomerDTO customerDTO) {
        if (customerRepository.existsByDocument(customerDTO.getDocument())) {
            throw new RuntimeException("Document already exists");
        }
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setDocument(customerDTO.getDocument());
        customer.setAddress(customerDTO.getAddress());
        customer.setPassword(customerDTO.getPassword());
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }
}