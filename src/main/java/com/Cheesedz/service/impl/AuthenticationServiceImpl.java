package com.Cheesedz.service.impl;

import com.Cheesedz.model.User;
import com.Cheesedz.model.role.Role;
import com.Cheesedz.payload.*;
import com.Cheesedz.repository.UserRepository;
import com.Cheesedz.service.AuthenticationService;
import com.Cheesedz.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    public Object signup(SignupRequest signUpRequest) {
        Optional<User> foundUser = userRepository.findByEmail(signUpRequest.getEmail());
        if (foundUser.isPresent()) {
            return new ExceptionResponse("Email already taken","Not accepted", 406);
        } else {
            User user = new User();

            user.setDob(signUpRequest.getDob());
            user.setGender(signUpRequest.getGender());
            user.setPhone(signUpRequest.getPhone());
            user.setEmail(signUpRequest.getEmail());
            user.setName(signUpRequest.getName());
            user.setUsername(signUpRequest.getEmail());
            if (signUpRequest.getRoleID() == 0) {
                user.setRole(Role.USER);
            } else if (signUpRequest.getRoleID() == 1) {
                user.setRole(Role.SYSTEM_ADMIN);
            } else {
                user.setRole(Role.SHOP_ADMIN);
            }
            user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

            return userRepository.save(user);
        }
    }

    public Object login(LoginRequest loginRequest) {
        var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("Invalid email or password.")
        );

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                loginRequest.getPassword()));

        String jwt = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse authenticationResponse = new JwtAuthenticationResponse();

        authenticationResponse.setToken(jwt);
        authenticationResponse.setRefreshToken(refreshToken);
        return authenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse authenticationResponse = new JwtAuthenticationResponse();

            authenticationResponse.setToken(jwt);
            authenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return authenticationResponse;
        }
        return null;
    }
}