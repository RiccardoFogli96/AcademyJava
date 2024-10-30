package com.library.course.controller;

import com.library.course.model.AuthorDTO;
import com.library.course.model.CreateAuthorDTO;
import com.library.course.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @Operation( summary = "GetAllAuthors" )
    @GetMapping("/private/author/all-authors")
    public ResponseEntity<List<AuthorDTO>>  getAllAuthors( HttpServletRequest request ){
        request.getAttribute("email");
        List<AuthorDTO> authorDTOList = authorService.getAllAuthors();
        log.debug("Get all Authors");
        return ResponseEntity.status(HttpStatus.OK).body(authorDTOList);
    }

    @GetMapping("/public/author/{authorId}")
    public ResponseEntity<?> getAuthorById(@PathVariable("authorId") Long id){
        try {
            AuthorDTO authorDTO = authorService.getAuthorDTOById(id);
            log.debug("Author with id: {} found", id);
            return ResponseEntity.status(HttpStatus.OK).body(authorDTO);
        }catch (Exception e){
            log.error("Error in getting Author by Id: {}", e.getMessage(), e);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/author")
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

    @DeleteMapping("/author/{authorId}")
    public ResponseEntity deleteAuthor(@PathVariable ("authorId") Long id){
        authorService.deleteAuthor(id);
        log.debug("Author with Id {} deleted", id);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/author/{authorID}")
    public ResponseEntity<?> updateAuthor(@PathVariable ("authorID") Long id, @RequestBody AuthorDTO createAuthorDTO){
        try{
            return ResponseEntity.ok(authorService.updateAuthor(createAuthorDTO, id));
        } catch (Exception e){
            log.error("Error in update author with id {}", id, e);
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
