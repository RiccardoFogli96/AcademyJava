package com.library.course.controller;

import com.library.course.entity.Author;
import com.library.course.model.CreateMagazineDTO;
import com.library.course.model.MagazineDTO;
import com.library.course.service.MagazineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/magazine")
@RequiredArgsConstructor
public class MagazineController {
    private final MagazineService magazineService;
    @PostMapping("")
    public ResponseEntity<?> addMagazine(@RequestBody CreateMagazineDTO createMagazineDTO){
        try{
            MagazineDTO newMagazine = magazineService.addMagazine(createMagazineDTO);
            return  ResponseEntity.status(HttpStatus.CREATED).body(newMagazine);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }


    }

    @GetMapping()
    public ResponseEntity< List<Author >> getListAuthor( @RequestBody CreateMagazineDTO createMagazineDTO ) throws Exception {
        return ResponseEntity.ok(magazineService.getListAuthor(createMagazineDTO));
    }

}
