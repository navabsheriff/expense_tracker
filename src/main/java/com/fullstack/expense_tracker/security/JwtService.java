package com.fullstack.expense_tracker.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secretKey;
    public String generateToken(String email){

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60))
                .setIssuedAt(new Date())
                .signWith(getSigningKey())
                .compact();
    }
    public String extractEmail(String token){

        return Jwts.parserBuilder()


                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    private SecretKey getSigningKey(){

        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
