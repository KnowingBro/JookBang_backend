package com.knownbro.jookbang.global.jwt.util;

import com.knownbro.jookbang.global.jwt.config.JwtProperties;
import com.knownbro.jookbang.global.jwt.dto.TokenResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.Date;

import static com.knownbro.jookbang.global.jwt.config.JwtConstants.*;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    private final JwtProperties jwtProperties;

    public TokenResponse generateToken(String authId, String role) {
        String accessToken = jwtProperties.getPrefix() + EMPTY.getMessage() + generateToken(authId, role, ACCESS_KEY.getMessage(), jwtProperties.getAccessExp());
        String refreshToken = jwtProperties.getPrefix() + EMPTY.getMessage() + generateToken(authId, role, REFRESH_KEY.getMessage(), jwtProperties.getRefreshExp());

        return new TokenResponse(accessToken, refreshToken, getExpiredTime());
    }

    private String generateToken(String authId, String role, String type, Long time) {
        return Jwts.builder()
                .setHeaderParam(TYPE.message, type)
                .claim(ROLE.message, role)
                .claim(AUTH_ID.message, authId)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .setExpiration(new Date(System.currentTimeMillis() + time * 1000))
                .compact();
    }

    public ZonedDateTime getExpiredTime() {
        return ZonedDateTime.now().plusSeconds(jwtProperties.getRefreshExp());
    }

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader(jwtProperties.getHeader());
        return parseToken(bearer);
    }

    public String parseToken(String bearer) {
        if (bearer != null && bearer.startsWith(jwtProperties.getPrefix())) {
            return bearer.replace(jwtProperties.getPrefix(), "");
        }
        return null;
    }
}
