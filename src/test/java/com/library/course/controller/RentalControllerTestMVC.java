package com.library.course.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.library.course.entity.Author;
import com.library.course.entity.Book;
import com.library.course.entity.Customer;
import com.library.course.mapper.BookMapper;
import com.library.course.mapper.CustomerMapper;
import com.library.course.mapper.RentalMapper;
import com.library.course.model.CreateRentalDTO;
import com.library.course.model.GenreBook;
import com.library.course.model.RentalDTO;
import com.library.course.repository.AuthorRepository;
import com.library.course.repository.BookRepository;
import com.library.course.repository.CustomerRepository;
import com.library.course.repository.RentalRepository;
import com.library.course.service.BookService;
import com.library.course.service.CustomerService;
import com.library.course.service.RentalService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class RentalControllerTestMVC {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RentalService rentalService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BookService bookService;
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private RentalMapper rentalMapper;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private BookMapper bookMapper;

    private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Test
    void addRental_WhenRentalDTOIsNotNullCreateNewRental() throws Exception {
        CreateRentalDTO createRentalDTO = CreateRentalDTO.builder().startDate(LocalDateTime.now()).endDate(LocalDateTime.of(2024,11,7,13,0)).build();
        Customer customer = Customer.builder().email("Customer@gmail.com").id(1L).firstName("Mario").lastName("Rossi").build();
        customerRepository.save(customer);
        Author author = author = Author.builder().name("Luigi").surname("Bianchi").id(1L).build();
        authorRepository.save(author);
        Book book = Book.builder().titolo("IT").genreBook(GenreBook.FANTASIA).id(1L).author(author).build();
        bookRepository.save(book);

        MvcResult result = mockMvc.perform(post("/rentals/customers/1/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRentalDTO))).andReturn();
        ;
        RentalDTO rentalDTO = objectMapper.readValue(result.getResponse().getContentAsString(),RentalDTO.class);
        Assertions.assertEquals(rentalDTO.getBook().getId(),1);
    }

    @Test
    void getAllRentalsByCustomerId() {
    }

    @Test
    void getAllRentals() {
    }

    @Test
    void getAllRentalsFiltered() {
    }
}