package com.library.course.service;

import com.library.course.entity.Author;
import com.library.course.mapper.AuthorMapper;
import com.library.course.model.AuthorDTO;
import com.library.course.model.CreateAuthorDTO;
import com.library.course.repository.AuthorRepository;
import com.library.course.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith( MockitoExtension.class)
class AuthorServiceTest {

	@Mock
	private AuthorRepository authorRepository;
	@Mock
	private AuthorMapper authorMapper;
	@Mock
	private BookRepository bookRepository;
	@Mock
	private JwtService jwtService;

	@InjectMocks
	private AuthorService authorService;

	private Author testAuthor;
	private AuthorDTO testAuthorDTO;
	private CreateAuthorDTO testCreateAuthor;
	private CreateAuthorDTO editAuthor;

	@BeforeEach
	void setUp() {
		testAuthor = Author.builder()
				.id(1L)
				.name("Mario")
				.surname("Rossi")
				.build();

		testAuthorDTO = AuthorDTO.builder()
				.name("Mario")
				.surname("Rossi")
				.build();

		testCreateAuthor = CreateAuthorDTO.builder()
				.name("Mario")
				.surname("Rossi")
				.build();

		editAuthor =CreateAuthorDTO.builder()
				.name("Piero")
				.surname("MaiPiu")
				.build();
	}

	@Test
	void test_getAuthorDTOById_everythingOk() throws Exception {
		when(authorRepository.findById(anyLong())).thenReturn(Optional.of(testAuthor));
		when(authorMapper.fromAuthorToDTO(testAuthor)).thenReturn(testAuthorDTO);

		AuthorDTO found = authorService.getAuthorDTOById(anyLong());
		assertEquals(testAuthorDTO, found);
	}

	@Test
	void test_getAuthorDTOById_AuthorNotFound(){
		when(authorRepository.findById(anyLong())).thenReturn(Optional.empty());

		Exception exception = assertThrows(Exception.class, () -> authorService.getAuthorDTOById(anyLong()));
		assertEquals("Author with Id 0 not found", exception.getMessage());
	}

	@Test
	void test_addAuthor_everythingOk() throws Exception {

		Author savedAuthor = Author.builder()
				.name("Mario")
				.surname("Rossi")
				.build();

		when(authorMapper.fromDTOtoAuthor(testCreateAuthor)).thenReturn(testAuthor);
		when(authorMapper.fromAuthorToDTO(testAuthor)).thenReturn(testAuthorDTO);
		when(authorRepository.save(testAuthor)).thenReturn(savedAuthor);

		 authorService.addNewAuthor(testCreateAuthor);

		 verify(authorRepository, times(1) ).save(testAuthor);

		 assertEquals("Mario", savedAuthor.getName());
	}

	@Test
	void test_addAuthor_whenCreateAuthorEmpty(){
		Exception exception = assertThrows(Exception.class, () -> authorService.addNewAuthor(new CreateAuthorDTO()));
		assertEquals("Either name or surname is null", exception.getMessage());
		verify(authorRepository, times(0)).save(testAuthor);
	}

	@Test
	void test_deleteAuthor(){
		long authorId = 1L;

		boolean result = authorService.deleteAuthor(authorId);

		assertTrue(result); // Verifica che il metodo ritorni `true`
		verify(bookRepository, times(1)).deleteByAuthorId(authorId); // Verifica che `deleteByAuthorId` sia stato chiamato con `authorId`
		verify(authorRepository, times(1)).deleteById(authorId); // Verifica che `deleteById` sia stato chiamato con `authorId`
	}

	@Test
	void test_updateAuthor_everythingOK() throws Exception {

		Author savedAuthor = Author.builder()
				.name("Piero")
				.surname("MaiPiu")
				.build();

		AuthorDTO savedAuthorDTO = AuthorDTO.builder()
				.name("Piero")
				.surname("MaiPiu")
				.build();

		when(authorRepository.existsById(anyLong())).thenReturn(true);
		when(authorMapper.fromDTOtoAuthor(editAuthor)).thenReturn(savedAuthor);
		when(authorRepository.save(savedAuthor)).thenReturn(savedAuthor);
		when(authorMapper.fromAuthorToDTO(savedAuthor)).thenReturn(savedAuthorDTO);

		AuthorDTO edited = authorService.updateAuthor(editAuthor, 1L);

		assertEquals(savedAuthorDTO, edited);
		verify(authorRepository, times(1) ).save(savedAuthor);
	}

	@Test
	void test_updateAuthor_whenAuthorDoesNotExist(){

		Exception exception = assertThrows(Exception.class, () -> authorService.updateAuthor(editAuthor, 1L));

		assertEquals("This Author doesn't exist", exception.getMessage());
	}

	@Test
	void test_findAllByID(){

		List <Long> authorIds = Arrays.asList(1L, 2L);

		List<Author> expectedAuthors = new ArrayList <>();

		expectedAuthors.add(testAuthor);
		expectedAuthors.add(new Author(2L, "Piero", "MaiPiu", "Mai più test", null, new ArrayList <>()));

		when(authorRepository.findAllByIdIn(authorIds)).thenReturn(expectedAuthors);

		List<Author> result = authorService.findAllById(authorIds);

		assertEquals(expectedAuthors, result);
		verify(authorRepository, times(1)).findAllByIdIn(authorIds);
	}

}