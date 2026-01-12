package com.tallalmohar.taskmanager.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {
    @Value("${jwt.secret}")
    private String secret_key;
    @Value("${jwt.expiration.hours}")
    private long expirationHours;


    public String generateToken(String email){
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims(claims)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (expirationHours*60*60*1000)))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    //once user logs in we need to know who it is with the token
    //so we extract the email
    public String extractEmail(String token){
        try{
            return Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        }catch(ExpiredJwtException | SignatureException |MalformedJwtException e){
            System.err.print("Error:Whilst extracting Email From JWT");
        }
        return null;
    }

    public boolean isTokenValid(String token){
        try{
                // checking if parsing is even possible with token
                Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token);
             return true;
        } catch (ExpiredJwtException | SignatureException |MalformedJwtException e) {
            System.err.print("Error:Whilst Validating Token");
            return false;
        }
    }



    private SecretKey getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secret_key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
