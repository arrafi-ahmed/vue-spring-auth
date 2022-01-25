package com.example.vuespringauth.security;

import com.example.vuespringauth.model.User;
import com.example.vuespringauth.service.UserService;
import com.example.vuespringauth.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        System.out.println("inside Custom userDetailsService 1");
        try {
            user = userService.findUserByUsername(username);
            System.out.println("inside Custom userDetailsService 2");
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException(username);
        }
        Set<GrantedAuthority> authorities = Set.of(SecurityUtil.convertToAuthority(user.getRole().name()));
        System.out.println(authorities);
        return new UserPrincipal(user, user.getId(), username, user.getPassword(), authorities);
    }
}
