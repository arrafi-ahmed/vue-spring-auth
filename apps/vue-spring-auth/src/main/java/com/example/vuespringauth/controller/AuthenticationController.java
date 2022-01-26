package com.example.vuespringauth.controller;

import com.example.vuespringauth.model.User;
import com.example.vuespringauth.security.UserPrincipal;
import com.example.vuespringauth.service.AuthenticationService;
import com.example.vuespringauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/authenticate/register")
    public ResponseEntity<?> register(@RequestBody User user) throws Exception {
        if (userService.findUserByUsername(user.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken!");
        }

        try {
            User savedUser = userService.saveUser(user);
            if (savedUser != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Registration successful!");
            } else {
                throw new Exception("Registration failed!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/api/authenticate/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.signInAndReturnJWT(user));
    }

    //    test jwt token
    @GetMapping("/api/user")
    public ResponseEntity<?> getUserDetails(@AuthenticationPrincipal UserPrincipal userPrincipal) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByUsername(userPrincipal.getUsername()));
    }
}
