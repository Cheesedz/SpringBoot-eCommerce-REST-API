package com.Cheesedz.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserDetailService {
    UserDetailsService userDetailsService();
}
