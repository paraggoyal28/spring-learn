package com.example.spring.springsecurityproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    // Public Endpoint - No authentication required
    @GetMapping("/public")
    public ResponseEntity<Map<String, String>> getPublicInfo() {
        return ResponseEntity.ok(Map.of(
                "status", "Public access active",
                "info", "This route is publicly accessible without JWT token."
        ));
    }

    // Authenticated User Endpoint - Required ROLE_USER or SCOPE_read:users
    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN') or hasAuthority('SCOPE_read:users')")
    public ResponseEntity<Map<String, Object>> getUserProfile(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(Map.of(
                "message", "Successfully authenticated!",
                "userId", jwt.getSubject(),
                "username", jwt.getClaimAsString("preferred_username"),
                "email", jwt.getClaimAsString("email"),
                "issuedAt", jwt.getIssuedAt(),
                "expiredAt", jwt.getExpiresAt()
        ));
    }

    // Admin Only Endpoint - Requires ROLE_ADMIN
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> getAdminStats(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(Map.of(
                "systemStatus", "ALL_SYSTEMS_OPERATIONAL",
                "adminUser", jwt.getClaimAsString("preferred_username"),
                "secretData", "Confidential Admin Metrics 2026"
        ));
    }
}


