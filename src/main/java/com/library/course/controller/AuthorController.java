package com.library.course.controller;

import com.library.course.model.AuthorDTO;
import com.library.course.model.CreateAuthorDTO;
import com.library.course.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthorController {

	private final AuthorService authorService;

	@Operation( summary = "GetAllAuthors" )
	@GetMapping( "/private/author/all-authors" )
	public ResponseEntity < List < AuthorDTO > > getAllAuthors( HttpServletRequest request ) {
		request.getAttribute("email");
		List < AuthorDTO > authorDTOList = authorService.getAllAuthors();
		log.debug("Get all Authors");
		return ResponseEntity.status(HttpStatus.OK).body(authorDTOList);
	}

	@GetMapping( "/public/author/{authorId}" )
	public ResponseEntity < AuthorDTO > getAuthorById( @PathVariable( "authorId" ) Long id ) throws Exception {
		AuthorDTO authorDTO = authorService.getAuthorDTOById(id);
		log.debug("Author with id: {} found", id);
		return ResponseEntity.status(HttpStatus.OK).body(authorDTO);
	}

	@PostMapping( "/author" )
	public ResponseEntity < AuthorDTO > addNewAuthor( @RequestBody CreateAuthorDTO authorDTO ) throws Exception {
		AuthorDTO newAuthor = authorService.addNewAuthor(authorDTO);
		log.debug("Author added in database {}", authorDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(newAuthor);
	}

	@DeleteMapping( "/author/{authorId}" )
	public ResponseEntity < Boolean > deleteAuthor( @PathVariable( "authorId" ) Long id ) {
		boolean deleted = authorService.deleteAuthor(id);
		log.debug("Author with Id {} deleted", id);
		return ResponseEntity.status(200).body(deleted);
	}

	@PutMapping( "/author/{authorID}" )
	public ResponseEntity < AuthorDTO > updateAuthor( @PathVariable( "authorID" ) Long id, @RequestBody AuthorDTO createAuthorDTO ) throws Exception {
		return ResponseEntity.ok(authorService.updateAuthor(createAuthorDTO, id));
	}
}
