package com.library.course.controller;

import com.library.course.model.CreateMagazineDTO;
import com.library.course.service.MagazineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("magazine/")
@RequiredArgsConstructor
public class MagazineController {
    private final MagazineService magazineService;

    @PostMapping("")
    public ResponseEntity<?> addMagazine(@RequestBody CreateMagazineDTO createMagazineDTO){
        CreateMagazineDTO newMagazine = magazineService.addMagazine(createMagazineDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(newMagazine);
    }
}
