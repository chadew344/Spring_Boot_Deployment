package com.example.spring_boot_deployment.service;

import com.example.spring_boot_deployment.dto.CustomerDTO;
import com.example.spring_boot_deployment.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();
    Customer findCustomer(Long id);
    CustomerDTO createCustomer(CustomerDTO customer);
    CustomerDTO updateCustomer(CustomerDTO customer);
    void deleteCustomer(Long id);
}
