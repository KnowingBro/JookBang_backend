package com.knownbro.jookbang.domain.user.presetation;

import com.knownbro.jookbang.domain.user.presetation.dto.UserResponse;
import com.knownbro.jookbang.domain.user.service.FindUserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final FindUserInfoService findUserInfoService;

    @GetMapping("")
    public UserResponse getUserInfo() {
        return findUserInfoService.execute();
    }
}
