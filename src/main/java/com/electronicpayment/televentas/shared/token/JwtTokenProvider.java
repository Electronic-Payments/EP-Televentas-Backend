package com.electronicpayment.televentas.shared.token;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.electronicpayment.televentas.shared.entities.User;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.ExpiredJwtException;

@SuppressWarnings("deprecation")
@Component
public class JwtTokenProvider {
    
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationTime;

    public String generateToken(User user) {
        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.secretKey));

        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("document", user.getDocument())
                .claim("name", user.getName())
                .claim("paternalLastName", user.getPaternalLastName())
                .claim("maternalLastName", user.getMaternalLastName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + this.expirationTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        JwtParser parser = Jwts.parser()
                .setSigningKey(this.secretKey)
                .build();

        Claims claims = parser.parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            JwtParser parser = Jwts.parser()
                    .setSigningKey(this.secretKey)
                    .build();

            parser.parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
