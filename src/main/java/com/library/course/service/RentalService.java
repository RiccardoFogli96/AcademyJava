package com.library.course.service;


import com.library.course.entity.Book;
import com.library.course.entity.Customer;
import com.library.course.entity.Rental;
import com.library.course.mapper.BookMapper;
import com.library.course.mapper.CustomerMapper;
import com.library.course.mapper.RentalMapper;
import com.library.course.model.BookDTO;
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
    private final BookMapper bookMapper;
    private final CustomerMapper customerMapper;

    public RentalDTO createRentalDTO(Long customerId, Long bookId, CreateRentalDTO createRentalDTO) throws Exception {

        Customer customer = customerService.getCustomerByID(customerId);
        CustomerDTO customerDTO = customerMapper.toCustomerDTO(customer);
        if (createRentalDTO == null) {
            throw new Exception("CreateRentalDTO is null");
        }
        Book book = bookService.getBookById(bookId);
        BookDTO bookDTO = bookMapper.fromBookToDTO(book);
        Rental rental = new Rental(createRentalDTO.getStartDate(), createRentalDTO.getEndDate(), customer, book);
        rentalRepository.save(rental);
        return rentalMapper.toDTO(rental,customerDTO,bookDTO);
    }

    public List<RentalDTO> getAllRentalsByCustomerId(Long customerId) throws Exception {
        checkCustomerStatus.isCustomerEnabled(customerId);
        List<Rental> rentals = rentalRepository.findByCustomer_Id(customerId);
        return rentals.stream().map(r -> rentalMapper.toDTO(r,null,null)).toList();
    }

    public Page<RentalDTO> getAllRentalsPaginated(int page, int quantity) {
        Pageable pageable = PageRequest.of(page, quantity).withSort(Sort.by("book.titolo"));
        Page<Rental> rental = rentalRepository.findAll(pageable);

        return rental.map(r -> rentalMapper.toDTO(r,null,null));
    }

    public Page<RentalDTO> getAllRentalsPaginated(int page, int quantity, String titolo) {
        Pageable pageable = PageRequest.of(page, quantity).withSort(Sort.by("book.titolo"));
        Page<Rental> rental = rentalRepository.findRentalsFilteredFalse(titolo,pageable);
        return rental.map(r -> rentalMapper.toDTO(r,null,null));
    }


}
