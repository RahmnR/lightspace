package com.enigma.lightspace.service.impl;

import com.enigma.lightspace.entity.*;
import com.enigma.lightspace.entity.constant.ERole;
import com.enigma.lightspace.model.request.AuthRequest;
import com.enigma.lightspace.model.response.LoginResponse;
import com.enigma.lightspace.model.response.RegisterResponse;
import com.enigma.lightspace.repository.UserCredentialRepository;
import com.enigma.lightspace.security.BCryptUtils;
import com.enigma.lightspace.security.JwtUtils;
import com.enigma.lightspace.service.AdminService;
import com.enigma.lightspace.service.AuthService;
import com.enigma.lightspace.service.RoleService;
import com.enigma.lightspace.service.VendorService;
import com.enigma.lightspace.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserCredentialRepository userCredentialRepository;
    private final AuthenticationManager authenticationManager;
    private final BCryptUtils bCryptUtils;
    private final RoleService roleService;
    private final JwtUtils jwtUtils;
    private final ValidationUtil validationUtil;
    private final AdminService adminService;
    private final VendorService vendorService;


    @Transactional(rollbackOn = Exception.class)
    @Override
    public RegisterResponse register(AuthRequest request) {
        try {
            Role role = roleService.getOrSave(ERole.ROLE_ADMIN);
            UserCredential credential = UserCredential.builder()
                    .email(request.getEmail())
                    .password(bCryptUtils.hashPassword(request.getPassword()))
                    .role(role)
                    .build();
            userCredentialRepository.saveAndFlush(credential);

            Admin admin = Admin.builder()
                    .email(request.getEmail())
                    .userCredential(credential)
                    .build();
            adminService.create(admin);
            return RegisterResponse.builder().email(credential.getEmail()).build();
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "user already exist");
        }
    }
    @Transactional(rollbackOn = Exception.class)
    @Override
    public RegisterResponse registerVendor(AuthRequest request) {
        try {
            Role role = roleService.getOrSave(ERole.ROLE_VENDOR);
            UserCredential credential = UserCredential.builder()
                    .email(request.getEmail())
                    .password(bCryptUtils.hashPassword(request.getPassword()))
                    .role(role)
                    .build();
            userCredentialRepository.saveAndFlush(credential);
            Vendor vendor = Vendor.builder()
                    .userCredential(credential)
                    .build();
            vendorService.create(vendor);
            return RegisterResponse.builder().email(credential.getEmail()).build();
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "user already exist");
        }
    }

    @Override
    public LoginResponse login(AuthRequest request) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        UserDetailsImpl userDetails = (UserDetailsImpl) authenticate.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        String token = jwtUtils.generateToken(userDetails.getEmail());

        return LoginResponse.builder()
                .email(userDetails.getEmail())
                .roles(roles)
                .token(token)
                .build();
    }
}
