package org.backend.config;

import lombok.RequiredArgsConstructor;
import org.backend.config.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable) // Tắt CSRF vì dùng JWT (Stateless)
                .authorizeHttpRequests(auth -> auth
                        // 1. Cho phép tất cả truy cập API Login/Register
                        .requestMatchers("/api/auth/**").permitAll()

                        // 2. Khách hàng có thể xem sản phẩm và danh mục (GET) mà không cần login
                        .requestMatchers(HttpMethod.GET, "/api/products", "/api/products/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/categories", "/api/categories/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/news", "/api/news/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/projects", "/api/projects/**").permitAll()
                        
                        // 3. Các API Admin, Dashboard, và thao tác ghi (POST/PUT/DELETE) phải có Token
                        .requestMatchers("/api/dashboard", "/api/dashboard/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/products", "/api/products/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/products", "/api/products/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/products", "/api/products/**").authenticated()

                        .requestMatchers(HttpMethod.POST, "/api/categories", "/api/categories/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/categories", "/api/categories/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/categories", "/api/categories/**").authenticated()

                        .requestMatchers(HttpMethod.GET, "/api/contacts", "/api/contacts/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/contacts", "/api/contacts/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/contacts", "/api/contacts/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/contacts", "/api/contacts/**").authenticated()

                        .requestMatchers(HttpMethod.POST, "/api/projects", "/api/projects/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/projects", "/api/projects/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/projects", "/api/projects/**").authenticated()

                        // 4. Mọi request còn lại đều phải qua bộ lọc authenticate
                        .anyRequest().authenticated()
                )
                // Cấu hình Stateless (không dùng Session)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                // Thêm Filter kiểm tra JWT trước khi vào các Filter mặc định của Spring
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173")); // React Port
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}