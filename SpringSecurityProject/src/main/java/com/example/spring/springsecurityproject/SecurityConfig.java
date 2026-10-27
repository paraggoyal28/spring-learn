package com.example.spring.springsecurityproject;

import com.example.spring.springsecurityproject.converter.CustomJwtAuthenticationConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled=true) // Enables @PreAuthorize and @PostAuthorize
public class SecurityConfig {

    private final CustomJwtAuthenticationConverter customJwtAuthenticationConverter;

    public SecurityConfig(CustomJwtAuthenticationConverter customJwtAuthenticationConverter) {
        this.customJwtAuthenticationConverter = customJwtAuthenticationConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. Enable CORS & Disable CSRF for stateless REST APIs
                .cors(cors ->
                        cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
            // 2. Stateless session management (NO HTTP JSESSIONID created)
                .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 3. Endpoint access permissions
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/public/**", "/actuator/health").permitAll()
                        .requestMatchers("/api/admin/*").hasRole("ADMIN")
                        .anyRequest().authenticated())
                // 4. Configure OAuth2 Resource Server with Custom JWT converter
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(customJwtAuthenticationConverter)));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3000", "https://myapp.com"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
