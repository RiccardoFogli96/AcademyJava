package com.library.course.service;

import com.library.course.entity.Customer;
import com.library.course.mapper.CustomerMapper;
import com.library.course.model.CreateCustomerDTO;
import com.library.course.model.CustomerDTO;
import com.library.course.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerMapper customerMapper;
	private final CustomerRepository customerRepository;

	public CustomerDTO addNewCustomer (CreateCustomerDTO customerDTO){
		Customer customer = customerMapper.toCustomer(customerDTO);
		customerRepository.save(customer);
		return customerMapper.toCustomerDTO(customer);
	}

	public List <CustomerDTO> getAllCustomers () throws Exception {
		List<Customer> customers = customerRepository.findAll();
		if(customers.isEmpty()){
			throw new Exception("There are no customers");
		}
		return customers
				.stream()
				.map(customerMapper::toCustomerDTO)
				.toList();
	}

	public CustomerDTO getCustomerByID (Long id) throws Exception {
		Customer found = customerRepository
				.findById(id)
				.orElseThrow(() -> new Exception ("Customer not found"));
		return customerMapper.toCustomerDTO(found);
	}

}
