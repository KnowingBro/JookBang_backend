package com.knownbro.jookbang.domain.user.presetation.dto;

import lombok.Getter;

@Getter
public class UserResponse {

    private String email;
    private String name;

    public UserResponse(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
