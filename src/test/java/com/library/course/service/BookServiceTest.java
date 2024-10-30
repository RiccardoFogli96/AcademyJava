package com.library.course.service;

import com.library.course.entity.Author;
import com.library.course.entity.Book;
import com.library.course.mapper.AuthorMapper;
import com.library.course.mapper.BookMapper;
import com.library.course.model.*;
import com.library.course.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
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
    private CreateBookDTO createBookDTO2;
    private AuthorDTO authorDTO;
    private Book book;
    private Book book2;
    private Book changedBook;
    private BookDTO changedBookDTO;
    private BookDTO bookDTO;
    private BookDTO bookDTO2;
    private Author author;
    private Author author2;
    private List<Book> books;
    private ModifyBookDTO modifyBookDTO;

    @BeforeEach
    public void setUp() {

        author = Author.builder()
                .id(1L)
                .build();

        createBookDTO = CreateBookDTO.builder()
                .titolo("IT")
                .authorId(1L)
                .build();

        createBookDTO2 = CreateBookDTO.builder()
                .authorId(1L)
                .titolo("IT")
                .build();

        authorDTO = AuthorDTO.builder()
                .id(1L)
                .build();
        author2 = Author.builder()
                .id(2L)
                .build();

        book = Book.builder()
                .titolo("IT")
                .id(1L)
                .author(author)
                .build();

        book2 = Book.builder()
                .titolo("La guerra dei papaveri")
                .id(2L)
                .author(author2)
                .build();

        bookDTO = BookDTO.builder()
                .titolo("IT")
                .authorId(1L)
                .id(1L)
                .build();

        bookDTO2 = BookDTO.builder()
                .titolo("La guerra dei papaveri")
                .authorId(2L)
                .id(2L)
                .build();

        books = asList(book, book2);

        modifyBookDTO = new ModifyBookDTO(1L, "Il nome della rosa");
        changedBook = Book.builder()
                .titolo("Il nome della rosa")
                .id(1L)
                .author(author)
                .build();

        changedBookDTO = BookDTO.builder()
                .titolo("Il nome della rosa")
                .id(1L)
                .authorId(1L)
                .build();
    }


    @Test
    void addBook_ShouldThrowException_WhenAuthorNotFound() throws Exception {
        String error = "Author not exist";
        when(authorService.getAuthorDTOById(createBookDTO.getAuthorId())).thenThrow(new Exception(error));
        Exception e = assertThrows(Exception.class, () -> bookService.addBook(createBookDTO));
        assertEquals(error, e.getMessage());
    }

    @Test
    void addBook_ShouldThrowException_WhenTitleAndAuthorIdAlreadyExist() throws Exception {

        when(authorService.getAuthorDTOById(createBookDTO.getAuthorId())).thenReturn(authorDTO);
        when(authorMapper.fromDTOtoAuthor(authorDTO)).thenReturn(author);
        when(bookRepository.existsByAuthor_IdAndTitolo(author.getId(), createBookDTO.getTitolo())).thenReturn(true);

        Exception exception = assertThrows(Exception.class, () -> bookService.addBook(createBookDTO));
        assertEquals("Book with title " + createBookDTO.getTitolo() + "already exists", exception.getMessage());
    }

    @Test
    void addBook_ShouldReturnBookDTO_WhenItsAllOk() throws Exception {
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
    void getBookDTOById_WhenIdIsNotPresent_ShouldThrowException() {
        when(bookRepository.findById(book.getId())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> bookService.getBookDTOById(book.getId()));
        Exception e = assertThrows(Exception.class, () -> bookService.getBookDTOById(anyLong()));
        assertEquals("Book not found", e.getMessage());
    }

    @Test
    void getBookDTOById_WheinItsAllOk() throws Exception {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        when(bookMapper.fromBookToDTO(book)).thenReturn(bookDTO);
        BookDTO bookFound = bookService.getBookDTOById(anyLong());
        assertEquals(bookFound.getId(), book.getId());
    }

    @Test
    void getBookById_WhenIdIsNotPresent_ShouldThrowException() {
        when(bookRepository.findById(book.getId())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> bookService.getBookById(book.getId()));
        Exception e = assertThrows(Exception.class, () -> bookService.getBookById(anyLong()));
        assertEquals("Book not found", e.getMessage());
    }

    @Test
    void getBookById_WhenItsAllOk() throws Exception {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        Book bookFound = bookService.getBookById(anyLong());
        assertEquals(bookFound.getId(), book.getId());
    }

    @Test
    void getBookByTipology_WhenItsAllOk() {
        when(bookRepository.findByTipologia(GenreBook.FANTASIA)).thenReturn(books);
        List<BookDTO> fantasyBooks = bookService.getBookByTipology(GenreBook.FANTASIA);
        assertEquals(fantasyBooks.size(), 2);
    }

    @Test
    void addBookWithAuthor_WhenAuthorNotExist_ShouldThrowException() throws Exception {
        when(authorService.getAuthorDTOById(anyLong())).thenReturn(null);
        assertThrows(Exception.class, () -> bookService.addBookWithAuthor(createBookDTO, anyLong()));
    }

    @Test
    void addBookWithAuthor_WhenItsAllOk() throws Exception {
        when(authorService.getAuthorDTOById(1L)).thenReturn(authorDTO);
        when(authorMapper.fromDTOtoAuthor(authorDTO)).thenReturn(author);
        when(bookRepository.existsByAuthor_IdAndTitolo(1L, "IT")).thenReturn(false);
        when(bookMapper.fromDTOToBook(createBookDTO2, author)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.fromBookToDTO(book)).thenReturn(bookDTO);
        BookDTO addedBook = bookService.addBookWithAuthor(createBookDTO2, 1L);
        assertEquals(addedBook.getAuthorId(), 1L);
    }

    @Test
    void getBookByIdAuthor_WhenItsAllOk() throws Exception {
        when(bookRepository.findByAuthorId(anyLong())).thenReturn(books);
        when(bookMapper.fromBookToDTO(book)).thenReturn(bookDTO);
        when(bookMapper.fromBookToDTO(book2)).thenReturn(bookDTO2);

        List<BookDTO> booksByAuthor = bookService.getBookByIdAuthor(anyLong());
        assertEquals(booksByAuthor.size(), 2);
    }

    @Test
    void getBookByIdAuthor_WhenListIsEmpty_ShouldThrowException() {
        when(bookRepository.findByAuthorId(anyLong())).thenReturn(List.of());
        assertThrows(Exception.class, () -> bookService.getBookByIdAuthor(anyLong()));
    }

    @Test
    void deleteBookByIdAuthor() throws Exception {
        when(bookRepository.findByAuthorId(anyLong())).thenReturn(books);
        boolean result = bookService.deleteBookByIdAuthor(anyLong());
        assertTrue(result);
    }

    @Test
    void changeTitleToBookDTO() throws Exception {
        when(bookRepository.findByAuthorIdAndId(anyLong(), anyLong())).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(changedBook);
        when(bookMapper.fromBookToDTO(book)).thenReturn(changedBookDTO);
        BookDTO modifyBook = bookService.changeTitleToBookDTO(1L, modifyBookDTO);
        assertEquals("Il nome della rosa", modifyBook.getTitolo());
    }

    @Test
    void changeTitleToBookDTO_WhenBookIsNull_ShouldThrowException() {
        when(bookRepository.findByAuthorIdAndId(anyLong(), anyLong())).thenReturn(null);
        assertThrows(Exception.class, () -> bookService.changeTitleToBookDTO(1L, modifyBookDTO));
    }
}