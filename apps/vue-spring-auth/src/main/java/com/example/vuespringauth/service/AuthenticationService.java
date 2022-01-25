package com.example.vuespringauth.service;

import com.example.vuespringauth.model.User;
import com.example.vuespringauth.security.UserPrincipal;
import com.example.vuespringauth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    public String signInAndReturnJWT(User signInRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(),
                signInRequest.getPassword()));
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        return jwtUtil.generateToken(userPrincipal);
    }
}
