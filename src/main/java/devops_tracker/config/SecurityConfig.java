package devops_tracker.config;

import devops_tracker.security.JwtFilter;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration

public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http)

            throws Exception {

        http

                // DISABLE CSRF
                .csrf(csrf ->
                        csrf.disable())

                // ENABLE CORS
                .cors(cors -> {})

                // AUTHORIZATION
                .authorizeHttpRequests(auth -> auth

                        // PUBLIC ROUTES
                        .requestMatchers(

                                "/auth/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**"

                        ).permitAll()

                        // SECURE ALL OTHER APIs
                        .anyRequest()
                        .authenticated()
                )

                // STATELESS SESSION
                .sessionManagement(session ->

                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                // DISABLE FORM LOGIN
                .formLogin(form ->
                        form.disable())

                // DISABLE BASIC AUTH
                .httpBasic(basic ->
                        basic.disable());

        // JWT FILTER
        http.addFilterBefore(

                jwtFilter,

                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(

            AuthenticationConfiguration config)

            throws Exception {

        return config.getAuthenticationManager();
    }
}