package com.library.course.mapper;

import com.library.course.entity.Author;
import com.library.course.entity.Magazine;
import com.library.course.model.CreateMagazineDTO;
import com.library.course.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MagazineMapper {
    @Autowired
    AuthorMapper authorMapper;
    @Autowired
    AuthorService authorService;

    public Magazine createMagazineDTOToMagazine(CreateMagazineDTO createMagazineDTO){
        List<Long> authorIdList = createMagazineDTO.getAuthorIdList();
        List<Author> authorList = new ArrayList<>();
        authorIdList.forEach(a-> {
            try {
                authorList.add(authorMapper.fromDTOtoAuthor(authorService.getAuthorById(a)));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return Magazine.builder().numberMagazine(createMagazineDTO.getNumberMagazine()).tipologyMagazine(createMagazineDTO.getTipologyMagazine()).publishedDate(createMagazineDTO.getPublishedDate()).title(createMagazineDTO.getTitle()).authorList(authorList).build();
    }

    public CreateMagazineDTO magazineToCreateMagazineDTO(Magazine magazine){
        List<Author> authorList = magazine.getAuthorList();
        List<Long> authorIdList = new ArrayList<>();
        authorList.forEach(a->authorIdList.add(a.getId()));
        return CreateMagazineDTO.builder().numberMagazine(magazine.getNumberMagazine()).tipologyMagazine(magazine.getTipologyMagazine()).publishedDate(magazine.getPublishedDate()).title(magazine.getTitle()).authorIdList(authorIdList).build();
    }

}
