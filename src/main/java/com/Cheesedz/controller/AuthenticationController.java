package com.Cheesedz.controller;

import com.Cheesedz.exception.AppException;
import com.Cheesedz.exception.eCommerceApiException;
import com.Cheesedz.model.role.Role;
import com.Cheesedz.model.role.RoleName;
import com.Cheesedz.model.User;
import com.Cheesedz.payload.*;
import com.Cheesedz.repository.RoleRepository;
import com.Cheesedz.repository.UserRepository;
import com.Cheesedz.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private static final String USER_ROLE_NOT_SET = "User role not set";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.status(HttpStatus.OK).body(
                new LoginResponse("Login successfully", new JwtAuthenticationResponse(jwt))
        );
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getUsername()))) {
            throw new eCommerceApiException(HttpStatus.BAD_REQUEST, "Username is already taken");
        }

        if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
            throw new eCommerceApiException(HttpStatus.BAD_REQUEST, "Email is already taken");
        }

        String name = signUpRequest.getName().toLowerCase();

        String username = signUpRequest.getUsername().toLowerCase();

        String email = signUpRequest.getEmail().toLowerCase();

        String phone = signUpRequest.getPhone().toLowerCase();

        String gender = signUpRequest.getGender().toLowerCase();

        Long roleID = signUpRequest.getRoleID();

        String dob = signUpRequest.getDob().toLowerCase();

        String password = passwordEncoder.encode(signUpRequest.getPassword());

        User user = new User(username, name,  email, phone, gender, dob, password);

//        List<Role> roles = new ArrayList<>();
//
//        if (userRepository.count() == 0) {
//            roles.add(roleRepository.findById(roleID)
//                    .orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));
//        } else {
//            roles.add(roleRepository.findByName(RoleName.USER)
//                    .orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));
//        }
//
//        user.setRoles(roleID);

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{userId}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).body(new SignupResponse("OK", "Sign up successfully"));
    }
}