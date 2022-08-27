package com.wj.resttasks.controllers;

import com.wj.resttasks.service.JWTUtil;
import com.wj.resttasks.models.UserCreds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody UserCreds userCreds){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userCreds.getUsername(), userCreds.getPassword());

                Authentication auth = authenticationManager.authenticate(authenticationToken);

                String jwt = jwtUtil.createToken(userCreds.getUsername());

                //return jwt
                return ResponseEntity.ok()
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
                        .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
                        .build();
    }
}
