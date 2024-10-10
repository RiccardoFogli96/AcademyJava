package com.library.course.controller;


import com.library.course.model.CreateRentalDTO;
import com.library.course.model.RentalDTO;
import com.library.course.service.RentalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

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

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<?> getAllRentalsByCustomerId (@PathVariable Long customerId){
        try{
            List<RentalDTO> rentalDTO = rentalService.getAllRentalsByCustomerId(customerId);
            return ResponseEntity.status(HttpStatus.CREATED).body(rentalDTO);
        }catch (Exception e){
            log.error("Error in addRental {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllRentals(@RequestParam int page, @RequestParam int quantity){
        return ResponseEntity.status(HttpStatus.FOUND).body(rentalService.getAllRentalsPaginated(page,quantity));
    }

    /*
    tutti i rental che hanno il nome di un libro
     */
    @GetMapping("/filtered/")
    public ResponseEntity<?> getAllRentalsFiltered(@RequestParam int page, @RequestParam int quantity, @RequestParam String titolo){
        return ResponseEntity.status(HttpStatus.FOUND).body(rentalService.getAllRentalsPaginated(page,quantity,titolo));
    }
}
