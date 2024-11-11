package com.library.course.service;

import com.library.course.entity.Author;
import com.library.course.entity.Magazine;
import com.library.course.mapper.AuthorMapper;
import com.library.course.mapper.MagazineMapper;
import com.library.course.model.CreateMagazineDTO;
import com.library.course.model.TipologyMagazine;
import com.library.course.repository.MagazineRepository;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MagazineServiceTest {

    @InjectMocks
    private MagazineService magazineService;

    @Mock
    private MagazineRepository magazineRepository;

    @Mock
    private MagazineMapper magazineMapper;

    @Mock
    private AuthorService authorService;

    @Mock
    private AuthorMapper authorMapper;

    private Author author1;
    private Author author2;
    private Author author3;
    private CreateMagazineDTO createMagazineDTO;
    List<Long> authorIdList = new ArrayList<>();
    List<Author> authorList = new ArrayList<>();
    List<Long> authorIdList2 = new ArrayList<>();
    Magazine magazine;
    Magazine magazineSaved;

    @BeforeEach
    void setUp() {

        author1 = new Author(1L, "Teste1", "Teste1", null, null, null);
        author2 = new Author(2L, "Teste2", "Teste2", null, null, null);
        author3 = new Author(3l, "Teste3", "Teste3", null, null, null);

        authorList = Arrays.asList(author1,author2,author3);

        authorIdList= Arrays.asList(1L,2L,3L,4L);
        authorIdList2 = Arrays.asList(1L,2L,3L);

        createMagazineDTO = CreateMagazineDTO.builder().title("Teste").authorIdList(authorIdList).publishedDate(LocalDate.now()).tipologyMagazine(TipologyMagazine.DAILY).build();

        magazine = Magazine.builder().title("Teste").publishedDate(LocalDate.now()).tipologyMagazine(TipologyMagazine.DAILY).authorList(authorList).build();
        magazineSaved = Magazine.builder().id(1L).title("Teste").publishedDate(LocalDate.now()).tipologyMagazine(TipologyMagazine.DAILY).authorList(authorList).build();
    }

    @Test
        //getListAuthor test
    void getListAuthor_ShouldThrowException_WhenAuthorNotExists() throws Exception {
        when(authorService.findAllById(createMagazineDTO.getAuthorIdList())).thenReturn(authorList);


        Exception exception = assertThrows(Exception.class, () -> magazineService.getListAuthor(createMagazineDTO));
        assertEquals("Some authors could not be found !", exception.getMessage());
    }

    @Test
    void addNewMagazine_ShouldThrowException_WhenAuthorNotExists() throws Exception {
        when(authorService.findAllById(createMagazineDTO.getAuthorIdList())).thenReturn(authorList);

        Exception exception = assertThrows(Exception.class, () -> magazineService.addMagazine(createMagazineDTO));

        assertEquals("Some authors could not be found !", exception.getMessage());
    }


    @Test
    void addNewMagazine_WhenEverythingOk() throws Exception {

        createMagazineDTO.setAuthorIdList(authorIdList2);

        when(authorService.findAllById(createMagazineDTO.getAuthorIdList())).thenReturn(authorList);
        when(magazineMapper.createMagazineDTOToMagazine(createMagazineDTO,authorList)).thenReturn(magazine);
        when(magazineRepository.save(magazine)).thenReturn(magazineSaved);

        magazineService.addMagazine(createMagazineDTO);

        verify(magazineRepository, times(1) ).save(magazine);

        assertEquals("Teste", magazine.getTitle());
    }
}