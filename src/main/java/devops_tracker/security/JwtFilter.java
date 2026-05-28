package devops_tracker.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component

public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService
            userDetailsService;

    @Override
    protected void doFilterInternal(

            HttpServletRequest request,

            HttpServletResponse response,

            FilterChain filterChain)

            throws ServletException,
            IOException {

        try {

            // SKIP LOGIN API
            if (request.getServletPath()
                    .equals("/auth/login")) {

                filterChain.doFilter(
                        request,
                        response
                );

                return;
            }

            // GET AUTH HEADER
            final String authHeader =
                    request.getHeader(
                            "Authorization"
                    );

            String username = null;

            String jwt = null;

            // CHECK TOKEN
            if (authHeader != null
                    && authHeader.startsWith(
                    "Bearer ")) {

                jwt = authHeader.substring(7);

                try {

                    username =
                            jwtUtil.extractUsername(
                                    jwt
                            );

                } catch (Exception e) {

                    System.out.println(
                            "JWT ERROR: "
                                    + e.getMessage()
                    );
                }
            }

            // VALIDATE USER
            if (username != null
                    && SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    == null) {

                UserDetails userDetails =

                        userDetailsService
                                .loadUserByUsername(
                                        username
                                );

                // VALIDATE TOKEN
                if (jwtUtil.validateToken(
                        jwt,
                        userDetails.getUsername()
                )) {

                    UsernamePasswordAuthenticationToken authToken =

                            new UsernamePasswordAuthenticationToken(

                                    userDetails,

                                    null,

                                    userDetails
                                            .getAuthorities()
                            );

                    authToken.setDetails(

                            new WebAuthenticationDetailsSource()

                                    .buildDetails(
                                            request
                                    )
                    );

                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(
                                    authToken
                            );
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "FILTER ERROR: "
                            + e.getMessage()
            );
        }

        // CONTINUE REQUEST
        filterChain.doFilter(
                request,
                response
        );
    }
}