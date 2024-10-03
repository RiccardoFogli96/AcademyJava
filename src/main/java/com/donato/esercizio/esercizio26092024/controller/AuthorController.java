package com.donato.esercizio.esercizio26092024.controller;

import com.donato.esercizio.esercizio26092024.model.AuthorDTO;
import com.donato.esercizio.esercizio26092024.model.CreateAuthorDTO;
import com.donato.esercizio.esercizio26092024.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    //private Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @GetMapping("/all-authors")
    public ResponseEntity<List<AuthorDTO>>  getAllAuthors(){
        List<AuthorDTO> authorDTOList = authorService.getAllAuthors();
        log.debug("Get all Authors");
        return ResponseEntity.status(HttpStatus.FOUND).body(authorDTOList);
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<?> getAuthorById(@PathVariable("authorId") Long id){
        try {
            AuthorDTO authorDTO = authorService.getAuthorById(id);
            log.debug("Author with id: {} found", id);
            return ResponseEntity.status(HttpStatus.FOUND).body(authorDTO);
        }catch (Exception e){
            log.error("Error in getting Author by Id: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PostMapping()
    public ResponseEntity<?> addNewAuthor(@RequestBody CreateAuthorDTO authorDTO){
        try{
            AuthorDTO newAuthor = authorService.addNewAuthor(authorDTO);
            log.debug("Author added in database {}", authorDTO);
            return  ResponseEntity.status(HttpStatus.CREATED).body(newAuthor);
        }catch(Exception e){
            log.error("Error in add new Author {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity deleteAuthor(@PathVariable ("authorId") Long id){
        authorService.deleteAuthor(id);
        log.debug("Author with Id {} deleted", id);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{authorID}")
    public ResponseEntity<?> updateAuthor(@PathVariable ("authorID") Long id, @RequestBody AuthorDTO createAuthorDTO){
        try{
            return ResponseEntity.ok(authorService.updateAuthor(createAuthorDTO, id));
        } catch (Exception e){
            log.error("Error in update author with id {}", id, e);
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
