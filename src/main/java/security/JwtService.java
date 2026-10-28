package com.example.plantilla_examen.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY = "mi_clave_secreta_super_segura_para_el_examen_parcial_web_avanzada";

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generarToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String obtenerUsernameDelToken(String token) {
        return obtenerClaims(token).getSubject();
    }

    public boolean validarToken(String token, String username) {
        final String user = obtenerUsernameDelToken(token);
        return (user.equals(username) && !estaExpirado(token));
    }

    private boolean estaExpirado(String token) {
        return obtenerClaims(token).getExpiration().before(new Date());
    }

    private Claims obtenerClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}