package com.library.course.mapper;

import com.library.course.entity.Author;
import com.library.course.model.AuthorDTO;
import com.library.course.model.CreateAuthorDTO;
import com.library.course.service.AuthService;
import com.library.course.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorMapper {

    public Author fromDTOtoAuthor(AuthorDTO authorDTO) {
        return Author.builder()
                .name(authorDTO.getName())
                .id(authorDTO.getId())
                .surname(authorDTO.getSurname())
                .biography(authorDTO.getBiography())
                .build();
    }

    public Author fromDTOtoAuthor(CreateAuthorDTO authorDTO) {
        return Author.builder()
                .name(authorDTO.getName())
                .surname(authorDTO.getSurname())
                .biography(authorDTO.getBiography())
                .build();
    }

    public AuthorDTO fromAuthorToDTO(Author author) {
        return AuthorDTO.builder()
                .name(author.getName())
                .id(author.getId())
                .surname(author.getSurname())
                .biography(author.getBiography())
                .build();
    }

    public List<AuthorDTO> fromAuthorListToDTOList(List<Author> authorList) {
        return authorList.stream().map(this::fromAuthorToDTO).toList();
    }

    public List<Long> fromAuthorListToLongList(List<Author> authorList) {
        return authorList.stream().map(i->i.getId()).toList();
    }

}
