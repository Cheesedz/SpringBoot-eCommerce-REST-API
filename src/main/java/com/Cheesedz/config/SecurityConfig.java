package com.Cheesedz.config;

import com.Cheesedz.model.role.Role;
import com.Cheesedz.security.JwtAuthenticationFilter;
import com.Cheesedz.service.impl.CustomUserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
//    @Value(value = "${app.apiPrefix}")
//    private String apiPrefix;

    private final CustomUserDetailServiceImpl customUserDetailService;

//    @Autowired
//    private JwtAuthenticationEntryPoint unauthorizedHandler;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter(){
//        return new JwtAuthenticationFilter();
//    }

//    @Autowired
//    public SecurityConfig(CustomUserDetailServiceImpl customUserDetailService, UserRepository userRepository,
//                          JwtAuthenticationEntryPoint unauthorizedHandler, JwtAuthenticationFilter jwtAuthenticationFilter) {
//        this.customUserDetailService = customUserDetailService;
//        this.unauthorizedHandler = unauthorizedHandler;
//        //this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
////                .cors(withDefaults())
//                .csrf().disable()
////                .exceptionHandling()
////                .authenticationEntryPoint(unauthorizedHandler)
////                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeHttpRequests(requests -> requests
//                        .requestMatchers(GET,
//                                String.format("%s/**", apiPrefix)).permitAll()
//                        .requestMatchers(POST,
//                                String.format("%s/**", apiPrefix)).permitAll()
//                        .requestMatchers(GET,
//                                String.format("%s/users/checkUsernameAvailability", apiPrefix)).permitAll()
//                        .requestMatchers(GET,
//                                String.format("%s/users/checkEmailAvailability", apiPrefix)).permitAll()
//                        .anyRequest().permitAll());
//
//        http
//                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//                //.formLogin(withDefaults())
//                .httpBasic(withDefaults());
//        return http.build();
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.requestMatchers("/api/auth/**")
                        .permitAll()
                        //.requestMatchers("api/admin").hasAnyAuthority(RoleName.SYSTEM_ADMIN.name())
                        .requestMatchers("api/user").hasAnyAuthority(Role.USER.name())
                        .anyRequest().authenticated())

                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class
                );
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailService.userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
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