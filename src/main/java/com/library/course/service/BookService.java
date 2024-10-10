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

        AuthorDTO authorDTO = authorService.getAuthorById(createBookDTO.getAuthorId());
        Author author = authorMapper.fromDTOtoAuthor(authorDTO);

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

    public List<BookDTO> getBookByTipology ( Tipologia tipologia) {
        List<Book> book = bookRepository.findByTipologia(tipologia);
        return book.stream().map(b -> new BookDTO(b.getTitolo(), b.getDescrizione(), b.getTipologia(), b.getAuthor().getId(), b.getId())).toList();
    }

    public BookDTO addBookWithAuthor( CreateBookDTO createBookDTO, Long id) throws Exception {
        authorService.getAuthorById(id);
        createBookDTO.setAuthorId(id);
        return addBook(createBookDTO);
    }

    public List<BookDTO> getBookByIdAuthor(long idAuthor) {
        List<Book> listBookAuthor = bookRepository.findByAuthorId(idAuthor);
        List<BookDTO> listBookDTOAuthor = new ArrayList<>();
        for (Book i : listBookAuthor) {
            BookDTO newBookResponseDTO = new BookDTO();
            newBookResponseDTO.setId(i.getId());
            newBookResponseDTO.setTitolo(i.getTitolo());
            newBookResponseDTO.setDescrizione(i.getDescrizione());
            newBookResponseDTO.setTipologia(i.getTipologia());
            newBookResponseDTO.setAuthorId(i.getAuthor().getId());
            listBookDTOAuthor.add(newBookResponseDTO);
        }

        return listBookDTOAuthor;
    }

    public boolean deleteBookByIdAuthor(long idAuthor){
        List<BookDTO> listBookDTO = this.getBookByIdAuthor(idAuthor);
        for(BookDTO i : listBookDTO){
            bookRepository.deleteById(i.getId());
        }
        return true;
    }

    public BookDTO changeTitleToBookDTO(Long authorId, ModifyBookDTO modifyBookDTO)throws Exception{
        Book book = bookRepository.findByAuthorIdAndId(authorId, modifyBookDTO.getId());
        if(book == null){
            throw new Exception("Non Esisto");
        }
        book.setTitolo(modifyBookDTO.getTitolo());
        bookRepository.save(book);
        return bookMapper.fromBookToDTO(book);
    }


}
