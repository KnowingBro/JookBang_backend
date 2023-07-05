package com.knownbro.jookbang.domain.user.presetation;

import com.knownbro.jookbang.domain.user.service.OAuth2GoogleService;
import com.knownbro.jookbang.global.jwt.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final OAuth2GoogleService googleService;

    @GetMapping("/login/google")
    public TokenResponse loginOfGoogle(@Validated @RequestParam(name = "code") String code) {
        return googleService.getJwtToken(code);
    }
}
