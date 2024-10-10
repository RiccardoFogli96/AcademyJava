package com.library.course.service;


import com.library.course.mapper.RentalMapper;
import com.library.course.model.BookDTO;
import com.library.course.model.CreateRentalDTO;
import com.library.course.model.CustomerDTO;
import com.library.course.model.RentalDTO;
import com.library.course.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


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
