package com.library.course.service;

import com.library.course.model.CreateMagazineDTO;
import com.library.course.repository.MagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MagazineService {
    @Autowired
    MagazineRepository magazineRepository;

    public CreateMagazineDTO addMagazine(CreateMagazineDTO createMagazineDTO){

    }

}
