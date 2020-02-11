package com.waracle.apigateway.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenManager {
    
    private static final int ACCESS_TOKEN_VALIDITY = 30 * 60 * 1000;
    private String jwtKey = "j3H5Ld5nYmGWyULy6xwpOgfSH++NgKXnJMq20vpfd+8=t";

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("www.waracle.com")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS256, jwtKey)
                .compact();
    }

    public boolean tokenValidate(String token) {
        if (getUsernameToken(token) != null && isExpired(token)) {
            return true;
        }
        return false;
    }

    public String getUsernameToken(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public boolean isExpired(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration().after(new Date(System.currentTimeMillis()));
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(token).getBody();
    }
    
}
