package com.library.course.service;

import com.library.course.mapper.AuthorMapper;
import com.library.course.mapper.BookMapper;
import com.library.course.model.CreateBookDTO;
import com.library.course.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private AuthorService authorService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private BookService bookService;

    private CreateBookDTO book;

    @BeforeEach
    public void setUp(){
       book = CreateBookDTO.builder()
                .titolo("IT")
                .authorId(1L)
                .build();
    }



    @Test
    //addBook testing
    void addBook_ShouldThrowException_WhenAuthorNotFound() throws Exception {
        String error = "Author not exist";
        when(authorService.getAuthorById(book.getAuthorId())).thenThrow(new Exception(error));
         Exception e = assertThrows(Exception.class,()-> bookService.addBook(book));
        assertEquals(error, e.getMessage());
    }

    @Test
    void getBookDTOById() {
    }

    @Test
    void getBookById() {
    }

    @Test
    void getBookByTipology() {
    }

    @Test
    void addBookWithAuthor() {
    }

    @Test
    void getBookByIdAuthor() {
    }

    @Test
    void deleteBookByIdAuthor() {
    }

    @Test
    void changeTitleToBookDTO() {
    }
}