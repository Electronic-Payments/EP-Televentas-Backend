package com.electronicpayment.televentas.config.security;

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
import io.jsonwebtoken.JwtException;

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

    public boolean validateToken(String token) throws JwtException {
        try {
            JwtParser parser = Jwts.parser()
                    .setSigningKey(this.secretKey)
                    .build();

            parser.parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new JwtException("El token ha expirado. Por favor, inicie sesión nuevamente para obtener un nuevo token de acceso."); 
        } catch (JwtException e) {
            throw new JwtException("El token es inválido. Por favor, asegúrese de estar usando un token válido.");
        }
    }
}
