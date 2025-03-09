package com.fudo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final Credential credential;

    public SecurityConfig(Credential credential) {
        this.credential = credential;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Solo /auth puede llamarse publicamente
                        .requestMatchers("/auth").permitAll()
                        // Todos los demas necesitan autenticacion
                        .anyRequest().authenticated()
                )
                // Agrego que primero se ejecute mi filtro antes que el default de spring
                .addFilterBefore(new TokenAuthenticationFilter(credential),
                        UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable); // No uso formularios

        return http.build();
    }
}
