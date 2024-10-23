package com.library.course.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {

    //private final KeyPairGenerator keyPairGen;
    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);


    @Value("${extra.jwt.expiration.minutes}")
    private Integer minutes;

    public String createToken(String email, String firstName){
        return Jwts.builder().setSubject(email)
                .claim("fistname", firstName)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plus(minutes, TimeUnit.MINUTES.toChronoUnit()).toInstant(ZoneOffset.UTC)))
                .signWith(SignatureAlgorithm.HS512, key).compact();
    }

    /*public void generateKeyPair(){
        keyPairGen.initialize(521); // Lunghezza per ES512
        KeyPair keyPair = keyPairGen.generateKeyPair();
        privateKey = keyPair.getPrivate();
        publicKey = keyPair.getPublic();
    }*/

    public Claims validateToken(String token){
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }
}
