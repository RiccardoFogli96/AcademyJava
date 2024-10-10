package com.library.course.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalDTO {

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private CustomerDTO customer;
    private BookDTO book;

    public RentalDTO(LocalDateTime startDate, LocalDateTime endDate, CustomerDTO customer, BookDTO book) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = customer;
        this.book = book;
    }
}
