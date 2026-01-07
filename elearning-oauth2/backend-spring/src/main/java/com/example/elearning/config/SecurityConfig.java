package com.example.elearning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    // Configure la sécurité HTTP et le Resource Server JWT
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
            );

        return http.build();
    }

    // Convertit les rôles Keycloak (realm_access.roles) en GrantedAuthorities
    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        // Par défaut JwtGrantedAuthoritiesConverter récupère les scopes via 'scope' claim.
        // Nous n'utilisons pas ça ici car Keycloak met les rôles dans 'realm_access.roles'.
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            // Récupère les rôles dans realm_access.roles et ajoute le préfixe ROLE_
            var authorities = new java.util.ArrayList<org.springframework.security.core.GrantedAuthority>();

            var realmAccess = jwt.getClaimAsMap("realm_access");
            if (realmAccess != null && realmAccess.containsKey("roles")) {
                var roles = (java.util.List<?>) realmAccess.get("roles");
                for (Object r : roles) {
                    String role = r.toString();
                    authorities.add(new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_" + role));
                }
            }

            // Optionnel: ajouter les authorities basées sur scopes
            authorities.addAll(grantedAuthoritiesConverter.convert(jwt));
            return authorities;
        });

        return converter;
    }
}
