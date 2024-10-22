package com.library.course.service;

import com.library.course.entity.Customer;
import com.library.course.model.LoginDTO;
import com.library.course.repository.CustomerRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthControllerService {

    private final CustomerRepository customerRepository;

    public String loginUser (LoginDTO loginDTO) throws Exception{

        Customer getCumstomer = customerRepository.findByEmailAndPassword(loginDTO.getEmail(),loginDTO.getPassword());

        if(getCumstomer == null){
            throw new Exception("Customer not found");
        }

        return Jwts.builder().setSubject(getCumstomer.getEmail()).claim("fistname", getCumstomer.getFirstName())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plus(10, TimeUnit.MINUTES.toChronoUnit()).toInstant(ZoneOffset.UTC)))
                .signWith(SignatureAlgorithm.ES512, Keys.secretKeyFor(SignatureAlgorithm.ES512)).compact();
    }

}
