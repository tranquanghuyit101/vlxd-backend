package org.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.backend.dto.request.LoginRequest;
import org.backend.dto.response.LoginResponse;
import org.backend.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthenticationService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        String token = authService.authenticate(request.getUsername(), request.getPassword());

        // Trả về DTO thay vì Map chung chung
        return ResponseEntity.ok(LoginResponse.builder()
                .token(token)
                .username(request.getUsername())
                .role("ADMIN")
                .build());
    }
}