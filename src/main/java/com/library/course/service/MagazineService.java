package com.library.course.service;

import com.library.course.entity.Magazine;
import com.library.course.mapper.AuthorMapper;
import com.library.course.mapper.MagazineMapper;
import com.library.course.model.CreateMagazineDTO;
import com.library.course.repository.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public CreateMagazineDTO addMagazine(CreateMagazineDTO createMagazineDTO){
        Magazine magazine = magazineMapper.createMagazineDTOToMagazine(createMagazineDTO);
        return magazineMapper.magazineToCreateMagazineDTO(magazineRepository.save(magazine));
    }

}
