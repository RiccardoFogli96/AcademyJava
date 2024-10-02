package com.donato.esercizio.esercizio26092024.controller;


import com.donato.esercizio.esercizio26092024.model.CreateBookDTO;
import com.donato.esercizio.esercizio26092024.model.BookDTO;
import com.donato.esercizio.esercizio26092024.model.Tipologia;
import com.donato.esercizio.esercizio26092024.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping("/author/{authorId}")
    public ResponseEntity<?> addBookWithAuthor(@RequestBody @Valid CreateBookDTO createBookDTO, @PathVariable("authorId") Long id){

    }

    @PostMapping()
    public ResponseEntity<BookDTO> addBook(@RequestBody @Valid CreateBookDTO createBookDTO) {
        BookDTO bookDTO = bookService.addBook(createBookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable("id") Long id){
        try{
            BookDTO bookFound = bookService.getBookById(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(bookFound);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<List<BookDTO>> getBookByTipology(@RequestParam("tipologia") Tipologia tipologia){
        List <BookDTO> bookDTOS = bookService.getBookByTipology(tipologia);
        return  ResponseEntity.status(HttpStatus.FOUND).body(bookDTOS);
    }

}
