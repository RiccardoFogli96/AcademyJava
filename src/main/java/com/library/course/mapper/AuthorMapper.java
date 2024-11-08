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
    private final AuthorService authorService;
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
        List<Long> longList = new ArrayList<>();
        authorList.forEach(a->longList.add(a.getId()));
        return longList;
    }

    public List<Author> fromAuthorIdListToAuthorList(List<Long> authorList) throws Exception{
        List<Author> authorIdList = new ArrayList<>();
        authorList.forEach(a-> {
            try {
                authorIdList.add(authorService.getAuthorById(a));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return authorIdList;
    }


}
