package com.donato.esercizio.esercizio26092024.mapper;

import com.donato.esercizio.esercizio26092024.entity.Customer;
import com.donato.esercizio.esercizio26092024.entity.Rental;
import com.donato.esercizio.esercizio26092024.model.RentalDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RentalMapper {

    private final BookMapper bookMapper;
    private final CustomerMapper customerMapper;

    public Rental toRental(RentalDTO rentalDTO) {

        return Rental
                .builder()
                .rentId(rentalDTO.getId())
                .customer(customerMapper.toCustomer(rentalDTO.getCustomer()))
                .book(bookMapper.toBook(rentalDTO.getBook()))
                .startDate(rentalDTO.getStartDate())
                .endDate(rentalDTO.getEndDate())
                .build();
    }
}
