package com.Cheesedz.config;

import com.Cheesedz.repository.UserRepository;
import com.Cheesedz.security.JwtAuthenticationEntryPoint;
import com.Cheesedz.security.JwtAuthenticationFilter;
import com.Cheesedz.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true)
public class SecurityConfig {
    @Value(value = "${app.apiPrefix}")
    private String apiPrefix;
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
//    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

//    @Autowired
//    public SecurityConfig(CustomUserDetailService customUserDetailService, UserRepository userRepository,
//                          JwtAuthenticationEntryPoint unauthorizedHandler, JwtAuthenticationFilter jwtAuthenticationFilter) {
//        this.customUserDetailService = customUserDetailService;
//        this.unauthorizedHandler = unauthorizedHandler;
//        //this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(requests -> requests
//                        .anyRequest().authenticated()
                        .requestMatchers(
                                String.format("%s/auth/signup", apiPrefix),
                                String.format("%s/auth/login", apiPrefix),
                                String.format("%s", apiPrefix)
                        )
                        .permitAll()
                        .requestMatchers(GET,
                                String.format("%s/api/**", apiPrefix)).permitAll()
                        .requestMatchers(POST,
                                String.format("%s/api/auth/**", apiPrefix)).permitAll()
                        .requestMatchers(GET,
                                String.format("%s/api/users/checkUsernameAvailability", apiPrefix),
                                String.format("%s/api/users/checkEmailAvailability", apiPrefix)
                                ).permitAll());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}