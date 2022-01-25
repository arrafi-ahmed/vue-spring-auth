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
    public ResponseEntity<?> register(@RequestBody User user) {
        User foundUser = userService.findUserByUsername(user.getUsername());
        if (foundUser != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/api/authenticate/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
    }

    //    test jwt token
    @GetMapping("/api/user")
    public ResponseEntity<?> getUserDetails(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return new ResponseEntity<>(userService.findUserByUsername(userPrincipal.getUsername()), HttpStatus.OK);
    }
}
