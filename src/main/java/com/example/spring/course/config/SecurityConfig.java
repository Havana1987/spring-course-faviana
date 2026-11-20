package com.example.spring.course.config;

import com.example.spring.course.repository.UserAccountRepository;
import com.example.spring.course.repository.model.UserAccount;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/api/auth/register").permitAll()
//                .requestMatchers("/api/catalog/products/public/**").permitAll()
//                .requestMatchers("/api/catalog/products/admin/**").hasRole("ADMIN")
//                .requestMatchers("/api/catalog/products/user/**").hasAnyRole("USER", "ADMIN")
//                .anyRequest().authenticated())
//            .httpBasic(Customizer.withDefaults());
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/register", "/api/auth/login").permitAll()
                        .requestMatchers("/api/catalog/products/public/**").permitAll()
                        .requestMatchers("/api/catalog/products/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/catalog/products/user/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(
                        org.springframework.security.config.http.SessionCreationPolicy.STATELESS))
                .requestCache(cache -> cache.disable())
                .oauth2ResourceServer(resource -> resource.jwt(jwt -> jwt
                        .jwtAuthenticationConverter(jwtAuthenticationConverter())));
        return http.build();
    }

    @Bean
    public org.springframework.security.authentication.AuthenticationManager authenticationManager(
            UserDetailsService users, PasswordEncoder encoder) {
        var provider = new org.springframework.security.authentication.dao.DaoAuthenticationProvider(users);
        provider.setPasswordEncoder(encoder);
        return new org.springframework.security.authentication.ProviderManager(provider);
    }

    @Bean
    public org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter jwtAuthenticationConverter() {
        var roles = new org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter();
        roles.setAuthoritiesClaimName("roles");
        roles.setAuthorityPrefix(""); // Le authorities emesse contengono gia ROLE_.
        var converter = new org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(roles);
        return converter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    public UserDetailsService userDetailsService(
//            PasswordEncoder passwordEncoder) {
//
//
//        UserDetails user = User.builder()
//                .username("mario")
//                .password(passwordEncoder.encode("password123"))
//                .roles("USER")
//                .build();
//
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("admin123"))
//                .roles("ADMIN")
//                .build();
//
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    @Bean
    CommandLineRunner createAdmin(
            UserAccountRepository repository,
            PasswordEncoder passwordEncoder) {


        return args -> {
            if (!repository.existsByUsername("admin")) {


                UserAccount admin = new UserAccount(
                        "admin",
                        passwordEncoder.encode("admin123"),
                        "ADMIN"
                );


                repository.save(admin);
            }
        };
    }
}