package com.library.course.mapper;

import com.library.course.entity.Author;
import com.library.course.entity.Book;
import com.library.course.model.BookDTO;
import com.library.course.model.CreateBookDTO;
import com.library.course.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookMapper {

    private final AuthorRepository authorRepository;

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

    public Book toBook (BookDTO bookDTO){
        return Book
                .builder()
                .titolo(bookDTO.getTitolo())
                .descrizione(bookDTO.getDescrizione())
                .tipologia(bookDTO.getTipologia())
                .author(authorRepository.findById(bookDTO.getAuthorId()).get())
                .build();
    }

}
