package com.library.course.service;


import com.library.course.entity.Book;
import com.library.course.entity.Customer;
import com.library.course.entity.Rental;
import com.library.course.mapper.RentalMapper;
import com.library.course.model.CreateRentalDTO;
import com.library.course.model.CustomerDTO;
import com.library.course.model.RentalDTO;
import com.library.course.repository.RentalRepository;
import com.library.course.utils.CheckCustomerStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RentalService {

    private final CustomerService customerService;
    private final BookService bookService;
    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;
    private final CheckCustomerStatus checkCustomerStatus;

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
        checkCustomerStatus.isCustomerEnabled(customerId);
        List<Rental> rentals = rentalRepository.findByCustomer_Id(customerId);
        return rentals.stream().map(rentalMapper::toDTO).toList();
    }

    public Page<RentalDTO> getAllRentalsPaginated(int page, int quantity) {
        Pageable pageable = PageRequest.of(page, quantity).withSort(Sort.by("book.titolo"));
        Page<Rental> rental = rentalRepository.findAll(pageable);

        return rental.map(rentalMapper::toDTO);
    }

    public Page<RentalDTO> getAllRentalsPaginated(int page, int quantity, String titolo) {
        Pageable pageable = PageRequest.of(page, quantity).withSort(Sort.by("book.titolo"));
        Page<Rental> rental = rentalRepository.findRentalsFilteredFalse(titolo,pageable);
        return rental.map(rentalMapper::toDTO);
    }


}
