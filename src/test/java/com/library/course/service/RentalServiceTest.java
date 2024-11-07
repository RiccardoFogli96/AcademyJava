package com.library.course.service;

import com.library.course.entity.Book;
import com.library.course.entity.Customer;
import com.library.course.entity.Rental;
import com.library.course.mapper.RentalMapper;
import com.library.course.model.CreateRentalDTO;
import com.library.course.model.GenreBook;
import com.library.course.model.RentalDTO;
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

import static org.mockito.ArgumentMatchers.any;
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

    @InjectMocks
    private RentalService rentalService;

    @Test
    void createRentalDTO_WhenRentalDTOIsNotNullCreateNewRental() throws Exception {

        Customer customer = Customer.builder().email("Customer@gmail.com").id(1L).firstName("Mario").lastName("Rossi").build();
        Book book = Book.builder().titolo("IT").genreBook(GenreBook.FANTASIA).id(1L).build();
        CreateRentalDTO createRentalDTO = CreateRentalDTO.builder().startDate(LocalDateTime.now()).endDate(LocalDateTime.of(2024,11,7,13,0)).build();
        when(customerService.getCustomerByID(1L)).thenReturn(customer);
        when(bookService.getBookById(1L)).thenReturn(book);
        Rental rental = new Rental(createRentalDTO.getStartDate(), createRentalDTO.getEndDate(), customer, book);
        when(rentalRepository.save(any(Rental.class))).thenReturn(rental);
        RentalDTO rentalDTO = new RentalDTO();
        //when(rentalMapper.toDTO(any(Rental.class))).thenReturn(rentalDTO);

        rentalService.createRentalDTO(customer.getId(), book.getId(),createRentalDTO);


        Assertions.assertEquals(createRentalDTO.getStartDate(),rental.getStartDate());
    }

    @Test
    void createRentalDTO_WhenRentalDTOIsNull_ThrowException() {

    }

    @Test
    void getAllRentalsByCustomerId() {
    }

    @Test
    void getAllRentalsPaginated() {
    }

    @Test
    void testGetAllRentalsPaginated() {
    }
}