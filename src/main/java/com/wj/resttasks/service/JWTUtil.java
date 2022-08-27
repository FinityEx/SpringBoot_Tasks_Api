package com.wj.resttasks.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

//TODO: update to current standards
@Component
public class JWTUtil {

    //for demo only
    @Value("${jwt.secret}")
    private String secret;

    // jwt validation time
    private final int minutes = 60;

    public String createToken(String username){
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + minutes * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        return token;
    }

    public String getAuthUsername(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (token != null) {
            String username = Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token.replace("Bearer", ""))
                    .getBody()
                    .getSubject();

            if (username != null)
                return username;
            }
            return null;
        }


    }

