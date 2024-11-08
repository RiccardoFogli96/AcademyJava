package com.library.course.service;

import com.library.course.entity.Customer;
import com.library.course.mapper.CustomerMapper;
import com.library.course.model.CreateCustomerDTO;
import com.library.course.model.CustomerDTO;
import com.library.course.model.CustomerStatus;
import com.library.course.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerMapper customerMapper;
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO();
    CustomerDTO customerDTO;
    Customer customer;

    @BeforeEach
    void setUp() {
        customerDTO = CustomerDTO.builder()
                .email("customer@gmail.com")
                .firstName("Mario")
                .id(1L)
                .lastName("Bianchi")
                .build();

        customer = Customer.builder()
                .email("customer@gmail.com")
                .id(1L)
                .firstName("Mario")
                .lastName("Bianchi")
                .build();

        createCustomerDTO.setEmail("customer@gmail.com");
        createCustomerDTO.setFirstName("Mario");
        createCustomerDTO.setLastName("Bianchi");
    }

    @Test
    void addNewCustomer_ShouldReturnCustomerDT_WhenAllOk() {
        when(customerMapper.toCustomer(createCustomerDTO)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);

        customerService.addNewCustomer(createCustomerDTO);

        assertEquals(customerDTO.getEmail(), "customer@gmail.com");
    }

    @Test
    void getAllCustomers_ShouldThrowException_WhenCustomerListIsEmpty() throws Exception {
        List<Customer> customerList = new ArrayList<>();

        when(customerRepository.findAll()).thenReturn(customerList);
        Exception exception = assertThrows(Exception.class, () -> customerService.getAllCustomers());

        assertEquals("There are no customers", exception.getMessage());
    }

    @Test
    void getAllCustomers_ShouldReturnListCustomerDTO_WhenAllOk() throws Exception {
        when(customerRepository.findAll()).thenReturn(List.of(customer));
        when(customerMapper.toCustomerDTO(customer)).thenReturn(customerDTO);

        List<CustomerDTO> result = customerService.getAllCustomers();

        assertEquals(1, result.size());
    }

    @Test
    void getCustomerDTOByID_ShouldThrowException_WhenNoCustomerDTOFound() {
        when(customerRepository.findById(customer.getId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> customerService.getCustomerByID(1L));

        assertEquals("Customer with id: 1 not found.", exception.getMessage());
    }

    @Test
    void getCustomerDTOByID_ShouldReturnCustomerDTO_WhenAllOk() throws Exception {
        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        when(customerMapper.toCustomerDTO(customer)).thenReturn(customerDTO);

        CustomerDTO result = customerService.getCustomerDTOByID(1L);

        assertEquals(customerDTO, result);
    }

    @Test
    void getCustomerByID_ShouldThrowException_WhenCustomerNotFound() {

        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> customerService.getCustomerByID(1L));

        assertEquals("Customer with id: 1 not found.", exception.getMessage());
    }

    @Test
    void getCustomerById_ShouldReturnCustomer_WhenAllOk() throws Exception {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer result = customerService.getCustomerByID(1L);
        assertEquals(customer, result);
    }

    @Test
    void changeCustomerStatus_ShouldThrowException_WhenNoCustomerFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(Exception.class, () -> customerService.changeCustomerStatus(1L, CustomerStatus.ENABLED));
        assertEquals("Customer with id: 1 not found.", exception.getMessage());
    }

    @Test
    void changeCustomerStatus_ShouldReturnCustomerDTO_WhenAllOk()throws Exception{
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.toCustomerDTO(customer)).thenReturn(customerDTO);
        customerService.changeCustomerStatus(1L,CustomerStatus.ENABLED);

        assertEquals(customer.getStatus(),CustomerStatus.ENABLED);
    }
}