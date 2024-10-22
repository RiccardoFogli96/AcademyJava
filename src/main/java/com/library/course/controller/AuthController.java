package com.library.course.controller;

import com.library.course.model.LoginDTO;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    @PostMapping("/public/login")
    public ResponseEntity<?> loginUser (@RequestBody() LoginDTO loginDTO){

    }



}
