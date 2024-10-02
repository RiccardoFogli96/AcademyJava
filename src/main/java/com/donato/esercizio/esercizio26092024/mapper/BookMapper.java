package com.donato.esercizio.esercizio26092024.mapper;

import com.donato.esercizio.esercizio26092024.entity.Book;
import com.donato.esercizio.esercizio26092024.model.BookDTO;
import com.donato.esercizio.esercizio26092024.model.CreateBookDTO;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book fromDTOToBook(BookDTO bookDTO){
        return Book.builder()
                .id(bookDTO.getId())
                .titolo(bookDTO.getTitolo())
                .descrizione(bookDTO.getDescrizione())
                .tipologia(bookDTO.getTipologia())
                .authorId(bookDTO.getAuthorId())
                .build();
    }

    public Book fromDTOToBook(CreateBookDTO createBookDTO){
        return Book.builder()
                .titolo(createBookDTO.getTitolo())
                .descrizione(createBookDTO.getDescrizione())
                .tipologia(createBookDTO.getTipologia())
                .authorId(createBookDTO.getAuthorId())
                .build();
    }

    public BookDTO fromBookToDTO(Book book){
        return BookDTO.builder()
                .id(book.getId())
                .titolo(book.getTitolo())
                .tipologia(book.getTipologia())
                .authorId(book.getAuthorId())
                .descrizione(book.getDescrizione())
                .build();
    }

}
