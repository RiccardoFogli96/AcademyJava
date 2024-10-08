package com.donato.esercizio.esercizio26092024.service;


import com.donato.esercizio.esercizio26092024.entity.Book;
import com.donato.esercizio.esercizio26092024.entity.Customer;
import com.donato.esercizio.esercizio26092024.model.BookDTO;
import com.donato.esercizio.esercizio26092024.model.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class RentalService {

private final CustomerService customerService;
private final BookService bookService;

    public RentalDTO createRentalDTO (Long customerId, Long bookId) throws Exception {
        CustomerDTO customerDTO = customerService.getCustomerByID(customerId);
        BookDTO bookDTO  = bookService.getBookById(bookId);



    }

}
