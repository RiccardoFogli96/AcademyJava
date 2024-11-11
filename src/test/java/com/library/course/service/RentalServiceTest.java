package com.library.course.service;

import com.library.course.entity.Book;
import com.library.course.entity.Customer;
import com.library.course.entity.Rental;
import com.library.course.mapper.BookMapper;
import com.library.course.mapper.CustomerMapper;
import com.library.course.mapper.RentalMapper;
import com.library.course.model.*;
import com.library.course.repository.RentalRepository;
import com.library.course.utils.CheckCustomerStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@ExtendWith(MockitoExtension.class)
class RentalServiceTest {

    @Captor
    private ArgumentCaptor<Rental> rentalArgumentCaptor;
    @Captor
    private ArgumentCaptor<RentalDTO> rentalDTOArgumentCaptor;

    @Mock
    private CustomerService customerService;
    @Mock
    private BookService bookService;
    @Mock
    private RentalRepository rentalRepository;
    @Mock
    private RentalMapper rentalMapper;
    @Mock
    private CheckCustomerStatus checkCustomerStatus;
    @Mock
    private CustomerMapper customerMapper;
    @Mock
    private BookMapper bookMapper;


    @InjectMocks
    private RentalService rentalService;

    @Test
    void createRentalDTO_WhenRentalDTOIsNotNullCreateNewRental() throws Exception {

        Customer customer = Customer.builder().email("Customer@gmail.com").id(1L).firstName("Mario").lastName("Rossi").build();
        Book book = Book.builder().titolo("IT").genreBook(GenreBook.FANTASIA).id(1L).build();
        CreateRentalDTO createRentalDTO = CreateRentalDTO.builder().startDate(LocalDateTime.now()).endDate(LocalDateTime.of(2024,11,7,13,0)).build();
        when(customerService.getCustomerByID(1L)).thenReturn(customer);
        when(bookService.getBookById(1L)).thenReturn(book);
        when(bookMapper.fromBookToDTO(any(Book.class))).thenReturn(new BookDTO()); //mi assicuro che bookMapper quando chiamato nel test torni un BookDTO
        Rental rental = new Rental(createRentalDTO.getStartDate(), createRentalDTO.getEndDate(), customer, book);
        when(rentalRepository.save(any(Rental.class))).thenReturn(rental);
        RentalDTO rentalDTO = new RentalDTO();
         //when(rentalMapper.toDTO(any(Rental.class))).thenReturn(rentalDTO);

        rentalService.createRentalDTO(customer.getId(), book.getId(),createRentalDTO);


        Assertions.assertEquals(createRentalDTO.getStartDate(),rental.getStartDate());
    }

    @Test
    void createRentalDTO_WhenRentalDTOIsNull_ThrowException() throws Exception {

        CreateRentalDTO createRentalDTO = null;
        Customer customer = Customer.builder().email("Customer@gmail.com").id(1L).firstName("Mario").lastName("Rossi").build();
        CustomerDTO customerDTO = CustomerDTO.builder()
                .id(1L)
                .firstName("Mario")
                .lastName("Rossi")
                .email("Customer@gmail.com")
                .build();

        Long customerId = 1L;

        when(customerService.getCustomerByID(customerId)).thenReturn(customer);
        when(customerMapper.toCustomerDTO(customer)).thenReturn(customerDTO);

        String errorMessage = null;

        try {
            rentalService.createRentalDTO(customerId, 1L, createRentalDTO);
        } catch (Exception exception){

            errorMessage = exception.getMessage();
        }

        Exception exception = Assertions.assertThrows(Exception.class, ()-> rentalService.createRentalDTO(customerId, 1L, createRentalDTO));

        Assertions.assertEquals("CreateRentalDTO is null", exception.getMessage());
        Assertions.assertEquals("CreateRentalDTO is null", errorMessage);
    }

    @Test
    void getAllRentalsByCustomerId_WhenCustomerEnabled_andRentalsPresent() throws Exception {
        Long customerId = 1L; //Definisco un customerId x usarlo nelle chiamate del test
        // CustomerDTO customerDTO = new CustomerDTO(); non indispensabile, posso istanziarlo direttamente nel mock
        //non mi interessa costruire un customer vero e proprio perchè non sto verificando quello ora.
        when(checkCustomerStatus.isCustomerEnabled(customerId)).thenReturn(new CustomerDTO());
        // Rental rental1 = new Rental(); non necessario come x customerDTO
        // Rental rental2 = new Rental(); //
        List<Rental> rentals = List.of(new Rental(), new Rental()); //lista di Rental con 2 noleggi generici
        when(rentalRepository.findByCustomer_Id(customerId)).thenReturn(rentals); //mi assicuro che venga tornata la lista

        when(rentalMapper.toDTO(any(Rental.class), isNull(), isNull())).thenReturn(new RentalDTO()); //simulo conversione in RentalDTO

        List<RentalDTO> actualRentalDTOList = rentalService.getAllRentalsByCustomerId(customerId); //chiamo il metodo testato e lo salvo
        Assertions.assertEquals(rentals.size(), actualRentalDTOList.size()); //verifico che sia tornata correttamente la lista paragonandone le dimensioni
    }

    @Test
    void getAllRentalsPaginated() {
    }

    @Test
    void testGetAllRentalsPaginated() {
    }
}