package com.donato.esercizio.esercizio26092024.model;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder

public class CreateRentalDTO {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
