package com.donato.esercizio.esercizio26092024.mapper;

import com.donato.esercizio.esercizio26092024.entity.Customer;
import com.donato.esercizio.esercizio26092024.model.CreateCustomerDTO;
import com.donato.esercizio.esercizio26092024.model.CustomerDTO;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

	public Customer toCustomer ( CreateCustomerDTO createCustomerDTO ){

		return Customer.builder()
				.email(createCustomerDTO.getEmail())
				.firstName(createCustomerDTO.getFirstName())
				.lastName(createCustomerDTO.getLastName())
				.build();
	}

	public CustomerDTO toCustomerDTO (Customer customer){
		return CustomerDTO.builder()
				.email(customer.getEmail())
				.id(customer.getId())
				.firstName(customer.getFirstName())
				.rentals(customer.getRentals())
				.lastName(customer.getLastName())
				.build();
	}

	public Customer toCustomer(CustomerDTO customerDTO){
		return Customer
				.builder()
				.email(customerDTO.getEmail())
				.id(customerDTO.getId())
				.firstName(customerDTO.getFirstName())
				.rentals(customerDTO.getRentals())
				.lastName(customerDTO.getLastName())
				.build();
	}
}
