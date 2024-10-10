package com.library.course.controller;

import com.library.course.model.*;
import com.library.course.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("")
    public ResponseEntity<?> addNewCustomer(@RequestBody @Valid CreateCustomerDTO createCustomerDTO) {
        try {
            CustomerDTO customerDTO = customerService.addNewCustomer(createCustomerDTO);
            return ResponseEntity.ok(customerDTO);
        } catch (Exception e) {
            log.error("Error in addNewCustomer {}", e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCustomerByID(@PathVariable("customerId") Long id) {
        try {
            CustomerDTO customerDTO = customerService.getCustomerDTOByID(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(customerDTO);
        } catch (Exception e) {
            log.error("Generic error in getCustomerDTOByID {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllCustomers() {
        try {
            List<CustomerDTO> customers = customerService.getAllCustomers();
            return ResponseEntity.status(HttpStatus.CREATED).body(customers);
        } catch (Exception e) {
            log.error("Generic error in getBookByTipology {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
