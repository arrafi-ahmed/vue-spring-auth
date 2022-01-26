package com.example.vuespringauth.security;

import com.example.vuespringauth.model.User;
import com.example.vuespringauth.service.UserService;
import com.example.vuespringauth.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws BadCredentialsException {
        User user = userService.findUserByUsername(username);
        
        if (user == null) {
            throw new BadCredentialsException("Username not found!");
        }
        Set<GrantedAuthority> authorities = Set.of(SecurityUtil.convertToAuthority(user.getRole().name()));
        return new UserPrincipal(user, user.getId(), username, user.getPassword(), authorities);
    }
}
