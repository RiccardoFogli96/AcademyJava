package com.library.course.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {

    @Autowired
    private KeyPair keyPair;


    @Value("${extra.jwt.expiration.minutes}")
    private Integer minutes;

    public String createToken(String email, String firstName) throws NoSuchAlgorithmException{
        return Jwts.builder().subject(email)
                .claim("firstname", firstName)
                .issuedAt(new Date())
                .expiration(Date.from(LocalDateTime.now().plus(minutes, TimeUnit.MINUTES.toChronoUnit()).toInstant(ZoneOffset.UTC)))
                .signWith(keyPair.getPrivate())
                .compact();
    }

    public Claims validateToken(String token){
        return Jwts.parser()
                .verifyWith(keyPair.getPublic())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
