package com.donato.esercizio.esercizio26092024.mapper;

import com.donato.esercizio.esercizio26092024.entity.Author;
import com.donato.esercizio.esercizio26092024.model.AuthorDTO;
import com.donato.esercizio.esercizio26092024.model.CreateAuthorDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorMapper {
    public Author fromDTOtoAuthor(AuthorDTO authorDTO){
        return Author.builder()
                .name(authorDTO.getName())
                .id(authorDTO.getId())
                .surname(authorDTO.getSurname())
                .biography(authorDTO.getBiography())
                .build();
    }
    public Author fromDTOtoAuthor(CreateAuthorDTO authorDTO){
        return Author.builder()
                .name(authorDTO.getName())
                .surname(authorDTO.getSurname())
                .biography(authorDTO.getBiography())
                .build();
    }
    public AuthorDTO fromAuthorToDTO(Author author){
        return AuthorDTO.builder()
                .name(author.getName())
                .id(author.getId())
                .surname(author.getSurname())
                .biography(author.getBiography())
                .build();
    }
    public List<AuthorDTO> fromAuthorListToDTOList(List<Author> authorList){
        return authorList.stream().map(this::fromAuthorToDTO).toList();
    }



}
