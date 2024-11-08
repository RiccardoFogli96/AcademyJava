package com.library.course.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.course.model.CreateMagazineDTO;
import com.library.course.model.TipologyMagazine;
import com.library.course.service.MagazineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
class MagazineControllerTest {

	@MockBean
	private MagazineService magazineService;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	MockMvc mockMvc;

	@InjectMocks
	MagazineController magazineController;

	CreateMagazineDTO createMagazineDTO;
	List<Long> authorIds = new ArrayList <>();

	@BeforeEach
	void setUp() {
		authorIds = Arrays.asList(0L,1L,2L);

		createMagazineDTO = CreateMagazineDTO.builder()
				.title("MagazineTest")
				.tipologyMagazine(TipologyMagazine.MONTHLY)
				.publishedDate(LocalDate.now())
				.numberMagazine(0)
				.authorIdList(authorIds)
				.build();
	}

	@Test
	void getListAuthor_EverythingOk() throws Exception {

		MvcResult result = mockMvc.perform(get("/magazine").content(objectMapper.writeValueAsString(createMagazineDTO)).contentType(MediaType.APPLICATION_JSON))
					.andReturn();

		result.getResponse().getContentAsString().

	}
}