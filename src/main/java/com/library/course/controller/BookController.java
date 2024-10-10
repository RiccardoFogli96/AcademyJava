package com.library.course.controller;


import com.library.course.model.CreateBookDTO;
import com.library.course.model.BookDTO;
import com.library.course.model.ModifyBookDTO;
import com.library.course.model.Tipologia;
import com.library.course.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private Logger logger = LoggerFactory.getLogger(BookController.class);

    @PostMapping("/author/{authorId}")
    public ResponseEntity<?> addBookWithAuthor(@RequestBody @Valid CreateBookDTO createBookDTO, @PathVariable("authorId") Long id){
        try{
            BookDTO newBookDTO = bookService.addBookWithAuthor(createBookDTO, id);
            logger.info("Added a Book with Author's Id {}", id);
            return ResponseEntity.ok(newBookDTO);
        }catch (Exception e){
            logger.error("Error in add book with Author {}", e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<?> addBook(@RequestBody @Valid CreateBookDTO createBookDTO) {
        try{
            BookDTO bookDTO = bookService.addBook(createBookDTO);
            logger.info("Added a Book");
            return ResponseEntity.status(HttpStatus.CREATED).body(bookDTO);
        } catch (Exception e){
            logger.error("Generic error in add book {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable("id") Long id){
        try{
            BookDTO bookFound = bookService.getBookById(id);
            logger.info("Book with Id {} found", id);
            return ResponseEntity.status(HttpStatus.FOUND).body(bookFound);
        } catch (Exception e) {
            logger.error("Error in get book by Id {}", e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<List<BookDTO>> getBookByTipology(@RequestParam("tipologia") Tipologia tipologia){
        List <BookDTO> bookDTOS = bookService.getBookByTipology(tipologia);
        logger.info("Books with tipology {} found", tipologia);
        return  ResponseEntity.status(HttpStatus.FOUND).body(bookDTOS);
    }

    @PatchMapping("/manage/{authorId}")
    public ResponseEntity<?> changeTitleByAuthorId(@PathVariable("authorId") Long id, ModifyBookDTO modifyBookDTO){
        try{
            BookDTO bookDTO = bookService.changeTitleToBookDTO(id,modifyBookDTO);
            logger.info("Change title by author's Id {} done", id);
            return ResponseEntity.status(HttpStatus.OK).body(bookDTO);
        }catch (Exception e){
            logger.error("Error in change title by author's Id {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
