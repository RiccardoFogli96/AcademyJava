package com.library.course.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.course.entity.Author;
import com.library.course.entity.Book;
import com.library.course.model.BookDTO;
import com.library.course.model.CreateBookDTO;
import com.library.course.model.GenreBook;
import com.library.course.repository.AuthorRepository;
import com.library.course.service.BookService;
import com.library.course.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest()
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookService bookService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthorRepository authorRepository;

    private Author author;
    private Book book, book2;
    private List<Book> books;
    private CreateBookDTO createBookDTO, createBookDTO2;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp(){
        book = Book.builder()
                .titolo("IT")
                .id(1L)
                .author(author)
                .build();

        book2 = Book.builder()
                .titolo("La guerra dei papaveri")
                .id(2L)
                .author(author)
                .build();

        author = Author.builder()
                .id(1L)
                .name("Mario")
                .surname("Rossi")
                .build();

        createBookDTO = CreateBookDTO.builder()
                .titolo("IT")
                .genreBook(GenreBook.FANTASIA)
                .authorId(1L)
                .build();

        createBookDTO2 = CreateBookDTO.builder()
                .authorId(1L)
                .titolo("IT")
                .build();

        books = new ArrayList<>();
        books.add(book);
        books.add(book2);
    }

    @Test
    void addBookWithAuthor_When_AllOkay() throws Exception {
        authorRepository.save(author);
        MvcResult mvcResult = mockMvc.perform(post("/book/author/" + author.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createBookDTO)))
                .andReturn();

        BookDTO bookDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),BookDTO.class);
        assertEquals(bookDTO.getTitolo(), "IT");
    }

    @Test
    void addBook_WhenAuthorIsNotPresent() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/book/author/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createBookDTO))).andReturn();
        String error = mvcResult.getResponse().getContentAsString();
        assertEquals(error, "Author with id " + 1 + " not found");
    }

    @Test
    void getBookById() {
    }

    @Test
    void getBookByTipology() {
    }

    @Test
    void changeTitleByAuthorId() {
    }
}