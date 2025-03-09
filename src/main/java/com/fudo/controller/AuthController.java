package com.fudo.controller;

import static com.fudo.security.TokenAuthenticationFilter.MOCK_TOKEN;
import com.fudo.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if (authService.authenticate(username, password)) {
            return ResponseEntity.ok(Map.of("token", MOCK_TOKEN));
        }
        return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
    }
}