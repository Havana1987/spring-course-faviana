package com.example.spring.course.service;

import com.example.spring.course.model.response.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {
    private final JwtEncoder jwtEncoder;
    private final String issuer;

    public TokenService(JwtEncoder jwtEncoder, @Value("${app.jwt.issuer}") String issuer) {
        this.jwtEncoder = jwtEncoder;
        this.issuer = issuer;
    }

    public TokenResponse issue(Authentication authentication) {
        Instant now = Instant.now();
        var claims = JwtClaimsSet.builder().issuer(issuer)
                .subject(authentication.getName()).issuedAt(now).expiresAt(now.plusSeconds(900))
                .claim("roles", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()).build();
        var header = JwsHeader.with(MacAlgorithm.HS256).build();
        String token = jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
        return new TokenResponse(token, "Bearer", 900L);
    }
}
