package com.donato.esercizio.esercizio26092024.controller;

import com.donato.esercizio.esercizio26092024.model.AuthorDTO;
import com.donato.esercizio.esercizio26092024.model.CreateAuthorDTO;
import com.donato.esercizio.esercizio26092024.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @GetMapping("/all-authors")
    public ResponseEntity<List<AuthorDTO>>  getAllAuthors(){
        List<AuthorDTO> authorDTOList = authorService.getAllAuthors();
        return ResponseEntity.status(HttpStatus.FOUND).body(authorDTOList);
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<?> getAuthorById(@PathVariable("authorId") Long id){
        try {
            AuthorDTO authorDTO = authorService.getAuthorById(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(authorDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PostMapping()
    public ResponseEntity<?> addNewAuthor(@RequestBody CreateAuthorDTO authorDTO){
        try{
            AuthorDTO newAuthor = authorService.addNewAuthor(authorDTO);
            return  ResponseEntity.status(HttpStatus.CREATED).body(newAuthor);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
