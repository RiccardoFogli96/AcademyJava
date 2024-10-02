package com.donato.esercizio.esercizio26092024.controller;

import com.donato.esercizio.esercizio26092024.model.AuthorDTO;
import com.donato.esercizio.esercizio26092024.model.CreateAuthorDTO;
import com.donato.esercizio.esercizio26092024.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor

public class AuthorController {

    private final AuthorService authorService;
    private Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @GetMapping("/all-authors")
    public ResponseEntity<List<AuthorDTO>>  getAllAuthors(){
        List<AuthorDTO> authorDTOList = authorService.getAllAuthors();
        logger.info("Get all Authors");
        return ResponseEntity.status(HttpStatus.FOUND).body(authorDTOList);
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<?> getAuthorById(@PathVariable("authorId") Long id){
        try {
            AuthorDTO authorDTO = authorService.getAuthorById(id);
            logger.info("Author with id: {} found", id);
            return ResponseEntity.status(HttpStatus.FOUND).body(authorDTO);
        }catch (Exception e){
            logger.error("Error in getting Author by Id: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PostMapping()
    public ResponseEntity<?> addNewAuthor(@RequestBody CreateAuthorDTO authorDTO){
        try{
            AuthorDTO newAuthor = authorService.addNewAuthor(authorDTO);
            logger.info("Author added in database");
            return  ResponseEntity.status(HttpStatus.CREATED).body(newAuthor);
        }catch(Exception e){
            logger.error("Error in add new Author {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{authorID}")
    public ResponseEntity deleteAuthorAndBooks(@PathVariable("authorID") Long id){
    authorService.deleteAuthorAndBooks(id);
    logger.info("Author with Id {} deleted with his books", id);
    return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity deleteAuthor(@PathVariable ("authorId") Long id){
        authorService.deleteAuthor(id);
        logger.info("Author with Id {} deleted", id);
        return ResponseEntity.status(200).build();
    }
}
