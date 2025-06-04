package com.example.demo.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final Map<String, String> users = new HashMap<>();

    public CustomUserDetailsService(PasswordEncoder passwordEncoder) {
        // Usuário admin com senha "admin"
        String encodedPassword = passwordEncoder.encode("admin");
        users.put("admin", encodedPassword);
        System.out.println("Senha codificada para admin: " + encodedPassword);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!users.containsKey(username)) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }
        
        return User.builder()
                .username(username)
                .password(users.get(username))
                .roles("ADMIN")
                .build();
    }
}