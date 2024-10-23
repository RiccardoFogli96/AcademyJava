package com.library.course.service;

import com.library.course.entity.Customer;
import com.library.course.model.LoginDTO;
import com.library.course.repository.CustomerRepository;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
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
public class AuthControllerService {

    private final CustomerRepository customerRepository;
    private final KeyPairGenerator keyPairGen;

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public String loginUser (LoginDTO loginDTO) throws Exception{

        Customer getCumstomer = customerRepository.findByEmailAndPassword(loginDTO.getEmail(),loginDTO.getPassword());

        if(getCumstomer == null){
            throw new Exception("Customer not found");
        }

        return Jwts.builder().subject(getCumstomer.getEmail()).claim("fistname", getCumstomer.getFirstName())
                .issuedAt(new Date())
                .expiration(Date.from(LocalDateTime.now().plus(10, TimeUnit.MINUTES.toChronoUnit()).toInstant(ZoneOffset.UTC)))
                .signWith(privateKey).compact();
    }

    public void generateKeyPair(){
        keyPairGen.initialize(521); // Lunghezza per ES512
        KeyPair keyPair = keyPairGen.generateKeyPair();
        privateKey = keyPair.getPrivate();
        publicKey = keyPair.getPublic();
    }
}
