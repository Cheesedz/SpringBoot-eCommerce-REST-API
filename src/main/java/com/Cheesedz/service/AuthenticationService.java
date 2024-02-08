package com.Cheesedz.service;

import com.Cheesedz.payload.JwtAuthenticationResponse;
import com.Cheesedz.payload.RefreshTokenRequest;
import com.Cheesedz.payload.SignupRequest;
import com.Cheesedz.payload.LoginRequest;

public interface AuthenticationService {
    Object signup(SignupRequest signupRequest);
    Object login(LoginRequest loginRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
