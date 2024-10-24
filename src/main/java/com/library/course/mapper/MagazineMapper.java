package com.library.course.mapper;

import com.library.course.entity.Author;
import com.library.course.entity.Magazine;
import com.library.course.model.CreateMagazineDTO;
import com.library.course.service.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MagazineMapper {
    @Autowired
    MagazineService magazineService;

    public Magazine createMagazineDTOToMagazine(CreateMagazineDTO createMagazineDTO){
        List<Author> authorList = magazineService.getListAuthor(createMagazineDTO);

        return Magazine.builder().numberMagazine(createMagazineDTO.getNumberMagazine()).tipologyMagazine(createMagazineDTO.getTipologyMagazine()).publishedDate(createMagazineDTO.getPublishedDate()).title(createMagazineDTO.getTitle()).authorList(authorList).build();
    }

    public CreateMagazineDTO magazineToCreateMagazineDTO(Magazine magazine){
        List<Long> authorIdList = magazineService.getListAuthorId(magazine);

        return CreateMagazineDTO.builder().numberMagazine(magazine.getNumberMagazine()).tipologyMagazine(magazine.getTipologyMagazine()).publishedDate(magazine.getPublishedDate()).title(magazine.getTitle()).authorIdList(authorIdList).build();
    }

}
