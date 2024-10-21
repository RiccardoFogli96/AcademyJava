package com.library.course.controller;

import com.library.course.model.CreateMagazineDTO;
import com.library.course.service.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("magazine/")
public class MagazineController {
    @Autowired
    MagazineService magazineService;

    @PostMapping("")
    public ResponseEntity<?> addMagazine(@RequestBody CreateMagazineDTO createMagazineDTO){

    }
}
