package com.knownbro.jookbang.global.jwt.auth;

import com.knownbro.jookbang.global.jwt.config.JwtConstants;
import com.knownbro.jookbang.global.jwt.util.JwtProvider;
import com.knownbro.jookbang.global.security.auth.AuthDetailsService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtAuth {

    private final JwtProvider jwtProvider;
    private final AuthDetailsService authDetailsService;

    public Authentication authentication(String token) {
        Claims claims = jwtProvider.getJws(token).getBody();

        if(isNotAccessToken(token)) {
            throw new IllegalArgumentException("엑세스 토큰이 아니여");
        }

        UserDetails userDetails = authDetailsService.loadUserByUsername(claims.get(JwtConstants.AUTH_ID.message).toString());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private boolean isNotAccessToken(String token) {
        if (token.isEmpty()) {
            throw new IllegalArgumentException("엑세스 토큰이 업서");
        }

        String role = jwtProvider.getJws(token).getHeader().get(JwtConstants.TYPE.message).toString();
        return !role.equals(JwtConstants.ACCESS_KEY.message);
    }
}