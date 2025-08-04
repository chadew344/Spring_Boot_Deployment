package com.example.spring_boot_deployment.controller;

import com.example.spring_boot_deployment.dto.CustomerDTO;
import com.example.spring_boot_deployment.service.CustomerService;
import com.example.spring_boot_deployment.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping (path = "get-all")
    public ResponseEntity<APIResponse<List<CustomerDTO>>> getAllJob(){
        return ResponseEntity.ok( new APIResponse<>(
                200,
                "Success: List Fetched Successfully",
                customerService.getAllCustomers()
        ));
    }

    @PostMapping("create")
    public ResponseEntity<APIResponse<CustomerDTO>> createJob(@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<>(new APIResponse<>(
                201,
                "Successfully Created",
                customerService.createCustomer(customerDTO)
        ), HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<APIResponse<CustomerDTO>> updateJob(@ModelAttribute CustomerDTO customerDTO){
        return ResponseEntity.ok(new APIResponse<>(
                201,
                "Success: Customer Updated",
                customerService.updateCustomer(customerDTO)
        ));
    }

    @GetMapping("search/{id}")
    public ResponseEntity<APIResponse<CustomerDTO>>  updateJob(@PathVariable String id){
        return ResponseEntity.ok( new APIResponse<>(
                200,
                "Success: Customer Searched",
                 customerService.findCustomer(Long.valueOf(id))
        ));
    }

    @DeleteMapping("remove/{id}")
    public  ResponseEntity<APIResponse<String>>  deleteJob(@PathVariable String id){
        customerService.deleteCustomer(Long.valueOf(id));
        return  ResponseEntity.ok(new APIResponse<>(
                201,
                "Success: Customer Deleted",
                null
        ));
    }


}
