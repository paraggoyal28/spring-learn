package com.example.spring.ratelimiter.component;

import com.example.spring.ratelimiter.model.UserTier;
import com.example.spring.ratelimiter.service.RateLimiterService;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.FilterChain;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {
    private final RateLimiterService rateLimiterService;

    public RateLimitingFilter(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 1. Extract identification and tier headers (defaults to IP and FREE if missing)
        String userId = request.getHeader("X-User-Id");

        if (userId == null || userId.isEmpty()) {
            userId = request.getRemoteAddr(); // Fallback to client IP
        }

        String tierHeader = request.getHeader("X-User-Tier");
        UserTier tier;

        try {
            tier = (tierHeader != null) ? UserTier.valueOf(tierHeader.toUpperCase()): UserTier.FREE;
        } catch (IllegalArgumentException e) {
            tier = UserTier.FREE; // Fallback default
        }

        // 2. Validate against rate limiter
        boolean isAllowed = rateLimiterService.allowRequest(userId, tier);

        if (!isAllowed) {
            response.setStatus(429); // HTTP 429 Too Many Requests
            response.setContentType("application/json");
            response.getWriter().write("""
                    {
                        "status": 429,
                        "error": "Rate Limit exceeded",
                        "message": "You have exceeded your %s tier limit. Please upgrade or try again later."
                    }
                    """.formatted(tier.name()));
            return;
        }

        // 3. Pass request down the filter chain, if allowed
        filterChain.doFilter(request, response);
    }
}
