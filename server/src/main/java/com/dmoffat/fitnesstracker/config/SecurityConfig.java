package com.dmoffat.fitnesstracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF
            .csrf(AbstractHttpConfigurer::disable)
            // Turn off "saved request" logic - this prevents sessions being created when trying to access protected
            // routes without an auth token
            .requestCache(RequestCacheConfigurer::disable)
            .authorizeHttpRequests(authorize ->
                authorize
                    .requestMatchers("/api/v1/auth/login").permitAll()
                    .anyRequest().authenticated()
            )
            .sessionManagement(session -> {
                // Only create a HttpSession when required
                session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
            });

        return http.build();
    }

    // Store the 'SecurityContext' in the HttpSession between requests
    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }

    // Provide a custom mechanism for looking up users
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        return new ProviderManager(authenticationProvider);
    }
}
