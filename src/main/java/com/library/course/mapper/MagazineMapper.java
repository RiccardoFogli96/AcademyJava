package com.library.course.mapper;

import com.library.course.entity.Author;
import com.library.course.entity.Magazine;
import com.library.course.model.AuthorDTO;
import com.library.course.model.CreateMagazineDTO;
import com.library.course.model.MagazineDTO;
import com.library.course.service.MagazineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@RequiredArgsConstructor
@Component
public class MagazineMapper {

   private final MagazineService magazineService;

    public Magazine createMagazineDTOToMagazine( CreateMagazineDTO createMagazineDTO, List<Author> authors ){
        return Magazine
                .builder()
                .numberMagazine(createMagazineDTO.getNumberMagazine())
                .tipologyMagazine(createMagazineDTO.getTipologyMagazine())
                .publishedDate(createMagazineDTO.getPublishedDate())
                .title(createMagazineDTO.getTitle())
                .authorList(authors).build();
    }

    public MagazineDTO toMagazineDTO( Magazine magazine, List< AuthorDTO > authorDTOS ){

        return MagazineDTO
                .builder()
                .numberMagazine(magazine.getNumberMagazine())
                .tipologyMagazine(magazine.getTipologyMagazine())
                .publishedDate(magazine.getPublishedDate())
                .title(magazine.getTitle())
                .authorList(authorDTOS)
		        .build();
    }

}
