package com.weykko.librarysystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] PUBLIC_URI = {
            "/api/public/**"
    };

    private static final String[] ADMIN_URI = {
            "/api/admin/**"
    };

    private static final String[] USER_URI = {
            "/api/user/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(c -> c
                        .requestMatchers(PUBLIC_URI).permitAll()
                        .requestMatchers(ADMIN_URI).hasAuthority("ROLE_ADMIN")
                        .requestMatchers(USER_URI).hasAuthority("ROLE_SUPPORT")
                        .anyRequest().authenticated()
                ).httpBasic(
                        httpBasicConfigurer -> {
                        }
                )
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("admin")
                        .password("admin")
                        .roles("ADMIN")
                        .passwordEncoder(passwordEncoder::encode)
                        .build(),
                User.builder()
                        .username("support")
                        .password("support")
                        .roles("SUPPORT")
                        .passwordEncoder(passwordEncoder::encode)
                        .build(),
                User.builder()
                        .username("user")
                        .password("user")
                        .roles("USER")
                        .passwordEncoder(passwordEncoder::encode)
                        .build()
        );
    }
}
