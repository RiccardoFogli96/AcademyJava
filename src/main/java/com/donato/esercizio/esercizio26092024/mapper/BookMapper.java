package com.donato.esercizio.esercizio26092024.mapper;

import com.donato.esercizio.esercizio26092024.controller.AuthorController;
import com.donato.esercizio.esercizio26092024.entity.Author;
import com.donato.esercizio.esercizio26092024.entity.Book;
import com.donato.esercizio.esercizio26092024.model.AuthorDTO;
import com.donato.esercizio.esercizio26092024.model.BookDTO;
import com.donato.esercizio.esercizio26092024.model.CreateBookDTO;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book fromDTOToBook(CreateBookDTO createBookDTO, Author author){
        return Book.builder()
                .titolo(createBookDTO.getTitolo())
                .descrizione(createBookDTO.getDescrizione())
                .tipologia(createBookDTO.getTipologia())
                .author(author)
                .build();
    }

    public BookDTO fromBookToDTO(Book book){
        Author author = book.getAuthor();

        return BookDTO.builder()
                .id(book.getId())
                .titolo(book.getTitolo())
                .tipologia(book.getTipologia())
                .authorId(author.getId())
                .descrizione(book.getDescrizione())
                .build();
    }

}
