package com.donato.esercizio.esercizio26092024.service;


import com.donato.esercizio.esercizio26092024.entity.Book;
import com.donato.esercizio.esercizio26092024.entity.Customer;
import com.donato.esercizio.esercizio26092024.entity.Rental;
import com.donato.esercizio.esercizio26092024.mapper.RentalMapper;
import com.donato.esercizio.esercizio26092024.model.BookDTO;
import com.donato.esercizio.esercizio26092024.model.CreateRentalDTO;
import com.donato.esercizio.esercizio26092024.model.CustomerDTO;
import com.donato.esercizio.esercizio26092024.model.RentalDTO;
import com.donato.esercizio.esercizio26092024.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


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
        CustomerDTO customerDTO = customerService.getCustomerByID(customerId);
        BookDTO bookDTO = bookService.getBookById(bookId);
        RentalDTO rental = new RentalDTO(createRentalDTO.getStartDate(), createRentalDTO.getEndDate(), customerDTO, bookDTO);
        rentalRepository.save(rentalMapper.toRental(rental));
        return rental;
    }

}
