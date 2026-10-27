package com.example.spring.springsecurityproject.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
* Custom Converter to map both standard OAuth2 scopes (SCOPE_*)
* and identify Provider Roles (ROLE_*) from JWT claims into Spring Security GrantedAuthorities
 */
@Component
public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final JwtGrantedAuthoritiesConverter defaultAuthoritiesConverter
            = new JwtGrantedAuthoritiesConverter();

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        // 1. Extract standard Oauth2 scopes (e.g SCOPE_read:users)
        Collection<GrantedAuthority> authorities = defaultAuthoritiesConverter.convert(jwt);
        if (authorities == null) {
            authorities = new HashSet<>();
        }

        // 2. Extract Custom Identity Provider Roles (e.g Keycloak realm_access.roles)
        Collection<GrantedAuthority> customRoles = extractProviderRoles(jwt);

        // 3. Combine Scopes + Roles into unified collection
        Collection<GrantedAuthority> combinedAuthorities = Stream.concat(
                authorities.stream(),
                customRoles.stream()).collect(Collectors.toSet());

        // Use 'preferred_username' or fall back to JWT subject class ('sub')
        String principalClaimName = jwt.getClaimAsString("preferred_username");
        if (principalClaimName == null) {
            principalClaimName = jwt.getSubject();
        }

        return  new JwtAuthenticationToken(jwt, combinedAuthorities, principalClaimName);
    }

    @SuppressWarnings("checked")
    private Collection<GrantedAuthority> extractProviderRoles(Jwt jwt) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        // Example A: Keycloak "realm_access" claim format -> { "roles" : ["USER", "ADMIN"] }
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        if (realmAccess != null && realmAccess.containsKey("roles")) {
            List<String> roles = (List<String>)  realmAccess.get("roles");
            for (String role: roles) {
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
            }
        }

        // Example B: Standard "roles" array claim -> ["USER", "ADMIN"]
        List<String> directRoles = jwt.getClaimAsStringList("roles");
        if (directRoles != null) {
            for (String role: directRoles) {
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
            }
        }

        return grantedAuthorities;
    }
}
