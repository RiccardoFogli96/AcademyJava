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
    @Autowired
    AuthorService authorService;

    @GetMapping("/all-authors")
    public ResponseEntity<List<AuthorDTO>>  getAllAuthors(){
        return ResponseEntity.status(HttpStatus.FOUND).body(authorService.getAllAuthors());
    }
    @GetMapping("/{authorId}")
    public ResponseEntity<?> getAuthorById(@PathVariable("authorId") Long id){
        try {
            return ResponseEntity.status(HttpStatus.FOUND).body(authorService.getAuthorById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping()
    public ResponseEntity<?> addNewAuthor(@RequestBody CreateAuthorDTO authorDTO){
        try{
            return  ResponseEntity.status(HttpStatus.CREATED).body(authorService.addNewAuthor(authorDTO));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
