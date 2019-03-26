package com.simpleblog.security.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.simpleblog.security.UserPrinciple;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {
 
    @Value("${jwt.secret}")
    private String jwtSecret;
 
    @Value("${jwt.expiration}")
    private int jwtExpiration;
 
    public String generateJwtToken(Authentication authentication) {
 
        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();
 
        return Jwts.builder()
        		   .setSubject((userPrincipal.getUsername()))
        		   .setIssuedAt(new Date())
        		   .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
        		   .signWith(SignatureAlgorithm.HS512, jwtSecret)
        		   .compact();
    }
 
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                   .setSigningKey(jwtSecret)
                   .parseClaimsJws(token)
                   .getBody().getSubject();
    }
 
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (RuntimeException e) {
           e.printStackTrace();
        }
        
        return false;
    }
}
