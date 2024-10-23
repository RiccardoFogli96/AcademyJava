package com.library.course.service;

import com.library.course.entity.Customer;
import com.library.course.model.LoginDTO;
import com.library.course.repository.CustomerRepository;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomerRepository customerRepository;
    private final JwtService jwtService;



    public String loginUser (LoginDTO loginDTO) throws Exception{

        Customer getCumstomer = customerRepository.findByEmailAndPassword(loginDTO.getEmail(),loginDTO.getPassword());

        if(getCumstomer == null){
            throw new Exception("Customer not found");
        }

        return jwtService.createToken(getCumstomer.getEmail(), getCumstomer.getFirstName());
    }


}
