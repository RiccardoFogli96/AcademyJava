package com.library.course.mapper;

import com.library.course.entity.Magazine;
import com.library.course.model.CreateMagazineDTO;

public class MagazineMapper {
    public Magazine createMagazineDTOToMagazine(CreateMagazineDTO createMagazineDTO){
        return Magazine.builder().numberMagazine(createMagazineDTO.getNumberMagazine())
    }
}
