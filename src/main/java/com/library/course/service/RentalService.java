package com.library.course.service;


import com.library.course.entity.Book;
import com.library.course.entity.Customer;
import com.library.course.entity.Rental;
import com.library.course.mapper.RentalMapper;
import com.library.course.model.BookDTO;
import com.library.course.model.CreateRentalDTO;
import com.library.course.model.CustomerDTO;
import com.library.course.model.RentalDTO;
import com.library.course.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RentalService {

    private final CustomerService customerService;
    private final BookService bookService;
    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;

    public RentalDTO createRentalDTO(Long customerId, Long bookId, CreateRentalDTO createRentalDTO) throws Exception {
        if (createRentalDTO == null) {
            throw new Exception("CreateRentalDTO is null");
        }
        Customer customer = customerService.getCustomerByID(customerId);
        Book book = bookService.getBookById(bookId);
        Rental rental = new Rental(createRentalDTO.getStartDate(), createRentalDTO.getEndDate(), customer, book);
        rentalRepository.save(rental);
        return rentalMapper.toDTO(rental);
    }

    public List<RentalDTO> getAllRentalsByCustomerId(Long customerId) throws Exception {
        customerService.getCustomerByID(customerId);
        List<Rental> rentals = rentalRepository.findByCustomer_Id(customerId);
        return rentals.stream().map(rentalMapper::toDTO).toList();
    }
}
