package com.example.spring_boot_deployment.service.impl;

import com.example.spring_boot_deployment.dto.CustomerDTO;
import com.example.spring_boot_deployment.entity.Customer;
import com.example.spring_boot_deployment.repository.CustomerRepository;
import com.example.spring_boot_deployment.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return List.of();
    }

    @Override
    public Customer findCustomer(Long id) {
        return null;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        try {
            Customer savedCustomer = customerRepository.save(modelMapper.map(customerDTO, Customer.class));
            return modelMapper.map(savedCustomer, CustomerDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save job. Error: " + e.getMessage(), e);
        }
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customer) {
        return null;
    }

    @Override
    public void deleteCustomer(Long id) {

    }
}
