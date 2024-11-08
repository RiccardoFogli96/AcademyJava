package com.library.course.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.course.entity.Author;
import com.library.course.entity.Book;
import com.library.course.model.BookDTO;
import com.library.course.model.CreateBookDTO;
import com.library.course.model.GenreBook;
import com.library.course.model.ModifyBookDTO;
import com.library.course.repository.AuthorRepository;
import com.library.course.repository.BookRepository;
import com.library.course.service.BookService;
import com.library.course.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
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

    @Autowired
    private BookRepository bookRepository;


    private Author author;
    private Book book;
    private Book book2;
    private List<Book> books;
    private CreateBookDTO createBookDTO2;
    private CreateBookDTO createBookDTO;
    private ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    void setUp() {
        author = Author.builder()
                .name("Mario")
                .surname("Rossi")
                .build();

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

        BookDTO bookDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), BookDTO.class);
        assertEquals(bookDTO.getTitolo(), "IT");
    }

    @Test
    void addBook_WhenAuthorIsNotPresent() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/book/author/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createBookDTO))).andReturn();
        String error = mvcResult.getResponse().getContentAsString();
        assertEquals(error, "Author with id " + 1 + " not found");

        String errorLine = mvcResult.getResponse().getContentAsString();
        assertEquals(error, "Author with Id " + 1 + " not found");
    }

    //boolean isPresent = bookRepository.existsByAuthor_IdAndTitolo(author.getId(), createBookDTO.getTitolo());
    @Test
    void addBook_WhenBookAlreadyExists() throws Exception {

        Author author1 = Author.builder()
                .id(1L)
                .name("Mario")
                .surname("Bianchi")
                .build();
        Book book1 = Book.builder()
                .id(1L)
                .author(author1)
                .titolo("IT")
                .build();
        CreateBookDTO createBookDTO1 = CreateBookDTO.builder()
                .titolo("IT")
                .genreBook(GenreBook.FANTASIA)
                .authorId(author1.getId())
                .build();

        authorRepository.save(author1);
        bookRepository.save(book1);
        MvcResult mvcResult = mockMvc.perform(post("/book/author/" + author1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createBookDTO1)))
                .andReturn();

        String errorMessage = mvcResult.getResponse().getContentAsString();

        assertEquals(errorMessage, "Book with title " + createBookDTO1.getTitolo() + " already exists");
        assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());

    }

    @Test
    void getBookById() {
    }

    @Test
    void getBookById_When_AllOkay() {
    }


    /*
    @Test
    void getBookById() {
    }
     */

    @Test
    void getBookByTipology() {
    }

    //"/manage/{authorId}"
    @Test
    void changeTitleByAuthorId_WhenBookDoesntExist() throws Exception {

        ModifyBookDTO modifyBookDTO = ModifyBookDTO
                .builder()
                .id(2L)
                .titolo("New title")
                .build();

        MvcResult mvcResult = mockMvc.perform(patch("/book/manage/" + 4L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modifyBookDTO)))
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        assertEquals(result,"Book with id " + modifyBookDTO.getId() + " doesn't exist");
    }

    @Test
    void changeTitleByAuthorId() {
    }
}