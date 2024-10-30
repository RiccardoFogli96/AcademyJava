package com.library.course.service;


import com.library.course.entity.Author;
import com.library.course.entity.Book;
import com.library.course.mapper.AuthorMapper;
import com.library.course.mapper.BookMapper;
import com.library.course.model.*;
import com.library.course.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final BookMapper bookMapper;
    private final AuthorMapper authorMapper;



    public BookDTO addBook(CreateBookDTO createBookDTO) throws Exception {

        AuthorDTO authorDTO = authorService.getAuthorDTOById(createBookDTO.getAuthorId());
        Author author = authorMapper.fromDTOtoAuthor(authorDTO);
        boolean isPresent = bookRepository.existsByAuthor_IdAndTitolo(author.getId(), createBookDTO.getTitolo());
        if(isPresent){
            throw new Exception("Book with title " + createBookDTO.getTitolo() + "already exists");
        }
        Book newBook = bookMapper.fromDTOToBook(createBookDTO, author);
        newBook = bookRepository.save(newBook);

        return bookMapper.fromBookToDTO(newBook);
    }

    public BookDTO getBookDTOById(Long id) throws Exception{
        Book book = bookRepository.findById(id).orElseThrow(()-> new Exception("Book not found"));
        return bookMapper.fromBookToDTO(book);
    }

    public Book getBookById (Long id) throws Exception{
       return bookRepository.findById(id).orElseThrow(()-> new Exception("Book not found"));
    }

    public List<BookDTO> getBookByTipology ( GenreBook genreBook) {
        List<Book> book = bookRepository.findByTipologia(genreBook);
        return book.stream().map(b -> new BookDTO(b.getTitolo(), b.getDescrizione(), b.getGenreBook(), b.getAuthor().getId(), b.getId())).toList();
    }

    public BookDTO addBookWithAuthor( CreateBookDTO createBookDTO, Long id) throws Exception {
        authorService.getAuthorDTOById(id);
        createBookDTO.setAuthorId(id);
        return addBook(createBookDTO);
    }

    public List<BookDTO> getBookByIdAuthor(long idAuthor) throws Exception {
        List<Book> listBookAuthor = bookRepository.findByAuthorId(idAuthor);
        if(listBookAuthor.isEmpty()){
            throw new Exception("There is no Book with Author Id: " + idAuthor);
        }
        return listBookAuthor.stream().map(bookMapper::fromBookToDTO).toList();
    }

    public boolean deleteBookByIdAuthor(long idAuthor) throws Exception {
        List<BookDTO> listBookDTO = this.getBookByIdAuthor(idAuthor);
        listBookDTO.stream().map(bookMapper::toBook).forEach(bookRepository::delete);
        return true;
    }

    public BookDTO changeTitleToBookDTO(Long authorId, ModifyBookDTO modifyBookDTO)throws Exception{
        Book book = bookRepository.findByAuthorIdAndId(authorId, modifyBookDTO.getId());
        if(book == null){
            throw new Exception("Book with id " + modifyBookDTO.getId() + "doesn't exist");
        }
        book.setTitolo(modifyBookDTO.getTitolo());
        bookRepository.save(book);
        return bookMapper.fromBookToDTO(book);
    }


}
