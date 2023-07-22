package com.enigma.lightspace.service;

import com.enigma.lightspace.model.request.AuthRequest;
import com.enigma.lightspace.model.response.LoginResponse;
import com.enigma.lightspace.model.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(AuthRequest request);
    RegisterResponse registerVendor(AuthRequest request);
    LoginResponse login(AuthRequest request);
}
