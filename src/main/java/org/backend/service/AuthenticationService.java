package org.backend.service;

import lombok.RequiredArgsConstructor;
import org.backend.config.jwt.JwtService;
import org.backend.dto.request.LoginRequest;
import org.backend.dto.response.LoginResponse;
import org.backend.model.User;
import org.backend.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String authenticate(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        var user = repository.findByUsername(username)
                .orElseThrow(() -> new BadCredentialsException("invalid username"));
        return jwtService.generateToken(user.getUsername());
    }

    // Hàm này dùng để tạo user Admin đầu tiên (chỉ dùng 1 lần)
    public void registerInitialAdmin() {
        if (repository.findByUsername("admin").isEmpty()) {
            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("123456")) // Mật khẩu sẽ được mã hóa
                    .build();
            repository.save(admin);
        }
    }
}