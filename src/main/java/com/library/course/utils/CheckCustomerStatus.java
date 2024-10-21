package com.library.course.utils;

import com.library.course.entity.Customer;
import com.library.course.mapper.CustomerMapper;
import com.library.course.model.CustomerDTO;
import com.library.course.model.CustomerStatus;
import com.library.course.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckCustomerStatus {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    public CustomerDTO isCustomerEnabled(Long id)throws Exception{
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new Exception("Customer with id: " + id + " not found"));

        if(!customer.getStatus().equals(CustomerStatus.ENABLED)){
            throw new Exception("Customer is not enabled!");
        }
         return customerMapper.toCustomerDTO(customer);
    }
}
