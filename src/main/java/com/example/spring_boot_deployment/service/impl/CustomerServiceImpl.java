package com.example.spring_boot_deployment.service.impl;

import com.example.spring_boot_deployment.dto.CustomerDTO;
import com.example.spring_boot_deployment.entity.Customer;
import com.example.spring_boot_deployment.exception.ResourceNotFound;
import com.example.spring_boot_deployment.repository.CustomerRepository;
import com.example.spring_boot_deployment.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CustomerDTO> getAllCustomers() {
        try {
            List<Customer> allJobs = customerRepository.findAll();
            return modelMapper.map(allJobs, new TypeToken<List<CustomerDTO>>(){}.getType());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve all Customers. Error: " + e.getMessage(), e);
        }
    }

    @Override
    public CustomerDTO findCustomer(Long id) {
        return customerRepository.findById(id)
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .orElseThrow(() -> new ResourceNotFound("Customer with ID " + id + " not found."));
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
        try {
            if (!customerRepository.existsById(customer.getId())) {
                throw new ResourceNotFound("Customer with ID " + customer.getId() + " not found for update.");
            }
            Customer updatedCustomer = customerRepository.save(modelMapper.map(customer, Customer.class));
            return modelMapper.map(updatedCustomer, CustomerDTO.class);
        } catch (ResourceNotFound e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update customer. Error: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteCustomer(Long id) {
        try {
            if (!customerRepository.existsById(id)) {
                throw new ResourceNotFound("Customer with ID " + id + " not found for deletion.");
            }
            customerRepository.deleteById(id);
        } catch (ResourceNotFound e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete customer by ID. Error: " + e.getMessage(), e);
        }
    }
}
