package com.library.course.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class RentalServiceTestMVC {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createRentalDTO() {
    }

    @Test
    void getAllRentalsByCustomerId() {
    }

    @Test
    void getAllRentalsPaginated() {
    }

    @Test
    void testGetAllRentalsPaginated() {
    }
}