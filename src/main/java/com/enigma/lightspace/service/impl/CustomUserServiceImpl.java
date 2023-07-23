package com.enigma.lightspace.service.impl;
import com.enigma.lightspace.entity.UserCredential;
import com.enigma.lightspace.entity.UserDetailsImpl;
import com.enigma.lightspace.repository.UserCredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserServiceImpl implements UserDetailsService {

    private final UserCredentialRepository userCredentialRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserCredential userCredential = userCredentialRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));

//        Role role = userCredential.getRole();
//        ERole eRole = userCredential.getRole().getRole();
//        String name = userCredential.getRole().getRole().name();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userCredential.getRole().getRole().name());

        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>(Collections.singleton(authority));

        return UserDetailsImpl.builder()
                .email(userCredential.getEmail())
                .password(userCredential.getPassword())
                .authorities(grantedAuthorities)
                .build();
    }
}