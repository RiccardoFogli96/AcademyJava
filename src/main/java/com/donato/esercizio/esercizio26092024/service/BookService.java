package com.donato.esercizio.esercizio26092024.service;


import com.donato.esercizio.esercizio26092024.entity.Book;
import com.donato.esercizio.esercizio26092024.model.CreateBookDTO;
import com.donato.esercizio.esercizio26092024.model.BookDTO;
import com.donato.esercizio.esercizio26092024.model.Tipologia;
import com.donato.esercizio.esercizio26092024.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;


    public BookDTO addBook (CreateBookDTO createBookDTO){

        Book newBook = new Book();
        newBook.setTitolo(createBookDTO.getTitolo());
        newBook.setDescrizione(createBookDTO.getDescrizione());

        newBook = bookRepository.save(newBook);

        BookDTO newBookResponseDTO = new BookDTO();
        newBookResponseDTO.setId(newBook.getId());
        newBookResponseDTO.setTitolo(newBook.getTitolo());
        newBookResponseDTO.setDescrizione(newBook.getDescrizione());

        return newBookResponseDTO;
    }

    public BookDTO getBookById (Long id) throws Exception{
        Book book = bookRepository.findById(id).orElseThrow(()-> new Exception("Book not found"));

        BookDTO newBookDTO = new BookDTO();
        newBookDTO.setId(book.getId());
        newBookDTO.setTitolo(book.getTitolo());
        newBookDTO.setDescrizione(book.getDescrizione());

        return newBookDTO;
    }

    public List<BookDTO> getBookByTipology (Tipologia tipologia) {
        List<Book> book = bookRepository.findByTipologia(tipologia);

        List<BookDTO> bookDTOS = book.stream().map(b -> new BookDTO(b.getId(),b.getTitolo(), b.getDescrizione())).toList();
        return bookDTOS;
    }


}
