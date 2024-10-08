package com.donato.esercizio.esercizio26092024.controller;


import com.donato.esercizio.esercizio26092024.model.CreateRentalDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    @PostMapping("/customers/{customerId}/books/{bookId}")
    public ResponseEntity<?> addRental (@PathVariable Long customerId, @PathVariable Long bookId){
        CreateRentalDTO createRentalDTO =


    }
}
