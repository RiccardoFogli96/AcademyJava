package com.library.course.service;

import com.library.course.entity.Author;
import com.library.course.entity.Book;
import com.library.course.mapper.AuthorMapper;
import com.library.course.mapper.BookMapper;
import com.library.course.model.AuthorDTO;
import com.library.course.model.BookDTO;
import com.library.course.model.CreateBookDTO;
import com.library.course.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

    private CreateBookDTO createBookDTO;
    private AuthorDTO authorDTO;
    private Book book;
    private BookDTO bookDTO;
    private Author author;

    @BeforeEach
    public void setUp(){

        author = Author.builder()
                .id(1L)
                .build();

       createBookDTO = CreateBookDTO.builder()
                .titolo("IT")
                .authorId(1L)
                .build();

       authorDTO = AuthorDTO.builder()
               .id(1L)
               .build();

       book = Book.builder()
               .titolo("IT")
               .author(author)
               .build();

       bookDTO = BookDTO.builder()
               .titolo("IT")
               .authorId(1L)
               .id(1L)
               .build();
    }



    @Test
    //addBook testing
    void addBook_ShouldThrowException_WhenAuthorNotFound() throws Exception {
        String error = "Author not exist";
        when(authorService.getAuthorDTOById(createBookDTO.getAuthorId())).thenThrow(new Exception(error));
         Exception e = assertThrows(Exception.class,()-> bookService.addBook(createBookDTO));
        assertEquals(error, e.getMessage());
    }

    @Test
    void addBook_ShouldThrowException_WhenTitleAndAuthorIdAlreadyExist()throws Exception{

        when(authorService.getAuthorDTOById(createBookDTO.getAuthorId())).thenReturn(authorDTO);
        when(authorMapper.fromDTOtoAuthor(authorDTO)).thenReturn(author);
        when(bookRepository.existsByAuthor_IdAndTitolo(author.getId(), createBookDTO.getTitolo())).thenReturn(true);

        Exception exception = assertThrows(Exception.class, () -> bookService.addBook(createBookDTO));
        assertEquals("Book with title " + createBookDTO.getTitolo() + "already exists", exception.getMessage());
    }

    @Test
    void addBook_ShouldReturnBookDTO_WhenItsAllOk()throws Exception{
        Book newBook = Book.builder()
                .id(1L)
                .titolo("IT")
                .author(author)
                .build();

        when(authorService.getAuthorDTOById(createBookDTO.getAuthorId())).thenReturn(authorDTO);
        when(authorMapper.fromDTOtoAuthor(authorDTO)).thenReturn(author);
        when(bookRepository.existsByAuthor_IdAndTitolo(author.getId(), createBookDTO.getTitolo())).thenReturn(false);
        when(bookMapper.fromDTOToBook(createBookDTO, author)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(newBook);
        when(bookMapper.fromBookToDTO(newBook)).thenReturn(bookDTO);

        bookService.addBook(createBookDTO);

        assertEquals(bookDTO.getId(), 1);

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