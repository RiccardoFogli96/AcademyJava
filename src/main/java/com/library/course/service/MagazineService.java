package com.library.course.service;

import com.library.course.entity.Author;
import com.library.course.entity.Magazine;
import com.library.course.mapper.AuthorMapper;
import com.library.course.mapper.MagazineMapper;
import com.library.course.model.CreateMagazineDTO;
import com.library.course.repository.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MagazineService {
    @Autowired
    MagazineRepository magazineRepository;
    @Autowired
    MagazineMapper magazineMapper;
    @Autowired
    AuthorService authorService;
    @Autowired
    AuthorMapper authorMapper;

    public CreateMagazineDTO addMagazine(CreateMagazineDTO createMagazineDTO) {
        Magazine magazine = magazineMapper.createMagazineDTOToMagazine(createMagazineDTO);
        return magazineMapper.magazineToCreateMagazineDTO(magazineRepository.save(magazine));
    }

    public List<Author> getListAuthor(CreateMagazineDTO createMagazineDTO) {
        List<Long> authorIdList = createMagazineDTO.getAuthorIdList();
        List<Author> authorList = new ArrayList<>();
        authorIdList.forEach(a -> {
            try {
                authorList.add(authorMapper.fromDTOtoAuthor(authorService.getAuthorById(a)));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return authorList;
    }

    public List<Long> getListAuthorId(Magazine magazine){
        List<Author> authorList = magazine.getAuthorList();
        List<Long> authorIdList = new ArrayList<>();
        authorList.forEach(a->authorIdList.add(a.getId()));
        return authorIdList;
    }
}
