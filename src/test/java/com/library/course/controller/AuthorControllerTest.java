package com.library.course.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.course.model.AuthorDTO;
import com.library.course.model.CreateAuthorDTO;
import com.library.course.service.AuthorService;
import com.library.course.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
class AuthorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AuthorService authorService;

	@MockBean
	private JwtService jwtService;

	@MockBean CheckUserInterceptor checkUserInterceptor;

	private final ObjectMapper objectMapper = new ObjectMapper();

	private AuthorDTO testDTO;
	private CreateAuthorDTO testCreateAuthor;

	@BeforeEach
	public void setup() {
		testDTO = AuthorDTO.builder()
				.id(1L)
				.name("Mario")
				.surname("Rossi")
				.build();

		testCreateAuthor = CreateAuthorDTO.builder()
				.name("Mario")
				.surname("Rossi")
				.build();
	}

	@Test
	void test_getAllAuthors_returnAuthorsList() throws Exception {
		List<AuthorDTO> authorDTOS = Collections.singletonList(testDTO);

		when(authorService.getAllAuthors()).thenReturn(authorDTOS);
		when(jwtService.createToken("test@test.com", "Mario")).thenReturn("test-jwt-token");
		when(checkUserInterceptor.preHandle(any(HttpServletRequest.class), any(HttpServletResponse.class), any(Object.class) )).thenReturn(true);

		mockMvc.perform(get("/private/author/all-authors").header("Authorization", "Bearer test-jwt-token"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(testDTO.getId()))
				.andExpect(jsonPath("$[0].name").value(testDTO.getName()))
				.andExpect(jsonPath("$[0].surname").value(testDTO.getSurname()));

		verify(authorService, times(1)).getAllAuthors();
	}

	@Test
	void test_getAllAuthors_noAuthentication() throws Exception {
		when(checkUserInterceptor.preHandle(any(HttpServletRequest.class), any(HttpServletResponse.class), any(Object.class) )).thenThrow(new SecurityException("Not authorized"));
		mockMvc.perform(get("/private/author/all-authors"))
				.andExpect(status().isUnauthorized());

		verify(authorService, times(0)).getAllAuthors();
	}

	@Test
	void test_getAuthorByID_returnAuthor() throws Exception {

		when(authorService.getAuthorDTOById(anyLong())).thenReturn(testDTO);

		mockMvc.perform(get("/public/author/1"))
				.andExpect(status().isOk())
				.andExpectAll(
						jsonPath("$.id").value(testDTO.getId()),
						jsonPath("$.name").value(testDTO.getName()),
						jsonPath("$.surname").value(testDTO.getSurname())
				);

		verify(authorService, times(1)).getAuthorDTOById(1L);
	}

	@Test
	void test_getAuthorById_NoAuthorFound() throws Exception {
		when(authorService.getAuthorDTOById(anyLong())).thenThrow(new Exception("Author with Id" + 0 + " not found"));

		mockMvc.perform(get("/public/author/10"))
				.andExpect(status().isIAmATeapot())
				.andExpect(jsonPath("$.errorMessage").value("Author with Id0 not found"));

		verify(authorService, times(1)).getAuthorDTOById(10L);
	}

	@Test
	void test_addNewAuthor_everythingOk() throws Exception {

		when(authorService.addNewAuthor(any(CreateAuthorDTO.class))).thenReturn(testDTO);

		mockMvc.perform(post("/author")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(testCreateAuthor)))
				.andExpect(status().isCreated())
				.andExpectAll(
						jsonPath("$.id").value(testDTO.getId()),
						jsonPath("$.name").value(testDTO.getName()),
						jsonPath("$.surname").value(testDTO.getSurname())
				);

		verify(authorService, times(1)).addNewAuthor(testCreateAuthor);
	}

	@Test
	void test_addNewAuthor_shouldThrowException() throws Exception {

		CreateAuthorDTO badAuthor = CreateAuthorDTO.builder()
				.name(null)
				.surname(null)
				.build();

		when(authorService.addNewAuthor(any(CreateAuthorDTO.class))).thenThrow(new Exception("Either name or surname is null"));

		mockMvc.perform(post("/author")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(badAuthor)))
				.andExpect(status().isIAmATeapot())
				.andExpect(jsonPath("$.errorMessage").value("Either name or surname is null"));

		verify(authorService, times(1)).addNewAuthor(badAuthor);
	}

	@Test
	void test_updateAuthor_everythingOk() throws Exception {

		CreateAuthorDTO newCreateAuthor = CreateAuthorDTO.builder()
				.name("Piero")
				.surname("maipiu")
				.build();

		AuthorDTO newDTO = AuthorDTO.builder()
				.name("Piero")
				.surname("maipiu")
				.id(1L)
				.build();

		when(authorService.updateAuthor(any(CreateAuthorDTO.class), anyLong())).thenReturn(newDTO);

		mockMvc.perform(put("/author/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newCreateAuthor)))
				.andExpect(status().isOk())
				.andExpectAll(jsonPath("$.id").value(1L),
						jsonPath("$.name").value("Piero"),
						jsonPath("$.surname").value("maipiu"));
		verify(authorService, times(1)).updateAuthor(newCreateAuthor, 1L);
	}

	@Test
	void test_updateAuthor_whenAuthorDoesNotExist() throws Exception {

		CreateAuthorDTO newCreateAuthor = CreateAuthorDTO.builder()
				.name("Piero")
				.surname("maipiu")
				.build();

		when(authorService.updateAuthor(any(CreateAuthorDTO.class), anyLong())).thenThrow(new Exception("This Author doesn't exist"));

		mockMvc.perform(put("/author/10")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newCreateAuthor)))
				.andExpectAll(status().isIAmATeapot(),
						jsonPath("$.errorMessage").value("This Author doesn't exist"));

		verify(authorService, times(1)).updateAuthor(newCreateAuthor, 10L);
	}

	@Test
	void test_deleteAuthor() throws Exception {

		when(authorService.deleteAuthor(anyLong())).thenReturn(true);

		mockMvc.perform(delete("/author/1"))
				.andExpectAll(status().isOk(),
						content().string("true"));
	}
}