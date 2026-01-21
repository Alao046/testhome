package tech.justjava.testhome.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public", "/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userAuthoritiesMapper(userAuthoritiesMapper())
                        )
                )
                .logout(logout -> logout.logoutSuccessUrl("/public"));

        return http.build();
    }

    @Bean
    @SuppressWarnings("unchecked")
    public GrantedAuthoritiesMapper userAuthoritiesMapper() {
        return (authorities) -> {
            List<GrantedAuthority> mappedAuthorities = new ArrayList<>();

            authorities.forEach(authority -> {
                // Keep existing authorities (like SCOPE_email)
                mappedAuthorities.add(authority);

                if (authority instanceof OidcUserAuthority oidcAuth) {
                    Map<String, Object> claims = oidcAuth.getAttributes();

                    // Keycloak puts realm roles inside "realm_access" -> "roles"
                    if (claims.containsKey("realm_access")) {
                        Map<String, Object> realmAccess = (Map<String, Object>) claims.get("realm_access");
                        if (realmAccess.containsKey("roles")) {
                            List<String> roles = (List<String>) realmAccess.get("roles");
                            roles.forEach(role -> {
                                // This converts "ADMIN" to "ROLE_ADMIN"
                                mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));
                            });
                        }
                    }
                }
            });

            return mappedAuthorities;
        };
    }
}


//   .authenticated()
//hasRole("ADMIN")








