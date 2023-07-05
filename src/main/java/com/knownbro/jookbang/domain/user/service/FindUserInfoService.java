package com.knownbro.jookbang.domain.user.service;

import com.knownbro.jookbang.domain.user.UserFacade;
import com.knownbro.jookbang.domain.user.domain.User;
import com.knownbro.jookbang.domain.user.presentation.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindUserInfoService {

    private final UserFacade userFacade;

    public UserResponse execute() {
        User user = userFacade.getCurrentUser();

        return new UserResponse(user.getEmail(), user.getName());
    }
}
