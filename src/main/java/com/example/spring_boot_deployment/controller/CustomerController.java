package com.example.spring_boot_deployment.controller;

import com.example.spring_boot_deployment.dto.CustomerDTO;
import com.example.spring_boot_deployment.service.CustomerService;
import com.example.spring_boot_deployment.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@CrossOrigin
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public String getCustomer() {
        return "Hello World!";
    }

    @PostMapping("create")
    public ResponseEntity<APIResponse<CustomerDTO>> createJob(@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<>(new APIResponse<>(
                201,
                "Successfully Created",
                customerService.createCustomer(customerDTO)
        ), HttpStatus.CREATED);
    }



}
