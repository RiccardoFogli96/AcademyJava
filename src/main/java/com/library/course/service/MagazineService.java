package com.library.course.service;

import com.library.course.entity.Author;
import com.library.course.entity.Magazine;
import com.library.course.mapper.AuthorMapper;
import com.library.course.mapper.MagazineMapper;
import com.library.course.model.CreateMagazineDTO;
import com.library.course.model.MagazineDTO;
import com.library.course.repository.MagazineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MagazineService {

    private final MagazineRepository magazineRepository;
    private final MagazineMapper magazineMapper;
    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    public MagazineDTO addMagazine( CreateMagazineDTO createMagazineDTO) {
       // Magazine magazine = magazineMapper.createMagazineDTOToMagazine(createMagazineDTO);
       // return magazineMapper.magazineToCreateMagazineDTO(magazineRepository.save(magazine));
        return null;
    }

    public List<Author> getListAuthor(CreateMagazineDTO createMagazineDTO) throws Exception {
        List<Long> authorIdList = createMagazineDTO.getAuthorIdList();
        List<Author> authors = authorService.findAllById(authorIdList);
        if(authors.size() != authorIdList.size()){
            throw new Exception("Some authors could not be found !");
        }
        return authors;
    }

    public List<Long> getListAuthorId(Magazine magazine){
        List<Author> authorList = magazine.getAuthorList();
        List<Long> authorIdList = new ArrayList<>();
        authorList.forEach(a->authorIdList.add(a.getId()));
        return authorIdList;
    }
}
