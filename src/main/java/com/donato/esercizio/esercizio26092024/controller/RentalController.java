package com.donato.esercizio.esercizio26092024.controller;


import com.donato.esercizio.esercizio26092024.model.CreateRentalDTO;
import com.donato.esercizio.esercizio26092024.model.RentalDTO;
import com.donato.esercizio.esercizio26092024.service.RentalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
@Slf4j
public class RentalController {

    private final RentalService rentalService;

    @PostMapping("/customers/{customerId}/books/{bookId}")
    public ResponseEntity<?> addRental (@PathVariable Long customerId, @PathVariable Long bookId, @RequestBody CreateRentalDTO createRentalDTO){
        try{
            RentalDTO rentalDTO = rentalService.createRentalDTO(customerId,bookId,createRentalDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(rentalDTO);

        }catch (Exception e){
            log.error("Error in addRental {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
