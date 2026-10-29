package com.github.dafloresz.autopecas_erp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->
                auth
                        .requestMatchers(HttpMethod.POST, "/api/employees").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/employees/{id}").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/employees/{id}").hasAnyRole("MANAGER", "ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/employees/directory").hasAnyRole("INTERN", "MANAGER", "ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employees").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/employees/{id}").hasAnyRole("MANAGER", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/products").hasAnyRole("INTERN", "MANAGER", "ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.PUT, "/api/products/{id}").hasAnyRole("INTERN", "MANAGER", "ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.PATCH, "/api/products/{id}").hasAnyRole("INTERN", "MANAGER", "ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.DELETE, "/api/products/{id}").hasAnyRole("EMPLOYEE", "ADMIN", "MANAGER")

                        .requestMatchers(HttpMethod.POST, "/api/categories").hasAnyRole("INTERN", "MANAGER", "ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.PUT, "/api/categories/{id}").hasAnyRole("INTERN", "MANAGER", "ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.PATCH, "/api/categories/{id}").hasAnyRole("INTERN", "MANAGER", "ADMIN", "EMPLOYEE")
                        .requestMatchers(HttpMethod.DELETE, "/api/categories/{id}").hasAnyRole("EMPLOYEE", "ADMIN", "MANAGER")

                        .requestMatchers(HttpMethod.PUT, "/api/clients/{id}").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/api/clients/{id}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/orders/{id}").authenticated()

                        .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
