package com.library.course.mapper;

import com.library.course.entity.Rental;
import com.library.course.model.RentalDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RentalMapper {

    private final BookMapper bookMapper;
    private final CustomerMapper customerMapper;

    /*
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
     */

    public RentalDTO toDTO(Rental rental) {
        return RentalDTO
                .builder()
                .id(rental.getRentId())
                .customer(customerMapper.toCustomerDTO(rental.getCustomer()))
                .book(bookMapper.fromBookToDTO(rental.getBook()))
                .startDate(rental.getStartDate())
                .endDate(rental.getEndDate())
                .build();
    }
}
