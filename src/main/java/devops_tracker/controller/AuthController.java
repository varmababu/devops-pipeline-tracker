package devops_tracker.controller;

import devops_tracker.dto.AuthRequest;
import devops_tracker.dto.AuthResponse;
import devops_tracker.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

@CrossOrigin(origins = "http://localhost:5173")

public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody AuthRequest request) {

        System.out.println(
                "USERNAME = " + request.getUsername()
        );

        System.out.println(
                "PASSWORD = " + request.getPassword()
        );

        // TEMPORARY FORCE LOGIN
        String token =
                jwtUtil.generateToken("admin");

        return new AuthResponse(token);
    }
}