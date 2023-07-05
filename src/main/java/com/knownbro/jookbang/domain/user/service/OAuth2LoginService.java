package com.knownbro.jookbang.domain.user.service;

import com.knownbro.jookbang.domain.user.domain.User;
import com.knownbro.jookbang.domain.user.domain.authority.Authority;
import com.knownbro.jookbang.domain.user.domain.repository.UserRepository;
import com.knownbro.jookbang.global.oauth.OAuthAttributes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuth2LoginService {

    private final UserRepository userRepository;

    protected User saveOrUpdate(OAuthAttributes oAuthAttributes) {
        Optional<User> user = userRepository.findByEmail(oAuthAttributes.getEmail());

        if (user.isEmpty()) {
            return userRepository.save(User.builder()
                    .email(oAuthAttributes.getEmail())
                    .name(oAuthAttributes.getName())
                    .authority(Authority.USER)
                    .build());
        }

        return user.get().update(oAuthAttributes);
    }
}

