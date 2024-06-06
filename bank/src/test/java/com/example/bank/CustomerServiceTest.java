package com.example.bank;

import com.example.bank.service.CustomerService;
import com.example.bank.dto.CustomerDTO;
import com.example.bank.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void testCreateCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Test Name");
        customerDTO.setDocument("12345678901");
        customerDTO.setAddress("Test Address");
        customerDTO.setPassword("password");

        Customer customer = customerService.createCustomer(customerDTO);
        assertNotNull(customer);
        assertEquals("Test Name", customer.getName());
    }
}