package com.example.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.util.Date;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME = 86400000; // 1일

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1일 유효
                .signWith(SECRET_KEY)
                .compact();
    }

 // JWT 토큰 검증 및 사용자 이름 추출
    public String validateTokenAndGetUsername(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            
            Date expiration = claims.getExpiration();
            if (expiration != null && expiration.before(new Date())) {
                return null;
            }
            if (claims.getExpiration().before(new Date())) {
                return null;
            }
            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }}
